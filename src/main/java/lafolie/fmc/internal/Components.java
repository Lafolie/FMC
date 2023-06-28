package lafolie.fmc.internal;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import dev.onyxstudios.cca.api.v3.world.WorldComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.world.WorldComponentInitializer;
import lafolie.fmc.internal.element.ElementalStats;
import lafolie.fmc.internal.element.ElementalStatsComponent;
import lafolie.fmc.util.FMCIdentifier;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

public final class Components implements
	EntityComponentInitializer,
	WorldComponentInitializer
{

	public static final Identifier ELEMENTAL_STATS_ID = FMCIdentifier.componentID(ElementalStats.NBT_KEY);
	// public static final Identifier ELEMENTAL_STATS_ITEM_ID = FMCIdentifier.componentID("elemental_stats_item");

	public static final ComponentKey<ElementalStatsComponent> ELEMENTAL_STATS = ComponentRegistry.getOrCreate(ELEMENTAL_STATS_ID, ElementalStatsComponent.class);

	@Override
	public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry)
	{
		registry.registerFor(LivingEntity.class, ELEMENTAL_STATS, ElementalStatsComponent::new);
		registry.registerForPlayers(ELEMENTAL_STATS, ElementalStatsComponent::new, RespawnCopyStrategy.NEVER_COPY);

	}

	@Override
	public void registerWorldComponentFactories(WorldComponentFactoryRegistry registry)
	{
		// TODO Auto-generated method stub
	}

}
