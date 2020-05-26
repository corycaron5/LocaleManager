package me.cory.localization;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class LocaleManager {

	private final HashMap<String, LocaleConfig> locales;
	private final HashMap<UUID, String> playerLocales;
	private String defaultLocale;
	
	/**
	 * Construct the LocaleManager instance
	 * @param defaultLocale The default locale
	 */
	public LocaleManager(String defaultLocale){
		this.defaultLocale = defaultLocale;
		locales = new HashMap<>();
		playerLocales = new HashMap<>();
		registerAllCsvConfigs();
		registerAllYamlConfigs();
		loadPlayerLocales();
	}
	
	/**
	 * Gets the default locale
	 * @return The default locale
	 */
	public String getDefaultLocale(){
		return defaultLocale;
	}
	
	/**
	 * Sets the default locale
	 * @param defaultLocale The new default locale
	 */
	public void setDefaultLocale(String defaultLocale){
		this.defaultLocale = defaultLocale;
	}
	
	/**
	 * Get all locales registered
	 * @return A map of all LocaleConfigs
	 */
	public HashMap<String, LocaleConfig> getLocales(){
		return locales;
	}
	
	/**
	 * List the names of all registered locales
	 * @return A set of all registered locale names
	 */
	public Set<String> listLocales(){
		return locales.keySet();
	}
	
	/**
	 * Gets the localized message
	 * @param locale The locale to get the message
	 * @param key The key to check
	 * @return The localized message
	 */
	public String getMessage(String locale, String key){
		LocaleConfig config = locales.get(locale);
		if(config==null)return null;
		return config.getMessage(key);
	}
	
	/**
	 * Sets the localized message
	 * @param locale The locale to set the message
	 * @param key The key for this message
	 * @param message The localized message
	 * @return The previous message if one existed
	 */
	public String setMessage(String locale, String key, String message){
		LocaleConfig config = locales.get(locale);
		if(config==null)return null;
		return config.setMessage(key, message);
	}
	
	/**
	 * Checks if the specified locale contains a message for the specified key
	 * @param locale Locale to check
	 * @param key Key to check
	 * @return True if the locale contains a message for the key
	 */
	public boolean containsMessage(String locale, String key){
		LocaleConfig config = locales.get(locale);
		if(config==null)return false;
		return config.containsMessage(key);
	}
	
	/**
	 * Checks if the specified locale exists
	 * @param locale Locale to check
	 * @return True if the locale is registered
	 */
	public boolean containsLocale(String locale){
		return locales.containsKey(locale);
	}
	
	/**
	 * Register the specified LocaleConfig object
	 * @param config LocaleConfig to register
	 * @return The previous LocaleConfig if it was already registered
	 */
	public LocaleConfig registerLocale(LocaleConfig config){
		return locales.put(config.getLocaleName(), config);
	}
	
	/**
	 * Unregister the specified LocaleConfig
	 * @param localeName The locale to unregister
	 * @return The LocaleConfig if it was registered
	 */
	public LocaleConfig unregisterLocale(String localeName){
		return locales.remove(localeName);
	}
	
	/**
	 * Register all CSV formatted Locale Configs in the plugin's data folder under the "locales" subdirectory
	 */
	public void registerAllCsvConfigs(){
		FilenameFilter filter = new FilenameFilter(){

			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".csv");
			}
			
		};
		File[] files = new File(JavaPlugin.getProvidingPlugin(LocaleManager.class).getDataFolder(), "locales").listFiles(filter);
		for(File file : files){
			registerLocale(new CsvConfig(file.getName().replace(".csv", "")));
		}
	}
	
	/**
	 * Register all YAML formatted Locale Configs in the plugin's data folder under the "locales" subdirectory
	 */
	public void registerAllYamlConfigs(){
		FilenameFilter filter = new FilenameFilter(){

			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".yml");
			}
			
		};
		File[] files = new File(JavaPlugin.getProvidingPlugin(LocaleManager.class).getDataFolder(), "locales").listFiles(filter);
		for(File file : files){
			registerLocale(new YamlConfig(file.getName().replace(".yml", "")));
		}
	}
	
	/**
	 * Gets the localized message for the specified player or the default if the localized message doesnt exist in that locale
	 * This is the main method that should be used to retrieve messages
	 * @param uuid Player's UUID to check for
	 * @param key The key to the message to retrieve
	 * @return The localized message in the locale the player currently has selected
	 */
	public String getPlayerMessage(UUID uuid, String key){
		if(!containsLocale(playerLocales.get(uuid)))return getMessage(defaultLocale, key);
		else if(containsMessage(playerLocales.get(uuid), key))return getMessage(playerLocales.get(uuid), key);
		else return getMessage(defaultLocale, key);
	}
	
	/**
	 * Gets a players currently selected locale
	 * @param uuid Player's UUID to check
	 * @return The name of the currently selected locale
	 */
	public String getPlayerLocale(UUID uuid){
		return playerLocales.get(uuid);
	}
	
	/**
	 * Sets a players currently selected locale
	 * @param uuid Player's UUID
	 * @param localeName The name of the locale
	 * @return The previous locale
	 */
	public String setPlayerLocale(UUID uuid, String localeName){
		return playerLocales.put(uuid, localeName);
	}
	
	/**
	 * Checks if the player has a locale selected currently
	 * Should only be false if the player has never logged in before
	 * @param uuid Player's UUID to check
	 * @return True if the player has a locale selected
	 */
	public boolean containsPlayer(UUID uuid){
		return playerLocales.containsKey(uuid);
	}
	
	/**
	 * Load all player selected locales from file
	 */
	public void loadPlayerLocales(){
		FileConfiguration config;
		try {
			config = YamlUtil.getConfig("player-locales");
		} catch (IOException e) {
			//LOG IO ERROR MESSAGE HERE
			return;
		}
		for(String string : config.getKeys(false)){
			playerLocales.put(UUID.fromString(string), config.getString(string));
		}
	}

	
	/**
	 * Save all player selected locales to file
	 */
	public void savePlayerLocales(){
		try {
			FileConfiguration config = YamlUtil.getConfig("player-locales");
			for(Entry<UUID, String> entry : playerLocales.entrySet()){
				config.set(entry.getKey().toString(), entry.getValue());
			}
			YamlUtil.saveConfig(config, "player-locales");
		} catch (IOException e) {
			//LOG IO ERROR MESSAGE HERE
			return;
		}
	}
}
