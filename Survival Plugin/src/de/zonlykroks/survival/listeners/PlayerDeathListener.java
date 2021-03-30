package de.zonlykroks.survival.listeners;

import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import de.zonlykroks.survival.config.PlayerData;

public class PlayerDeathListener implements Listener{
	
	@EventHandler
	public static void onDeath(PlayerDeathEvent event) {
		Player p = event.getEntity().getPlayer();
		PlayerData.setDeaths(p, p.getStatistic(Statistic.DEATHS) + 1);
		PlayerData.setDeathCause(p, event.getEntity().getPlayer().getLastDamageCause());
	}

}
