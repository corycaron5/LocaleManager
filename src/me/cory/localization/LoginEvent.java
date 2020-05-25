package me.cory.localization;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class LoginEvent implements Listener{

	/**
	 * Register player's locale or default if they have never joined before
	 * We register it in the Login event so it's already loaded if we want to send a message on the join event
	 * @param event PlayerLoginEvent
	 */
	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent event){
		Player player = event.getPlayer();
		LocaleManager man = Main.getPlugin(Main.class).getLocaleManager();
		if(!man.containsPlayer(player.getUniqueId())){
			String locale = player.getLocale();
			if(man.containsLocale(locale))man.setPlayerLocale(player.getUniqueId(), locale);
			else man.setPlayerLocale(player.getUniqueId(), man.getDefaultLocale());
		}
	}
	
}
