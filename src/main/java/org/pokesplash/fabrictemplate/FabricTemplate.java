package org.pokesplash.fabrictemplate;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pokesplash.fabrictemplate.command.CommandHandler;
import org.pokesplash.fabrictemplate.config.Config;
import org.pokesplash.fabrictemplate.config.Lang;

public class FabricTemplate implements ModInitializer {
	public static final String MOD_ID = "FabricTemplate";
	public static final String BASE_PATH = "/config/" + MOD_ID + "/";
	public static final Logger LOGGER = LogManager.getLogger();
	public static final Config config = new Config();
	public static final Lang lang = new Lang();

	/**
	 * Runs the mod initializer.
	 */
	@Override
	public void onInitialize() {
		CommandRegistrationCallback.EVENT.register(CommandHandler::registerCommands);
		load();
	}

	public static void load() {
		config.init();
		lang.init();
	}
}
