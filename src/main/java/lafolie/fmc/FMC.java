package lafolie.fmc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.resource.ResourceManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import software.bernie.geckolib.GeckoLib;
import dev.onyxstudios.cca.internal.CardinalComponentsBlock;
import lafolie.fmc.etc.AlBhed;
import lafolie.fmc.internal.element.ElementalEntityTags;
import lafolie.fmc.internal.element.ElementalItemTags;
import lafolie.fmc.util.DumpIDs;
import lafolie.fmc.util.ServerStatus;

public class FMC implements ModInitializer
{
	public static final ModContainer MOD = FabricLoader.getInstance().getModContainer("final-minecraft").get();

	public static final String MODID = "final-minecraft";
	public static final String VERSION_STRING = "23.6.0 Biggs"; 
	public static final Logger LOG = LoggerFactory.getLogger(MODID);

	private static ServerStatus serverStatus = ServerStatus.INIT;

	@Override
	public void onInitialize()
	{
		LOG.info("Loaded FMC Core version {}", VERSION_STRING);
		LOG.info(AlBhed.toAlBhed("Welcome to FMC!"));

		initContent();

		ServerLifecycleEvents.SERVER_STARTING.register(FMC::onServerStarting);
		ServerLifecycleEvents.SERVER_STARTED.register(FMC::onServerStarted);
		ServerLifecycleEvents.SERVER_STOPPING.register(FMC::onServerStopping);
		ServerLifecycleEvents.SERVER_STOPPED.register(FMC::onServerStopped);
		ServerLifecycleEvents.END_DATA_PACK_RELOAD.register(FMC::onEndDataPackReload);


		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(CommandManager.literal("dumpids")
			.executes(context ->
			{
				context.getSource().sendMessage(Text.literal("Dumping IDs..."));
				DumpIDs.exec();
				context.getSource().sendMessage(Text.literal("Finished."));
				return 0;
			})));
	}

	private void initContent()
	{
		FMCItemGroup.init();
		FMCBlocks.init();
		FMCItems.init();
	}

	// -------------------------------------------------------------------------
	// Server Callbacks
	// -------------------------------------------------------------------------

	private static void onServerStarting(MinecraftServer server)
	{
		serverStatus = ServerStatus.STARTING;
	}
	private static void onServerStarted(MinecraftServer server)

	{
		serverStatus = ServerStatus.STARTED;
		ElementalItemTags.clearCache();
		ElementalEntityTags.clearCache();
	}

	private static void onServerStopping(MinecraftServer server)
	{
		serverStatus = ServerStatus.STOPPING;
	}

	private static void onServerStopped(MinecraftServer server)
	{
		serverStatus = ServerStatus.STOPPED;
		ElementalItemTags.clearCache();
		ElementalEntityTags.clearCache();
	}

	private static void onEndDataPackReload(MinecraftServer server, ResourceManager resourceManager, boolean success)
	{
		ElementalItemTags.clearCache();
		ElementalEntityTags.clearCache();
	}
}
