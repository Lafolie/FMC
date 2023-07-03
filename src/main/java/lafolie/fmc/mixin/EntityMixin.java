package lafolie.fmc.mixin;

import org.spongepowered.asm.mixin.Mixin;

import lafolie.fmc.element.ElementalObject;
import lafolie.fmc.internal.Components;
import lafolie.fmc.internal.element.ElementalStats;
import net.minecraft.entity.Entity;

@Mixin(Entity.class)
public abstract class EntityMixin implements ElementalObject
{
	@Override
	public ElementalStats getElementalStats()
	{
		return (ElementalStats)Components.ELEMENTAL_STATS.get(this);
	}
}
