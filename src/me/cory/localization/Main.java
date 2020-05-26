package me.cory.localization;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Just an example. 
 * @author Cory Caron
 */
public class Main extends JavaPlugin {

	//We could change LocaleManager to a singleton pattern,
	//but we would need to initialize at startup to ensure we dont lock the thread when first loading the configs from file
	private LocaleManager localeMan;
	
	//Create the LocaleManager instance and register the login event 
	public void onEnable(){
		localeMan = new LocaleManager("en_ca");
		Bukkit.getPluginManager().registerEvents(new LoginEvent(localeMan), this);
	}
	
	//Save player locale selections
	public void onDisable(){
		localeMan.savePlayerLocales();
	}
	
	//get an instance of the LocaleManager
	public LocaleManager getLocaleManager(){
		return localeMan;
	}
}
