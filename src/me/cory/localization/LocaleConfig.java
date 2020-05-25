package me.cory.localization;

import java.util.HashMap;

public abstract class LocaleConfig {

	private final String localeName;
	private final HashMap<String, String> messages;
	
	/**
	 * Constructor for abstract class, to be called from any extending class
	 * @param localeName The locale name
	 */
	protected LocaleConfig(String localeName){
		this.messages = new HashMap<>();
		this.localeName = localeName;
		loadMessages();
	}
	
	/**
	 * Gets all registered messages
	 * @return Returns the HashMap of all registered messages
	 */
	public HashMap<String, String> getMessages(){
		return messages;
	}
	
	/**
	 * Get the localized message for the specified key
	 * @param key The key: Should be the same for each LocaleConfig
	 * @return The localized message
	 */
	public String getMessage(String key){
		return messages.get(key);
	}
	
	/**
	 * Set the localized message for the specified key
	 * @param key The key: Should be the same for each LocaleConfig
	 * @param message The localized message
	 * @return The previous message if one exists
	 */
	public String setMessage(String key, String message){
		return messages.put(key, message);
	}
	
	/**
	 * Checks if a localized message exists for the specified key
	 * @param key The key to check
	 * @return Returns true if this LocaleConfig has a message for the specified key
	 */
	public boolean containsKey(String key){
		return messages.containsKey(key);
	}
	
	/**
	 * Checks if this localized message exists
	 * Note: Messages contained in the config are not ChatColor translated
	 * @param message The message to check
	 * @return Returns true if this LocaleConfig has the specified message
	 */
	public boolean containsMessage(String message){
		return messages.containsValue(message);
	}
	
	/**
	 * Gets the name of this locale
	 * @return The name of this locale
	 */
	public String getLocaleName(){
		return localeName;
	}
	
	//Abstract methods that must be implemented by extending class
	public abstract void loadMessages();
	public abstract void saveMessages();
}
