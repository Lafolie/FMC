local path = select(1, ...):match "(.+%.).-"
-- local config = require(path .. "config")
local util = require(path .. "util")

local insert = table.insert

local dumpFiles = 
{
	-- blocks = "run/blockIDs.txt",
	entities = "run/entityIDs.txt",
	items = "run/itemIDs.txt",
	fluids = "run/fluidIDs.txt"
}

local csvFiles =
{
	-- blocks = "tools/input/blocks.csv",
	entities = "tools/input/entities.csv",
	items = "tools/input/items.csv",
	fluids = "tools/input/fluids.csv"
}

function loadCSV(path)
	local lut = {}
	local csv = util.readHeaderedCSV(path)
	for k, v in ipairs(csv) do
		lut[v.id] = true
	end

	return {lut = lut, csv = csv}
end

function addRow(csv, ...)
	local row = setmetatable({...}, csv.mt)
	insert(csv, row)
end

function merge(name, ...)
	local data = loadCSV(csvFiles[name])
	for id in io.lines(dumpFiles[name]) do
		if not data.lut[id] then
			addRow(data.csv, id, ...)
		end
	end

	util.writeCSV(csvFiles[name], data.csv)
end

merge("entities", "n", "n", "n", "n", "n")
merge("items", "n")
merge("fluids", "n")