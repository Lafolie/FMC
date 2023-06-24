package lafolie.fmc;

import lafolie.fmc.block.CrystalBlock;
import lafolie.fmc.util.FMCIdentifier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item.Settings;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;

public final class FMCBlocks
{
	public static final CrystalBlock CRYSTAL  = new CrystalBlock(FabricBlockSettings.create().strength(4.0f).nonOpaque().sounds(BlockSoundGroup.AMETHYST_BLOCK));
	public static final Block FIRE_CRYSTAL    = new Block(FabricBlockSettings.create().strength(4.0f).nonOpaque().sounds(BlockSoundGroup.AMETHYST_BLOCK));
	public static final Block ICE_CRYSTAL     = new Block(FabricBlockSettings.create().strength(4.0f).nonOpaque().sounds(BlockSoundGroup.AMETHYST_BLOCK));
	public static final Block THUNDER_CRYSTAL = new Block(FabricBlockSettings.create().strength(4.0f).nonOpaque().sounds(BlockSoundGroup.AMETHYST_BLOCK));
	public static final Block WIND_CRYSTAL    = new Block(FabricBlockSettings.create().strength(4.0f).nonOpaque().sounds(BlockSoundGroup.AMETHYST_BLOCK));
	public static final Block WATER_CRYSTAL   = new Block(FabricBlockSettings.create().strength(4.0f).nonOpaque().sounds(BlockSoundGroup.AMETHYST_BLOCK));
	public static final Block EARTH_CRYSTAL   = new Block(FabricBlockSettings.create().strength(4.0f).nonOpaque().sounds(BlockSoundGroup.AMETHYST_BLOCK));
	public static final Block HOLY_CRYSTAL    = new Block(FabricBlockSettings.create().strength(4.0f).nonOpaque().sounds(BlockSoundGroup.AMETHYST_BLOCK));
	public static final Block DARK_CRYSTAL    = new Block(FabricBlockSettings.create().strength(4.0f).nonOpaque().sounds(BlockSoundGroup.AMETHYST_BLOCK));
	public static final Block POISON_CRYSTAL  = new Block(FabricBlockSettings.create().strength(4.0f).nonOpaque().sounds(BlockSoundGroup.AMETHYST_BLOCK));
	public static final Block GRAVITY_CRYSTAL = new Block(FabricBlockSettings.create().strength(4.0f).nonOpaque().sounds(BlockSoundGroup.AMETHYST_BLOCK));
	
	// public static final HomeCrystalBlock HOME_CRYSTAL = new HomeCrystalBlock(FabricBlockSettings.of(Material.METAL).strength(4.0f).nonOpaque().sounds(BlockSoundGroup.AMETHYST_BLOCK).luminance((state) -> 15));
	// public static BlockEntityType<HomeCrystalBlockEntity> HOME_CRYSTAL_ENTITY;
	// public static final CrystalPedestalBlock CRYSTAL_PEDESTAL = new CrystalPedestalBlock(FabricBlockSettings.of(Material.METAL).strength(4.0f));
	// #<block_instance>

	private static void registerBlock(String name, Block block, Settings itemSettings)
	{
		Registry.register(Registries.BLOCK, FMCIdentifier.contentID(name), block);
		Registry.register(Registries.ITEM, FMCIdentifier.contentID(name), new BlockItem(block, itemSettings));
	}

	public static void init()
	{
		registerBlock("crystal_block", CRYSTAL, new FabricItemSettings());
		registerBlock("fire_crystal_block", FIRE_CRYSTAL, new FabricItemSettings());
		registerBlock("ice_crystal_block", ICE_CRYSTAL, new FabricItemSettings());
		registerBlock("thunder_crystal_block", THUNDER_CRYSTAL, new FabricItemSettings());
		registerBlock("wind_crystal_block", WIND_CRYSTAL, new FabricItemSettings());
		registerBlock("water_crystal_block", WATER_CRYSTAL, new FabricItemSettings());
		registerBlock("earth_crystal_block", EARTH_CRYSTAL, new FabricItemSettings());
		registerBlock("holy_crystal_block", HOLY_CRYSTAL, new FabricItemSettings());
		registerBlock("dark_crystal_block", DARK_CRYSTAL, new FabricItemSettings());
		registerBlock("poison_crystal_block", POISON_CRYSTAL, new FabricItemSettings());
		registerBlock("gravity_crystal_block", GRAVITY_CRYSTAL, new FabricItemSettings());
		
		// Registry.register(Registry.BLOCK, FMCIdentifier.contentID("home_crystal_block"), HOME_CRYSTAL);
		// HOME_CRYSTAL_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, FMCIdentifier.contentID("home_crystal_block_entity"), FabricBlockEntityTypeBuilder.create(HomeCrystalBlockEntity::new, HOME_CRYSTAL).build(null));
		
		// Registry.register(Registry.BLOCK, FMCIdentifier.contentID("crystal_pedestal_block"), CRYSTAL_PEDESTAL);
		// #<block_register>

		ItemGroupEvents.modifyEntriesEvent(FMCItemGroup.KEY).register(content ->
		{
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
		//#<block_group>
		});

	}

	@Environment(EnvType.CLIENT)
	public static void initClient()
	{
		// BlockEntityRendererRegistry.register(HOME_CRYSTAL_ENTITY, (BlockEntityRendererFactory.Context rendererDispatcherIn) -> {return new HomeCrystalRenderer();});
	}
}
