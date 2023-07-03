package lafolie.fmc.internal.element;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import lafolie.fmc.FMC;
import lafolie.fmc.element.ElementalAspect;
import lafolie.fmc.element.ElementalAttribute;
import lafolie.fmc.element.ElementalObject;
import lafolie.fmc.util.FMCIdentifier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;

public final class ElementalItemTags
{
	private static Map<RegistryEntry.Reference<Item>, ElementalAspect> CACHE = new HashMap<>();
	private static final Map<ElementalAspect, TagKey<Item>> TAGS = new EnumMap<>(ElementalAspect.class);

	static
	{
		for(ElementalAspect element : ElementalAspect.values())
		{
			String id = element.toNbtKey().concat("_elemental_items");
			TAGS.put(element, TagKey.of(RegistryKeys.ITEM, FMCIdentifier.commonTagID(id)));
		}
	}

	private static ElementalAspect findTag(RegistryEntry.Reference<Item> itemEntry)
	{
		for(Map.Entry<ElementalAspect, TagKey<Item>> entry : TAGS.entrySet())
		{
			if(itemEntry.isIn(entry.getValue()))
			{
				return entry.getKey();
			}
		}

		return null;
	}

	public static void clearCache()
	{
		CACHE.clear();
	}

	public static void populateElements(ItemStack stack)
	{
		RegistryEntry.Reference<Item> ref = (RegistryEntry.Reference<Item>)Registries.ITEM.getEntry(stack.getItem());
		if(!CACHE.containsKey(ref))
		{
			ElementalAspect element = findTag(ref);
			CACHE.put(ref, element != null ? element : ElementalAspect.NONE);
		}

		ElementalAspect element = CACHE.get(ref);
		if(element != ElementalAspect.NONE)
		{
			// FMC.LOG.info("{} is {}", stack.toString(), element.toString());
			ElementalObject obj = (ElementalObject)(Object)stack;
			obj.modifyElementalAspectNoSync(element, ElementalAttribute.RESISTANCE, 1); //TODO: update value
		}
	}

	public static ElementalAspect getItemElementalTag(RegistryEntry.Reference<Item> itemEntry)
	{
		if(!CACHE.containsKey(itemEntry))
		{
			ElementalAspect result = findTag(itemEntry);
			if(result != null)
			{
				// datapacks are loaded, so use the cache
				CACHE.put(itemEntry, result);
			}
			else
			{
				// datapacks not loaded, return none
				return ElementalAspect.NONE;
			}
		}
		return CACHE.get(itemEntry);
	}
}
