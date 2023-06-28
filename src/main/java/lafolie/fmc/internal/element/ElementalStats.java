package lafolie.fmc.internal.element;

import lafolie.fmc.element.ElementalAspect;
import lafolie.fmc.element.ElementalAttribute;
import net.minecraft.nbt.NbtCompound;

/**
 * Interface for modifying stats of Elemental Objects.
 * For ItemStacks, this is implemented directly on the ItemStack class.
 * For all others, it is implemented on thee ElementStatsComponent class.
 */
public interface ElementalStats
{
	public final String NBT_KEY = "elemental_stats";

	public default void modifyElement(ElementalAspect element, ElementalAttribute attribute, byte amount)
	{
		if(element == ElementalAspect.NONE)
		{
			return;
		}

		String key = element.toNbtKey();

		NbtCompound elements = getNbtCompound();
		if(!elements.contains(key))
		{
			elements.putByte(key, amount);
		}
		else
		{
			byte amt = elements.getByte(key);
			amt += amount;
			if(amt > (byte)0)
			{
				elements.putByte(key, amt);
			}
			else
			{
				elements.remove(key);
			}
		}
	}

	// Trigger a Component sync (does nothing for Items)
	public default void trySync() {};

	public NbtCompound getNbtCompound();

	// public NbtCompound getElementalNbt(ElementalAttribute attribute);

	// public NbtCompound getOrCreateElementalNbt(ElementalAttribute attribute);
}