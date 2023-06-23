package lafolie.fmc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resource.ResourceManager;
import net.minecraft.server.MinecraftServer;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import software.bernie.geckolib.GeckoLib;
import dev.onyxstudios.cca.internal.CardinalComponentsBlock;
import lafolie.fmc.etc.AlBhed;
import lafolie.fmc.util.ServerStatus;

public class FMC implements ModInitializer
{
	public static final String MODID = "FMC";
	public static final String VERSION_STRING = "23.6.0 Biggs"; 
	public static final Logger LOG = LoggerFactory.getLogger(MODID);

	private static ServerStatus serverStatus = ServerStatus.INIT;

	@Override
	public void onInitialize()
	{
		LOG.info("Loaded FMC Core version {}", VERSION_STRING);
		LOG.info(AlBhed.toAlBhed("Welcome to FMC!"));

		// TODO Auto-generated method stub
		// throw new UnsupportedOperationException("Unimplemented method 'onInitialize'");
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
		// ElementalItemTags.clearCache();
		// ElementalEntityTags.clearCache();
	}

	private static void onServerStopping(MinecraftServer server)
	{
		serverStatus = ServerStatus.STOPPING;
	}

	private static void onServerStopped(MinecraftServer server)
	{
		serverStatus = ServerStatus.STOPPED;
		// ElementalItemTags.clearCache();
		// ElementalEntityTags.clearCache();
	}

	private static void onEndDataPackReload(MinecraftServer server, ResourceManager resourceManager, boolean success)
	{
		// ElementalItemTags.clearCache();
		// ElementalEntityTags.clearCache();
	}
}
