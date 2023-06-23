package lafolie.fmc;

import lafolie.fmc.item.CrystalItem;
import lafolie.fmc.item.CrystalShardItem;
import lafolie.fmc.util.FMCIdentifier;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;

public final class FMCItems
{
	public static final CrystalShardItem CRYSTAL_SHARD = new CrystalShardItem(new FabricItemSettings());
	public static final CrystalItem CRYSTAL = new CrystalItem(new FabricItemSettings());
	public static final Item FIRE_CRYSTAL = new Item(new FabricItemSettings());
	public static final Item ICE_CRYSTAL = new Item(new FabricItemSettings());
	public static final Item THUNDER_CRYSTAL = new Item(new FabricItemSettings());
	public static final Item WIND_CRYSTAL = new Item(new FabricItemSettings());
	public static final Item WATER_CRYSTAL = new Item(new FabricItemSettings());
	public static final Item EARTH_CRYSTAL = new Item(new FabricItemSettings());
	public static final Item HOLY_CRYSTAL = new Item(new FabricItemSettings());
	public static final Item DARK_CRYSTAL = new Item(new FabricItemSettings());
	public static final Item POISON_CRYSTAL = new Item(new FabricItemSettings());
	public static final Item GRAVITY_CRYSTAL = new Item(new FabricItemSettings());
	// #<item_instance>



	private static void registerItem(String name, Item item)
	{
		Registry.register(Registries.ITEM, FMCIdentifier.contentID(name), item);
	}

	public static final void init()
	{
		registerItem("crystal_shard_item", CRYSTAL_SHARD);
		registerItem("crystal_item", CRYSTAL);
		registerItem("fire_crystal_item", FIRE_CRYSTAL);
		registerItem("ice_crystal_item", ICE_CRYSTAL);
		registerItem("thunder_crystal_item", THUNDER_CRYSTAL);
		registerItem("wind_crystal_item", WIND_CRYSTAL);
		registerItem("water_crystal_item", WATER_CRYSTAL);
		registerItem("earth_crystal_item", EARTH_CRYSTAL);
		registerItem("holy_crystal_item", HOLY_CRYSTAL);
		registerItem("dark_crystal_item", DARK_CRYSTAL);
		registerItem("poison_crystal_item", POISON_CRYSTAL);
		registerItem("gravity_crystal_item", GRAVITY_CRYSTAL);
		// #<item_register>

		ItemGroupEvents.modifyEntriesEvent(FMCItemGroup.KEY).register(content ->
		{
			content.add(CRYSTAL_SHARD);
			content.add(CRYSTAL);
			content.add(FIRE_CRYSTAL);
			content.add(ICE_CRYSTAL);
			content.add(THUNDER_CRYSTAL);
			content.add(WIND_CRYSTAL);
			content.add(WATER_CRYSTAL);
			content.add(EARTH_CRYSTAL);
			content.add(HOLY_CRYSTAL);
			content.add(DARK_CRYSTAL);
			content.add(POISON_CRYSTAL);
			content.add(GRAVITY_CRYSTAL);
			// #<item_group>
		});
	}
}
