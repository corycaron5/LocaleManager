package me.cory.localization;

import java.io.IOException;
import java.util.Map.Entry;

public class CsvConfig extends LocaleConfig{

	/**
	 * Constructor for CSV formatted Locale Config
	 * @param localeName Name of the locale
	 */
	public CsvConfig(String localeName) {
		super(localeName);
	}

	/**
	 * Called from super class when constructed
	 * Loads all localized messages from CSV file
	 */
	@Override
	public void loadMessages() {
		try {
			for(Entry<String, String> entry : CsvUtil.convertToMap(CsvUtil.getConfig(getLocaleName(), "locales")).entrySet()){
				setMessage(entry.getKey(), entry.getValue());
			}
		} catch (IOException e) {
			//LOG IO ERROR MESSAGE HERE
			return;
		}
		
	}

	/**
	 * Called from LocaleManager in case messages are changed at runtime
	 * Saves all localized messages to CSV file
	 */
	@Override
	public void saveMessages() {
		try {
			CsvUtil.saveConfig(CsvUtil.convertToCsv(getMessages()), getLocaleName(), "locales");
		} catch (IOException e) {
			//LOG IO ERROR MESSAGE HERE
			return;
		}
	}

}
