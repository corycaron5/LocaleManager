package me.cory.localization;

import java.io.IOException;
import java.util.Map.Entry;

import org.bukkit.configuration.file.FileConfiguration;

public class YamlConfig extends LocaleConfig{

	/**
	 * Constructor for YAML formatted Locale Config
	 * @param localeName Name of the locale
	 */
	public YamlConfig(String localeName) {
		super(localeName);
	}

	/**
	 * Called from super class when constructed
	 * Loads all localized messages from CSV file
	 */
	@Override
	public void loadMessages() {
		FileConfiguration config;
		try {
			config = YamlUtil.getConfig(getLocaleName(), "locales");
		} catch (IOException e) {
			//LOG IO ERROR MESSAGE HERE
			return;
		}
		for(String string : config.getKeys(true)){
			setMessage(string, config.getString(string));
		}
	}

	/**
	 * Called from LocaleManager in case messages are changed at runtime
	 * Saves all localized messages to CSV file
	 */
	@Override
	public void saveMessages() {
		FileConfiguration config;
		try {
			config = YamlUtil.getConfig(getLocaleName(), "locales");
			for(Entry<String, String> entry : getMessages().entrySet()){
				config.set(entry.getKey(), entry.getValue());
			}
			YamlUtil.saveConfig(config, getLocaleName(), "locales");
		} catch (IOException e1) {
			//LOG IO ERROR MESSAGE HERE
			return;
		}
	}

}
