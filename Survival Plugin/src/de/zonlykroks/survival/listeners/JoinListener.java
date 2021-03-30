package de.zonlykroks.survival.listeners;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.zonlykroks.survival.api.CoinApi;
import de.zonlykroks.survival.config.PlayerData;

public class JoinListener implements Listener{
	
	@EventHandler
	public static void onJoin(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		if(!p.hasPlayedBefore()) {
			CoinApi.setCoins(p.getUniqueId().toString(), 1000);
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			PlayerData.setFirstEverJoin(p, dtf.format(now));
			
			PlayerData.setJoin(p, dtf.format(now));
			
		}else if(p.hasPlayedBefore()) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			PlayerData.setJoin(p, dtf.format(now));
		}
		
		String ip = p.getAddress().getAddress().toString();
		PlayerData.setIP(p, ip);
	}

}
