package lafolie.fmc.element;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;


public interface ElementalStatsComponent extends ComponentV3
{
	public final static String KEY = "FMC_ElementalStats";
	public final static String INIT_KEY = "FMC_ElementalStatsInit";

	public NbtCompound getElementNbt();
	public void setElementNbt(NbtCompound nbt);

	public default boolean hasInitInnate()
	{
		return getElementNbt().contains(INIT_KEY);
	}

	public default void setHasInitInnate()
	{
		getElementNbt().putBoolean(INIT_KEY, true);
		trySync();
	}

	// Method is empty because it is unused by Items
	public default void trySync() {}

	public default void addElement(ElementalAspect element, ElementalAttribute attribute, byte inAmt)
	{
		if(element == ElementalAspect.NONE)
		{
			return;
		}
		
		String key = element.toNbtKey();

		NbtCompound elements = getOrCreateElementalNbt(attribute);
		if(!elements.contains(key))
		{
			elements.putByte(key, inAmt);
		}
		else
		{
			byte amt = elements.getByte(key);
			amt += inAmt;
			if(amt > (byte)0)
			{
				elements.putByte(key, amt);
			}
			else
			{
				elements.remove(key);
			}
		}

		// trySync();
	}

	/**
	 * MAY RETURN NULL!
	 * @param attribute
	 * @return
	 */
	public default NbtCompound getElementalNbt(ElementalAttribute attribute)
	{
		NbtCompound nbt = getElementNbt();
		String nbtKey = attribute.toNbtKey();
		if(nbt.contains(nbtKey, NbtElement.COMPOUND_TYPE))
		{
			return nbt.getCompound(nbtKey);
		}

		return null;
	}

	public default NbtCompound getOrCreateElementalNbt(ElementalAttribute attribute)
	{
		NbtCompound nbt = getElementNbt();
		String nbtKey = attribute.toNbtKey();

		if(!nbt.contains(nbtKey, NbtElement.COMPOUND_TYPE))
		{
			nbt.put(nbtKey, new NbtCompound());
			setElementNbt(nbt);
		}

		// trySync();
		return nbt.getCompound(nbtKey);
	}
}