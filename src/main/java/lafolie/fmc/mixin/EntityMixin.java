package lafolie.fmc.mixin;

import lafolie.fmc.element.ElementalObject;
import lafolie.fmc.internal.Components;
import lafolie.fmc.internal.element.ElementalEntityTags;
import lafolie.fmc.internal.element.ElementalStats;
import lafolie.fmc.internal.element.ElementalStatsComponent;
import net.minecraft.entity.Entity;

public abstract class EntityMixin implements ElementalObject
{

	@Override
	public ElementalStats getElementalStats()
	{
		ElementalStatsComponent component = Components.ELEMENTAL_STATS.get(this);
		ElementalEntityTags.populateElements((Entity)(Object)this);
		return (ElementalStats)component;
	}
	
}
