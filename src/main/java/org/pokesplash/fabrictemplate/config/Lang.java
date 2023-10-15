package org.pokesplash.fabrictemplate.config;

import com.google.gson.Gson;
import org.pokesplash.fabrictemplate.FabricTemplate;
import org.pokesplash.fabrictemplate.util.Utils;

import java.util.concurrent.CompletableFuture;

public class Lang {
	private String title;
	private String fillerMaterial;

	public Lang() {
		title = FabricTemplate.MOD_ID;
		fillerMaterial = "minecraft:white_stained_glass_pane";
	}

	public String getTitle() {
		return title;
	}

	public String getFillerMaterial() {
		return fillerMaterial;
	}

	/**
	 * Method to initialize the config.
	 */
	public void init() {
		CompletableFuture<Boolean> futureRead = Utils.readFileAsync(FabricTemplate.BASE_PATH, "lang.json",
				el -> {
					Gson gson = Utils.newGson();
					Lang lang = gson.fromJson(el, Lang.class);
					title = lang.getTitle();
					fillerMaterial = lang.getFillerMaterial();
				});

		if (!futureRead.join()) {
			FabricTemplate.LOGGER.info("No lang.json file found for " + FabricTemplate.MOD_ID + ". Attempting to " +
					"generate " +
					"one.");
			Gson gson = Utils.newGson();
			String data = gson.toJson(this);
			CompletableFuture<Boolean> futureWrite = Utils.writeFileAsync(FabricTemplate.BASE_PATH, "lang.json", data);

			if (!futureWrite.join()) {
				FabricTemplate.LOGGER.fatal("Could not write lang.json for " + FabricTemplate.BASE_PATH + ".");
			}
			return;
		}
		FabricTemplate.LOGGER.info(FabricTemplate.BASE_PATH + " lang file read successfully.");
	}
}
