# LocaleManager
A simple utility to manage localization in a clean efficient manner.

# Features
- Allows per player localization
- Can load localization messages from CSV or YAML
- Fallback to default message if it doesnt exist for a specific locale
- Automatically select the player's locale based on their minecraft locale if it exists
- Can be easily extended to include more types of configs

# Usage
Create an instance of the LocaleManager class with the name of the default locale on startup

localeMan = new LocaleManager("en_ca");

Register the login event to add player locales on login if they have never logged in before

Bukkit.getPluginManager().registerEvents(new LoginEvent(), this);

Note: In the LoginEvent class youll need to replace Main with the name of your main class


Save player locale selections back to file on disable

localeMan.savePlayerLocales();


To retrieve localized messages use the following method

LocaleManager#getPlayerMessage(UUID, String);

These messages are not ChatColor translated so you'll need to use ChatColor#translateAlternateColorCodes(String)


Translated configs should be in either CSV format in key,value pairs or YAML format where whole config is scanned and the deep key is used as the key and its value is the message

All configs in the plugin data folder under the subdirectory "locales" will automatically be read into memory upon initializing the LocaleManager class

# Legal
Do whatever the hell you'd like with this. 
No credit required.
