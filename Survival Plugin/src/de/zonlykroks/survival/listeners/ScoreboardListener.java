package de.zonlykroks.survival.listeners;

import java.util.Date;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import de.zonlykroks.survival.Main;
import de.zonlykroks.survival.api.CoinApi;
import de.zonlykroks.survival.util.Lag;
import net.md_5.bungee.api.ChatColor;

public class ScoreboardListener implements Listener{
	
	@EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
    	Player p = event.getPlayer();
        sendScoreboard(p);
    }
	
	private static HashMap<Scoreboard, Player> boards = new HashMap<>();
	
	public static void updater() {
		new BukkitRunnable() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				for(Scoreboard board : boards.keySet()) {
					Player p = boards.get(board);
					Date d = new Date();
					board.getTeam("zeit").setSuffix(""+ChatColor.DARK_GRAY + d.getHours() + ChatColor.DARK_GRAY + ":" + ChatColor.DARK_GRAY + d.getMinutes() + ChatColor.DARK_GRAY + ":" + ChatColor.DARK_GRAY + d.getSeconds());
					board.getTeam("spieler").setSuffix("" + ChatColor.DARK_GRAY + Bukkit.getOnlinePlayers().size());
					
					int tickspersecond = (int) Lag.getTPS();
					
					board.getTeam("tps").setSuffix(("" + ChatColor.DARK_GRAY + Math.round(tickspersecond)));
					board.getTeam("money").setSuffix("" + ChatColor.DARK_GRAY + CoinApi.getCoins(p.getUniqueId().toString()));
				}
			}
		}.runTaskTimer(Main.getPlugin(), 0, 20);
	}

		
		@SuppressWarnings("deprecation")
		public static void sendScoreboard(Player p) {
	

	        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();

	        Objective obj = board.registerNewObjective("aaa", "bbb");
	        
	        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
	        obj.setDisplayName(ChatColor.YELLOW + "Steam" + ChatColor.DARK_GRAY + "War");
	     
	        Team zeit = board.registerNewTeam("zeit");
	        Team s1 = board.registerNewTeam("s1");
	        Team spieler = board.registerNewTeam("spieler");
	        Team s2 = board.registerNewTeam("s2");
	        Team tps = board.registerNewTeam("tps");
	        Team s3 = board.registerNewTeam("s3");
	        Team money = board.registerNewTeam("money");
	       
	        Date d = new Date();
	        
	        zeit.setPrefix(ChatColor.YELLOW + "Zeit: ");
	        zeit.setSuffix("" + ChatColor.DARK_GRAY + d.getHours() + ChatColor.DARK_GRAY + ":" + ChatColor.YELLOW + d.getMinutes() + ChatColor.DARK_GRAY + ":" + ChatColor.DARK_GRAY + d.getSeconds());
	        zeit.addEntry(ChatColor.YELLOW.toString());
	        
	        s1.setPrefix("");
	        s1.setSuffix("");
	        s1.addEntry(ChatColor.DARK_RED.toString());
	        
	        spieler.setPrefix(ChatColor.YELLOW + "Spieler: ");
	        spieler.setSuffix("" + ChatColor.DARK_GRAY +Bukkit.getOnlinePlayers().size());
	        spieler.addEntry(ChatColor.DARK_GRAY.toString());
	        
	        s2.setPrefix("");
	        s2.setSuffix("");
	        s2.addEntry(ChatColor.DARK_BLUE.toString());
	        
	        s3.setPrefix("");
	        s3.setSuffix("");
	        s3.addEntry(ChatColor.DARK_PURPLE.toString());
	        
	        money.setPrefix(ChatColor.YELLOW + "Geld: ");
	        money.setSuffix("" + ChatColor.DARK_GRAY + CoinApi.getCoins(p.getUniqueId().toString())); //SQL Verbindung zu einer Datenbank hier hin fürs Geld!
	        money.addEntry(ChatColor.BLACK.toString());
	        
	        int tickspersecond = (int) Lag.getTPS();
	        
	        tps.setPrefix(ChatColor.YELLOW + "TPS: ");
	        tps.setSuffix("" + ChatColor.DARK_GRAY + Math.round(tickspersecond));
	        tps.addEntry(ChatColor.DARK_GREEN.toString());
	       
	        obj.getScore(ChatColor.YELLOW.toString()).setScore(7);
	        obj.getScore(ChatColor.DARK_RED.toString()).setScore(6);
	        obj.getScore(ChatColor.DARK_GRAY.toString()).setScore(5);
	        obj.getScore(ChatColor.DARK_BLUE.toString()).setScore(4);
	        obj.getScore(ChatColor.DARK_GREEN.toString()).setScore(3);
	        obj.getScore(ChatColor.DARK_PURPLE.toString()).setScore(2);
	        obj.getScore(ChatColor.BLACK.toString()).setScore(1);
	        
	        boards.put(board, p);


	        p.setScoreboard(board);
		    
		}
	    
}
