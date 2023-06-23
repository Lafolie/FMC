package lafolie.fmc;

import lafolie.fmc.util.FMCIdentifier;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;

public final class FMCItemGroup
{
	public static final RegistryKey<ItemGroup> KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, FMCIdentifier.groupID("fmc"));
	public static final ItemGroup GROUP = FabricItemGroup.builder()
			.icon(() -> new ItemStack(FMCItems.CRYSTAL))
			.displayName(Text.of("FMC Items"))
			.build();

	public static final void init()
	{
		Registry.register(Registries.ITEM_GROUP, KEY, GROUP);
	}
}
