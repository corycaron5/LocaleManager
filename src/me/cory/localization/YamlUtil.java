package me.cory.localization;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class YamlUtil {
	
	private YamlUtil(){}
	
	/**
	 * Gets the YAML config at the specified location
	 * Creates the config if it does not exist
	 * @param name Name of the config
	 * @param directory Folder where the config exists
	 * @return Returns the FileConfiguration object for this config
	 * @throws IOException If unable to read or create the specified config
	 */
	public static FileConfiguration getDistantConfig(String name, URI directory)
			throws IOException {
		File dir = new File(directory);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
		File file = new File(dir, name + ".yml");
		if (!file.exists()) {
			file.createNewFile();
		}
		return YamlConfiguration.loadConfiguration(file);
	}

	/**
	 * Saves the YAML config to the specified location
	 * @param config FileConfiguration object to save to file
	 * @param name Name of the config
	 * @param directory Folder where the config exists
	 * @throws IOException If unable to save the specified config to file
	 */
	public static void saveDistantConfig(FileConfiguration config, String name, URI directory) throws IOException {
		File dir = new File(directory);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
		File file = new File(dir, name + ".yml");
		config.save(file);
	}
	
	/**
	 * Gets the YAML config in the specified subdirectory
	 * Creates the config if it does not exist
	 * @param name Name of the config
	 * @param subdirectory Subdirectory under the plugin data folder
	 * @return Returns the FileConfiguration object for this config
	 * @throws IOException If unable to read or create the specified config
	 */
	public static FileConfiguration getConfig(String name, String subdirectory)throws IOException {
		File dir = new File(Main.getPlugin(Main.class).getDataFolder() + File.separator + subdirectory);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
		File file = new File(dir, name + ".yml");
		if (!file.exists()) {
			file.createNewFile();
		}
		return YamlConfiguration.loadConfiguration(file);
	}

	/**
	 * Saves the YAML config to the specified subdirectory
	 * @param config FileConfiguration object to save to file
	 * @param name Name of the config
	 * @param subdirectory Subdirectory under the plugin data folder
	 * @throws IOException If unable to save the specified config to file
	 */
	public static void saveConfig(FileConfiguration config, String name, String subdirectory) throws IOException {
		File dir = new File(Main.getPlugin(Main.class).getDataFolder() + File.separator + subdirectory);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
		File file = new File(dir, name + ".yml");
		config.save(file);
	}

	/**
	 * Gets the YAML config in the base plugin data folder
	 * Creates the config if it does not exist
	 * @param name Name of the config
	 * @return Returns the FileConfiguration object for this config
	 * @throws IOException If unable to read or create the specified config
	 */
	public static FileConfiguration getConfig(String name) throws IOException {
		File dir = Main.getPlugin(Main.class).getDataFolder();
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
		File file = new File(Main.getPlugin(Main.class).getDataFolder(), name + ".yml");
		if (!file.exists()) {
			file.createNewFile();
		}
		return YamlConfiguration.loadConfiguration(file);
	}

	/**
	 * Saves the YAML config to the base plugin data folder
	 * @param config FileConfiguration object to save to file
	 * @param name Name of the config
	 * @throws IOException If unable to save the specified config to file
	 */
	public static void saveConfig(FileConfiguration config, String name) throws IOException {
		File file = new File(Main.getPlugin(Main.class).getDataFolder(), name + ".yml");
		config.save(file);
	}
	
	/**
	 * Gets the YAML config in the another plugins data folder under a subdirectory
	 * Creates the config if it does not exist
	 * @param plugin Other plugin
	 * @param name Name of the config
	 * @param subdirectory Subdirectory under the plugin data folder
	 * @return Returns the FileConfiguration object for this config
	 * @throws IOException If unable to read or create the specified config
	 */
	public static FileConfiguration getConfig(JavaPlugin plugin, String name, String subdirectory)throws IOException {
		File dir = new File(plugin.getDataFolder() + File.separator + subdirectory);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
		File file = new File(dir, name + ".yml");
		if (!file.exists()) {
			file.createNewFile();
		}
		return YamlConfiguration.loadConfiguration(file);
	}

	/**
	 * Saves the YAML config to the another plugins data folder under a subdirectory
	 * @param config FileConfiguration object to save to file
	 * @param plugin Other plugin
	 * @param name Name of the config
	 * @param subdirectory Subdirectory under the plugin data folder
	 * @throws IOException If unable to save the specified config to file
	 */
	public static void saveConfig(FileConfiguration config, JavaPlugin plugin, String name, String subdirectory) throws IOException {
		File dir = new File(plugin.getDataFolder() + File.separator + subdirectory);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
		File file = new File(dir, name + ".yml");
		config.save(file);
	}

	/**
	 * Gets the YAML config in the another plugins base data folder
	 * Creates the config if it does not exist
	 * @param plugin Other plugin
	 * @param name Name of the config
	 * @return Returns the FileConfiguration object for this config
	 * @throws IOException If unable to read or create the specified config
	 */
	public static FileConfiguration getConfig(JavaPlugin plugin, String name) throws IOException {
		File dir = plugin.getDataFolder();
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
		File file = new File(Main.getPlugin(Main.class).getDataFolder(), name + ".yml");
		if (!file.exists()) {
			file.createNewFile();
		}
		return YamlConfiguration.loadConfiguration(file);
	}

	/**
	 * Saves the YAML config to the another plugins base data folder
	 * @param config FileConfiguration object to save to file
	 * @param plugin Other plugin
	 * @param name Name of the config
	 * @throws IOException If unable to save the specified config to file
	 */
	public static void saveConfig(FileConfiguration config, JavaPlugin plugin, String name) throws IOException {
		File file = new File(plugin.getDataFolder(), name + ".yml");
		config.save(file);
	}
	
}
