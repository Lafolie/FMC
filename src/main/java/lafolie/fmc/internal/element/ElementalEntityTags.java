package lafolie.fmc.internal.element;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimap;

import lafolie.fmc.FMC;
import lafolie.fmc.element.ElementalAspect;
import lafolie.fmc.element.ElementalAttribute;
import lafolie.fmc.element.ElementalObject;
import lafolie.fmc.util.FMCIdentifier;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;

public final class ElementalEntityTags
{
	private static class ElementTagEntry
	{
		public ElementalAspect element;
		public TagKey<EntityType<?>> tag;

		public ElementTagEntry(ElementalAspect element, TagKey<EntityType<?>> tag)
		{
			this.element = element;
			this.tag = tag;
		}
	}

	private static ListMultimap<RegistryEntry.Reference<EntityType<?>>, ElementalAspect> WEAKRESIST_CACHE = ArrayListMultimap.create();
	private static ListMultimap<RegistryEntry.Reference<EntityType<?>>, ElementalAspect> ABSORB_CACHE = ArrayListMultimap.create();
	private static ListMultimap<RegistryEntry.Reference<EntityType<?>>, ElementalAspect> IMMUNITY_CACHE = ArrayListMultimap.create();
	private static ListMultimap<RegistryEntry.Reference<EntityType<?>>, ElementalAspect> FATAL_CACHE = ArrayListMultimap.create();
	private static ListMultimap<RegistryEntry.Reference<EntityType<?>>, ElementalAspect> REVIVE_CACHE = ArrayListMultimap.create();

	private static final ListMultimap<ElementalAttribute, ElementTagEntry> TAGS = ArrayListMultimap.create();

	static
	{
		for(ElementalAttribute attribute : ElementalAttribute.values())
		{
			String attributeID = attribute.toNbtKey();
			if(attribute == ElementalAttribute.WEAKNESS)
			{
				continue;
			}

			for(ElementalAspect element : ElementalAspect.values())
			{
				String id;
				if(attribute == ElementalAttribute.RESISTANCE)
				{
					id = String.format(Locale.ROOT, "%s_elemental_entities", element.toNbtKey());
				}
				else
				{
					id = String.format(Locale.ROOT, "%s_%s_elemental_entities", attributeID, element.toNbtKey());
				}

				TagKey<EntityType<?>> tag = TagKey.of(RegistryKeys.ENTITY_TYPE, FMCIdentifier.commonTagID(id));
				TAGS.put(attribute, new ElementTagEntry(element, tag));
				FMC.LOG.info("Registered element tag {}", id);
			}
		}
	}

	public static void clearCache()
	{
		WEAKRESIST_CACHE.clear();
		IMMUNITY_CACHE.clear();
		ABSORB_CACHE.clear();
		FATAL_CACHE.clear();
		REVIVE_CACHE.clear();
	}

	private static List<ElementalAspect> findTags(RegistryEntry.Reference<EntityType<?>> entityEntry, ElementalAttribute attribute)
	{
		List<ElementalAspect> result = new ArrayList<>();
		List<ElementTagEntry> list = TAGS.get(attribute);

		for(ElementTagEntry entry : list)
		{
			if(entityEntry.isIn(entry.tag))
			{
				result.add(entry.element);
			}
		}

		return result;
	}

	private static void populate(
		ElementalObject obj,
		RegistryEntry.Reference<EntityType<?>> ref,
		Multimap<RegistryEntry.Reference<EntityType<?>>, ElementalAspect> cache,
		ElementalAttribute attribute)
	{
		if(!cache.containsKey(ref))
		{
			cache.putAll(ref, findTags(ref, attribute));
		}
		
		for(ElementalAspect element : cache.get(ref))
		{
			//TODO: set amount to configured value
			obj.modifyElementalAspectNoSync(element, attribute, 1);
		}
	}

	public static void populateElements(Entity entity)
	{
		RegistryEntry.Reference<EntityType<?>> ref = (RegistryEntry.Reference<EntityType<?>>)Registries.ENTITY_TYPE.getEntry(entity.getType());
		ElementalObject obj = (ElementalObject)entity;

		populate(obj, ref, WEAKRESIST_CACHE, ElementalAttribute.RESISTANCE);
		populate(obj, ref, ABSORB_CACHE, ElementalAttribute.ABSORBTION);
		populate(obj, ref, IMMUNITY_CACHE, ElementalAttribute.IMMUNITY);
		populate(obj, ref, FATAL_CACHE, ElementalAttribute.FATAL);
		populate(obj, ref, REVIVE_CACHE, ElementalAttribute.REVIVE);
	}
}
