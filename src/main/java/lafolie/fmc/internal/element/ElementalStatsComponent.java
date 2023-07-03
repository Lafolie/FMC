package lafolie.fmc.internal.element;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import lafolie.fmc.internal.Components;
import net.minecraft.nbt.NbtCompound;

public class ElementalStatsComponent implements ComponentV3, ElementalStats, AutoSyncedComponent
{
	private NbtCompound nbt = new NbtCompound();
	private Object provider;

	public ElementalStatsComponent(Object object)
	{
		provider = object;
	}

	@Override
	public void readFromNbt(NbtCompound tag)
	{
		if(tag.contains(NBT_KEY))
		{
			nbt = tag.getCompound(NBT_KEY);
		}
	}

	@Override
	public void writeToNbt(NbtCompound tag)
	{
		if(!nbt.isEmpty())
		{
			tag.put(NBT_KEY, nbt);
		}
	}

	@Override
	public void trySync()
	{
		Components.ELEMENTAL_STATS.sync(provider);
	}

	@Override
	public NbtCompound getNbtCompound()
	{
		return nbt;
	}
}