package lafolie.fmc.element;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public enum ElementalAspect
{
	NONE("n"),
	FIRE("f"),
	ICE("i"),
	THUNDER("t"),
	WIND("w"),
	WATER("u"),
	EARTH("e"),
	HOLY("h"),
	DARK("d"),
	POISON("p"),
	GRAVITY("g");

	private String key;

	private static final Map<ElementalAspect, ElementalAspect> WEAK = new EnumMap<>(ElementalAspect.class);
	private static final Map<ElementalAspect, ElementalAspect> RESIST = new EnumMap<>(ElementalAspect.class);
	private static final Map<ElementalAspect, String> LANG_KEYS = new EnumMap<>(ElementalAspect.class);
	private static final Map<String, ElementalAspect> NBT_KEYS = new HashMap<>();

	static
	{
		// FFXI system
		/*
		 * Fire melts Ice
		 * Ice blocks Wind
		 * Wind erodes Earth
		 * Earth grounds Thunder
		 * Thunder shocks Water
		 * Water douses Fire
		 * Light illuminates Dark
		 * Dark eclipses Light
		 */
		
		WEAK.put(ElementalAspect.NONE, ElementalAspect.NONE);
		WEAK.put(ElementalAspect.FIRE, ElementalAspect.WATER);
		WEAK.put(ElementalAspect.ICE, ElementalAspect.FIRE);
		WEAK.put(ElementalAspect.THUNDER, ElementalAspect.EARTH);
		WEAK.put(ElementalAspect.WIND, ElementalAspect.ICE);
		WEAK.put(ElementalAspect.WATER, ElementalAspect.THUNDER);
		WEAK.put(ElementalAspect.EARTH, ElementalAspect.WIND);
		WEAK.put(ElementalAspect.HOLY, ElementalAspect.DARK);
		WEAK.put(ElementalAspect.DARK, ElementalAspect.HOLY);
		WEAK.put(ElementalAspect.POISON, ElementalAspect.NONE);
		WEAK.put(ElementalAspect.GRAVITY, ElementalAspect.NONE);

		RESIST.put(ElementalAspect.NONE, ElementalAspect.NONE);
		RESIST.put(ElementalAspect.FIRE, ElementalAspect.ICE);
		RESIST.put(ElementalAspect.ICE, ElementalAspect.WIND);
		RESIST.put(ElementalAspect.THUNDER, ElementalAspect.WATER);
		RESIST.put(ElementalAspect.WIND, ElementalAspect.EARTH);
		RESIST.put(ElementalAspect.WATER, ElementalAspect.ICE);
		RESIST.put(ElementalAspect.EARTH, ElementalAspect.THUNDER);
		RESIST.put(ElementalAspect.HOLY, ElementalAspect.DARK);
		RESIST.put(ElementalAspect.DARK, ElementalAspect.HOLY);
		RESIST.put(ElementalAspect.POISON, ElementalAspect.NONE);
		RESIST.put(ElementalAspect.GRAVITY, ElementalAspect.NONE);

		LANG_KEYS.put(ElementalAspect.NONE, "fmc.core.element.tooltip.none");
		LANG_KEYS.put(ElementalAspect.FIRE, "fmc.core.element.tooltip.fire");
		LANG_KEYS.put(ElementalAspect.ICE, "fmc.core.element.tooltip.ice");
		LANG_KEYS.put(ElementalAspect.THUNDER, "fmc.core.element.tooltip.lightning");
		LANG_KEYS.put(ElementalAspect.WIND, "fmc.core.element.tooltip.wind");
		LANG_KEYS.put(ElementalAspect.WATER, "fmc.core.element.tooltip.water");
		LANG_KEYS.put(ElementalAspect.EARTH, "fmc.core.element.tooltip.earth");
		LANG_KEYS.put(ElementalAspect.HOLY, "fmc.core.element.tooltip.holy");
		LANG_KEYS.put(ElementalAspect.DARK, "fmc.core.element.tooltip.dark");
		LANG_KEYS.put(ElementalAspect.POISON, "fmc.core.element.tooltip.poison");
		LANG_KEYS.put(ElementalAspect.GRAVITY, "fmc.core.element.tooltip.gravity");
	}

	private ElementalAspect(String key)
	{
		this.key = key;
	}
}