package lafolie.fmc.util;

import java.io.FileWriter;
import java.io.IOException;

import lafolie.fmc.FMC;
import net.minecraft.registry.DefaultedRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public final class DumpIDs
{
	public static void exec()
	{
		dumpRegistry(Registries.BLOCK, "blockIDs.txt");
		dumpRegistry(Registries.ITEM, "itemIDs.txt");
		dumpRegistry(Registries.FLUID, "fluidIDs.txt");
		dumpRegistry(Registries.ENTITY_TYPE, "entityIDs.txt");
	}

	private static void dumpRegistry(DefaultedRegistry<?> registry, String name)
	{
		StringBuilder strBuild = new StringBuilder();
		for(Identifier id : registry.getIds())
		{
			strBuild.append(id.toString());
			strBuild.append(System.lineSeparator());
		}

		try
		{
			FileWriter writer = new FileWriter(name);
			writer.write(strBuild.toString());
			writer.flush();
			writer.close();
		} catch (IOException e)
		{
			FMC.LOG.error("Error creating ID dummp file " + name);
			e.printStackTrace();
		}
	}
}
