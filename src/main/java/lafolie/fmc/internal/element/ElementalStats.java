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

		NbtCompound elements = getOrCreateAttributeNbt(attribute);
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
	public default void trySync() {}

	/**
	 * Gets the nbt compound for all elemental data.
	 * Implemented by ElementalStatsComponent for most types.
	 * ItemStack implements this directly since Items don't use CCA.
	 * @return master elemental NBT container
	 */
	public NbtCompound getNbtCompound();

	// public void populateInnateStats(NbtCompound nbt);

	/**
	 * Gets the nbt compound for a specific attribute.
	 * Attribute coumpounds contain all the elements and affinity counts
	 * for the given attribute.
	 * @param attribute
	 * @return nbt compound to add ElementalAspect/Byte pairs
	 */
	public default NbtCompound getAttributeNbt(ElementalAttribute attribute)
	{
		return getNbtCompound().getCompound(attribute.toNbtKey());
	}

	/**
	 * Get or create the nbt compound for a specific attribute.
	 * Attribute coumpounds contain all the elements and affinity counts
	 * for the given attribute.
	 * @param attribute
	 * @returnnbt compound to add ElementalAspect/Byte pairs
	 */
	public default NbtCompound getOrCreateAttributeNbt(ElementalAttribute attribute)
	{
		String key = attribute.toNbtKey();
		NbtCompound nbt = getNbtCompound();
		if(!nbt.contains(key, NbtCompound.COMPOUND_TYPE))
		{
			nbt.put(key, new NbtCompound());
		}
		return nbt.getCompound(key);
	}
}