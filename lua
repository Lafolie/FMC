#!/usr/bin/env lua5.2
local mode = select(1, ...)
require("tools." .. mode)--(select(2, ...))