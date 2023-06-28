package lafolie.fmc.element;

import java.util.EnumMap;
import java.util.Map;

import lafolie.fmc.FMC;
import lafolie.fmc.internal.element.ElementalStats;
import net.minecraft.nbt.NbtCompound;

public interface ElementalObject
{
	public default void addElementalAspect(ElementalAspect element, ElementalAttribute attribute, int amount)
	{
		ElementalStats stats = getElementalStats();
		stats.modifyElement(element, attribute, (byte)amount);
		stats.trySync();
	}

	public default void removeElementalAspect(ElementalAspect element, ElementalAttribute attribute, int amount)
	{
		ElementalStats stats = getElementalStats();
		stats.modifyElement(element, attribute, (byte)-amount);
		stats.trySync();
	}

	/**
	 * Add a resistance (value 2) whilst automatically increasing resistance
	 * to the resistantTo element (value 1), and increasing weakness to weakTo
	 * element (value 2).
	 * Holy and Dark are accounted for.
	 * @param element
	 */
	public default void addElementalResistance(ElementalAspect element)
	{
		ElementalStats stats = getElementalStats();
		stats.modifyElement(element, ElementalAttribute.RESISTANCE, (byte)2);
		stats.modifyElement(element.getWeakTo(), ElementalAttribute.WEAKNESS, (byte)2);
		if(element != ElementalAspect.DARK && element != ElementalAspect.HOLY)
		{
			stats.modifyElement(element.getResistantTo(), ElementalAttribute.RESISTANCE, (byte)1);
		}
		stats.trySync();

	}

	/**
	 * Remove a resistance (value 2) whilst automatically decreasing resistance
	 * to the resistantTo element (value 1), and decreasing weakness to weakTo
	 * element (value 2).
	 * Holy and Dark are accounted for.
	 * @param element
	 */
	public default void removeElementalResistance(ElementalAspect element)
	{
		ElementalStats stats = getElementalStats();
		stats.modifyElement(element, ElementalAttribute.RESISTANCE, (byte)-2);
		stats.modifyElement(element.getWeakTo(), ElementalAttribute.WEAKNESS, (byte)-2);
		if(element != ElementalAspect.DARK && element != ElementalAspect.HOLY)
		{
			stats.modifyElement(element.getResistantTo(), ElementalAttribute.RESISTANCE, (byte)-1);
		}
		stats.trySync();
	}

	public default boolean hasElementalAspect(ElementalAspect element, ElementalAttribute attribute)
	{
		NbtCompound nbt = getElementalStats().getNbtCompound();
		if(nbt != null)
		{
			return nbt.contains(element.toNbtKey());
		}
		return false;
	}

	public default int getElementalAffinity(ElementalAspect element, ElementalAttribute attribute)
	{
		ElementalStats stats = getElementalStats();
		NbtCompound nbt = stats.getNbtCompound();
		if(nbt != null)
		{
			String key = element.toNbtKey();
			return nbt.contains(key) ? (int)nbt.getByte(key) : 0;
		}
		return 0;
	}

	public default Map<ElementalAspect, Integer> getElementalAffinities(ElementalAttribute attribute)
	{
		Map<ElementalAspect, Integer> result = new EnumMap<>(ElementalAspect.class);

		for(ElementalAspect element : ElementalAspect.values())
		{
			int affinity = getElementalAffinity(element, attribute);
			if(affinity > 0)
			{
				result.put(element, affinity);
			}
		}

		return result;
	}

	public default int getWeakResistAffinity(ElementalAspect element)
	{
		return getElementalAffinity(element, ElementalAttribute.RESISTANCE) - getElementalAffinity(element, ElementalAttribute.WEAKNESS);
	}

	public default ElementalAttribute getAttributeForDamage(ElementalAspect element)
	{
		ElementalAttribute attribute;

		if(hasElementalAspect(element, ElementalAttribute.REVIVE))
		{
			attribute = ElementalAttribute.REVIVE;
		}
		else if(hasElementalAspect(element, ElementalAttribute.FATAL))
		{
			attribute = ElementalAttribute.FATAL;
		}
		else if(hasElementalAspect(element, ElementalAttribute.ABSORBTION))
		{
			attribute = ElementalAttribute.ABSORBTION;
		}
		else if(hasElementalAspect(element, ElementalAttribute.IMMUNITY))
		{
			attribute = ElementalAttribute.IMMUNITY;
		}
		else
		{
			attribute = getWeakResistAffinity(element) >= 0 ? ElementalAttribute.RESISTANCE : ElementalAttribute.WEAKNESS;
		}
		return attribute;
	}

	public default void applyToObject(ElementalObject obj)
	{
		apply(obj, 1);
	}

	public default void removeFromObject(ElementalObject obj)
	{
		apply(obj, -1);
	}

	/**
	 * Internal use functions
	 */

	public ElementalStats getElementalStats();

	private void apply(ElementalObject obj, int addOrRemove)
	{
		ElementalStats stats = getElementalStats();
		ElementalStats targetStats = obj.getElementalStats();

		for(ElementalAttribute attribute : ElementalAttribute.values())
		{
			NbtCompound nbt = stats.getNbtCompound();
			if(nbt != null)
			{
				for(String key : nbt.getKeys())
				{
					FMC.LOG.info("\t\tAdjusting by {}", (byte)((nbt.getByte(key) * addOrRemove)));
					targetStats.modifyElement(ElementalAspect.fromNbtKey(key), attribute, (byte)((nbt.getByte(key) * addOrRemove)));
				}
			}
		}
		targetStats.trySync();
	}

	public default void modifyElementalAspectNoSync(ElementalAspect element, ElementalAttribute attribute, int amount)
	{
		ElementalStats stats = getElementalStats();
		stats.modifyElement(element, attribute, (byte)amount);

	}

	public default void addElementalResistanceNoSync(ElementalAspect element, int amount)
	{
		ElementalStats stats = getElementalStats();
		stats.modifyElement(element, ElementalAttribute.RESISTANCE, (byte)2);
		stats.modifyElement(element.getWeakTo(), ElementalAttribute.WEAKNESS, (byte)2);
		if(element != ElementalAspect.DARK && element != ElementalAspect.HOLY)
		{
			stats.modifyElement(element.getResistantTo(), ElementalAttribute.RESISTANCE, (byte)1);
		}
	}
}
