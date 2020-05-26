package me.cory.localization;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class LoginEvent implements Listener{

	private LocaleManager localeMan;
	
	/**
	 * Constructor with LocaleManager instance
	 * @param localeMan LocaleManager for this plugin
	 */
	public LoginEvent(LocaleManager localeMan){
		this.localeMan = localeMan;
	}
	
	/**
	 * Register player's locale or default if they have never joined before
	 * We register it in the Login event so it's already loaded if we want to send a message on the join event
	 * @param event PlayerLoginEvent
	 */
	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent event){
		Player player = event.getPlayer();
		if(!localeMan.containsPlayer(player.getUniqueId())){
			String locale = player.getLocale();
			if(localeMan.containsLocale(locale))localeMan.setPlayerLocale(player.getUniqueId(), locale);
			else localeMan.setPlayerLocale(player.getUniqueId(), localeMan.getDefaultLocale());
		}
	}
	
}

