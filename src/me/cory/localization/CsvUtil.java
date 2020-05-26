package me.cory.localization;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Util class for loading CSV configs
 * @author Cory Caron
 */
public final class CsvUtil {

	private CsvUtil(){}
	
	/**
	 * Gets the CSV config at the specified location
	 * Creates the config if it does not exist
	 * @param name Name of the config
	 * @param directory Folder where the config exists
	 * @return Returns a list of all lines in the config
	 * @throws IOException If unable to read or create the specified config
	 */
	public static List<String> getDistantConfig(String name, URI directory)throws IOException {
		File dir = new File(directory);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
		File file = new File(dir, name + ".csv");
		if (!file.exists()) {
			file.createNewFile();
		}
		return loadConfig(file);
	}

	/**
	 * Saves the CSV config to the specified location
	 * @param messages List of all lines to be written to the config
	 * @param name Name of the config
	 * @param directory Folder where the config exists
	 * @throws IOException If unable to save the specified config to file
	 */
	public static void saveDistantConfig(List<String> messages, String name, URI directory) throws IOException {
		File dir = new File(directory);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
		File file = new File(dir, name + ".csv");
		writeConfig(file, messages);
	}
	
	/**
	 * Gets the CSV config in the specified subdirectory
	 * Creates the config if it does not exist
	 * @param name Name of the config
	 * @param subdirectory Subdirectory under the plugin data folder
	 * @return Returns a list of all lines in the config
	 * @throws IOException If unable to read or create the specified config
	 */
	public static List<String> getConfig(String name, String subdirectory)throws IOException {
		File dir = new File(JavaPlugin.getProvidingPlugin(CsvUtil.class).getDataFolder() + File.separator + subdirectory);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
		File file = new File(dir, name + ".csv");
		if (!file.exists()) {
			file.createNewFile();
		}
		return loadConfig(file);
	}

	/**
	 * Saves the CSV config to the specified subdirectory
	 * @param messages List of all lines to be written to the config
	 * @param name Name of the config
	 * @param subdirectory Subdirectory under the plugin data folder
	 * @throws IOException If unable to save the specified config to file
	 */
	public static void saveConfig(List<String> messages, String name, String subdirectory) throws IOException {
		File dir = new File(JavaPlugin.getProvidingPlugin(CsvUtil.class).getDataFolder() + File.separator + subdirectory);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
		File file = new File(dir, name + ".csv");
		writeConfig(file, messages);
	}

	/**
	 * Gets the CSV config in the base plugin data folder
	 * Creates the config if it does not exist
	 * @param name Name of the config
	 * @return Returns a list of all lines in the config
	 * @throws IOException If unable to read or create the specified config
	 */
	public static List<String> getConfig(String name) throws IOException {
		File dir = JavaPlugin.getProvidingPlugin(CsvUtil.class).getDataFolder();
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
		File file = new File(JavaPlugin.getProvidingPlugin(CsvUtil.class).getDataFolder(), name + ".csv");
		if (!file.exists()) {
			file.createNewFile();
		}
		return loadConfig(file);
	}

	/**
	 * Saves the CSV config to the base plugin data folder
	 * @param messages List of all lines to be written to the config
	 * @param name Name of the config
	 * @throws IOException If unable to save the specified config to file
	 */
	public static void saveConfig(List<String> messages, String name) throws IOException {
		File file = new File(JavaPlugin.getProvidingPlugin(CsvUtil.class).getDataFolder(), name + ".csv");
		writeConfig(file, messages);
	}
	
	/**
	 * Gets the CSV config in the another plugins data folder under a subdirectory
	 * Creates the config if it does not exist
	 * @param plugin Other plugin
	 * @param name Name of the config
	 * @param subdirectory Subdirectory under the plugin data folder
	 * @return Returns a list of all lines in the config
	 * @throws IOException If unable to read or create the specified config
	 */
	public static List<String> getConfig(JavaPlugin plugin, String name, String subdirectory)throws IOException {
		File dir = new File(plugin.getDataFolder() + File.separator + subdirectory);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
		File file = new File(dir, name + ".csv");
		if (!file.exists()) {
			file.createNewFile();
		}
		return loadConfig(file);
	}

	/**
	 * Saves the CSV config to the another plugins data folder under a subdirectory
	 * @param messages List of all lines to be written to the config
	 * @param plugin Other plugin
	 * @param name Name of the config
	 * @param subdirectory Subdirectory under the plugin data folder
	 * @throws IOException If unable to save the specified config to file
	 */
	public static void saveConfig(List<String> messages, JavaPlugin plugin, String name, String subdirectory) throws IOException {
		File dir = new File(plugin.getDataFolder() + File.separator + subdirectory);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
		File file = new File(dir, name + ".csv");
		writeConfig(file, messages);
	}

	/**
	 * Gets the CSV config in the another plugins base data folder
	 * Creates the config if it does not exist
	 * @param plugin Other plugin
	 * @param name Name of the config
	 * @return Returns a list of all lines in the config
	 * @throws IOException If unable to read or create the specified config
	 */
	public static List<String> getConfig(JavaPlugin plugin, String name) throws IOException {
		File dir = plugin.getDataFolder();
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
		File file = new File(JavaPlugin.getProvidingPlugin(CsvUtil.class).getDataFolder(), name + ".csv");
		if (!file.exists()) {
			file.createNewFile();
		}
		return loadConfig(file);
	}

	/**
	 * Saves the CSV config to the another plugins base data folder
	 * @param messages List of all lines to be written to the config
	 * @param plugin Other plugin
	 * @param name Name of the config
	 * @throws IOException If unable to save the specified config to file
	 */
	public static void saveConfig(List<String> messages, JavaPlugin plugin, String name) throws IOException {
		File file = new File(plugin.getDataFolder(), name + ".csv");
		writeConfig(file, messages);
	}
	
	/**
	 * Write all lines to file
	 * @param file File to write to
	 * @param lines Lines to be written
	 * @throws IOException If unable to write lines to file
	 */
	public static void writeConfig(File file, List<String> lines) throws IOException{
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		for(String string : lines){
			writer.write(string);
			writer.newLine();
		}
		writer.close();
	}
	
	/**
	 * Read all lines from file
	 * @param file File to read from
	 * @return List of all lines from the file
	 * @throws IOException If unable to read lines from file
	 */
	public static List<String> loadConfig(File file) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(file));
		ArrayList<String> lines = new ArrayList<>();
		String line;
		while((line = reader.readLine()) != null){
			lines.add(line);
		}
		reader.close();
		return lines;
	}
	
	/**
	 * Converts all lines from Map to CSV format
	 * @param messages Map of all key:message pairs
	 * @return Returns a list of all lines in CSV format
	 */
	public static List<String> convertToCsv(Map<String, String> messages){
		ArrayList<String> lines = new ArrayList<>();
		for(Entry<String, String> entry : messages.entrySet()){
			lines.add(entry.getKey() + "," + entry.getValue());
		}
		return lines;
	}
	
	/**
	 * Converts all lines from CSV to Map format
	 * @param messages List of all messages in CSV format
	 * @return Returns a Map of all key:message pairs
	 */
	public static Map<String, String> convertToMap(List<String> messages){
		HashMap<String, String> map = new HashMap<>();
		for(String string : messages){
			String[] s = string.split(",");
			if(s.length!=2)continue;
			map.put(s[0], s[1]);
		}
		return map;
	}
}
