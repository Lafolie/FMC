local path = select(1, ...):match "(.+%.).-"
local config = require(path .. "config")
local util = require(path .. "util")
local json = require(path .. "lib.json.json-beautify")

local insert = table.insert

local elementShorthands = 
{
	n = util.elementalAspects.none,
	f = util.elementalAspects.fire,
	i = util.elementalAspects.ice,
	t = util.elementalAspects.thunder,
	w = util.elementalAspects.wind,
	u = util.elementalAspects.water,
	e = util.elementalAspects.earth,
	p = util.elementalAspects.poison,
	h = util.elementalAspects.holy,
	d = util.elementalAspects.dark,
	g = util.elementalAspects.gravity

}

local elementAttributes = 
{
	immunity = "immunity",
	absorb = "absorb",
	fatal = "fatal",
	revive = "revive",
	elements = "elements"
}

local jsonConfig = {indent = "\t"}

local csvFiles =
{
	-- blocks = "tools/input/blocks.csv",
	entities = "tools/input/entities.csv",
	items = "tools/input/items.csv",
	fluids = "tools/input/fluids.csv"
}

function parseEntityElements(str)
	local result = {}
	for char in str:gmatch "." do
		if char ~= "n" then
			insert(result, elementShorthands[char])
		end
	end

	return result
end

local outputProto = {}

function outputProto:init(prefix)
	self.prefix = prefix and (prefix .. "_") or ""
	self.data = {}
	self.lut = {}

	for _, element in ipairs(util.elementalAspects) do
		if element ~= util.elementalAspects.none then
			self.data[element] =
			{
				replace = false,
				values = {}
			}
		end
	end

	self.collisions = 0
	self.mismatches = 0
end

function outputProto:printMeta()
	print(string.format("%d collisions, %s mismatches", self.collisions, self.mismatches))
end

function outputProto:save(path)
	for element, tbl in pairs(self.data) do
		if #tbl.values > 0 then
			local p = path:format(string.format("%s%s", self.prefix, element))
			local str = json.beautify(tbl, jsonConfig)
			util.writeString(p, str)
		end
	end

	self:printMeta()
end

function outputProto:add(id, element)
	--ensure added IDs are unique
	if self.lut[id] then
		self.collisions = self.collisions + 1
		print(string.format("Found duplicate tag %s, skipping", id))
		if self.lut[id] ~= element then
			self.mismatches = self.mismatches + 1
			print(string.format([[
	WARNING: Duplicate tag elements do not match!
		Existing element: %s
		Given element:    %s]], self.lut[id], element))
		end

		return
	end
	-- print(element, id)
	if element ~= util.elementalAspects.none then
		self.lut[id] = element
		insert(self.data[element].values, id)
	end
end

function outputProto:addEntity(id, elements)
	for _, element in ipairs(elements) do
		if element ~= util.elementalAspects.none then
			insert(self.data[element].values, id)
		end
	end
end

function mkOutput(prefix)
	local tbl = setmetatable({}, {__index = outputProto})
	tbl:init(prefix)
	return tbl
end

-- Items
do
	local data = util.readHeaderedCSV(csvFiles.items)
	local output = mkOutput()

	for _, row in ipairs(data) do
		local element = elementShorthands[row.element]
		output:add(row.id, element)
	end

	output:save(config.commonTagPath .. "/items/%s_elemental_items.json")
end

-- Fluids
do
	local data = util.readHeaderedCSV(csvFiles.fluids)
	local output = mkOutput()

	for _, row in ipairs(data) do
		local element = elementShorthands[row.element]
		output:add(row.id, element)
	end

	output:save(config.commonTagPath .. "/fluids/%s_elemental_fluids.json")
end

-- Entities
do
	local data = util.readHeaderedCSV(csvFiles.entities)
	local outputs = {}

	for attr in pairs(elementAttributes) do
		local prefix = attr ~= elementAttributes.elements and attr
		outputs[attr] = mkOutput(prefix)
	end

	for _, row in ipairs(data) do
		for attr in pairs(elementAttributes) do
			outputs[attr]:addEntity(row.id, parseEntityElements(row[attr]))
		end
	end

	for _, output in pairs(outputs) do
		output:save(config.commonTagPath .. "/entity_types/%s_elemental_entities.json")
	end
end