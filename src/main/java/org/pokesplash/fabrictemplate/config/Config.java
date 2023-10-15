package org.pokesplash.fabrictemplate.config;

import com.google.gson.Gson;
import net.minecraft.world.gen.CountConfig;
import org.pokesplash.fabrictemplate.FabricTemplate;
import org.pokesplash.fabrictemplate.util.Utils;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class Config {
	private boolean isExample;

	public Config() {
		isExample = true;
	}

	public void init() {
		CompletableFuture<Boolean> futureRead = Utils.readFileAsync("/config/suffixadvancements/",
				"config.json", el -> {
					Gson gson = Utils.newGson();
					Config cfg = gson.fromJson(el, Config.class);
					isExample = cfg.isExample();
				});

		if (!futureRead.join()) {
			FabricTemplate.LOGGER.info("No config.json file found for SuffixAdvancements. Attempting to generate " +
					"one");
			Gson gson = Utils.newGson();
			String data = gson.toJson(this);
			CompletableFuture<Boolean> futureWrite = Utils.writeFileAsync("/config/suffixadvancements/",
					"config.json", data);

			if (!futureWrite.join()) {
				FabricTemplate.LOGGER.fatal("Could not write config for GTS.");
			}
			return;
		}
		FabricTemplate.LOGGER.info("SuffixAdvancements config file read successfully");
	}

	public boolean isExample() {
		return isExample;
	}
}
