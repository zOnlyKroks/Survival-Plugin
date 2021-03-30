package de.zonlykroks.survival;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import de.zonlykroks.survival.commands.BalanceCommand;
import de.zonlykroks.survival.commands.GamemodeCommands;
import de.zonlykroks.survival.commands.InventorySaveCommand;
import de.zonlykroks.survival.commands.PayCommand;
import de.zonlykroks.survival.commands.SoftReloadCommand;
import de.zonlykroks.survival.config.PlayerData;
import de.zonlykroks.survival.config.PrefixConfig;
import de.zonlykroks.survival.listeners.JoinListener;
import de.zonlykroks.survival.listeners.PlayerDeathListener;
import de.zonlykroks.survival.listeners.QuitListener;
import de.zonlykroks.survival.listeners.ScoreboardListener;
import de.zonlykroks.survival.mysql.Connector;
import de.zonlykroks.survival.util.Lag;
import de.zonlykroks.survival.util.RealTime;
import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener{
	
	private PlayerData playerData;
	
	@SuppressWarnings("unused")
	private PrefixConfig prefixConfig;
	
	public static HashMap<Player,Player> tpa = new HashMap<Player,Player>();
	
	private static Main instance;
	
	@Override
	public void onEnable() {
		instance = this;

		System.out.println("Survival Plugin started up!");
		
		if(!getDataFolder().exists()) {
			getDataFolder().mkdirs();
		}
		
		this.playerData = new PlayerData(this);
		
		this.prefixConfig = new PrefixConfig(this);
		
		if(!getConfig().contains("words")){
            List<String> words = new ArrayList<String>();
            words.add("Nigger");
            getConfig().set("words", words);
        }
		
		saveConfig();
		
		Connector.connect();
		Connector.createTable();
		
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Lag(), 100L, 1L);
		ScoreboardListener.updater();
		RealTime.setRealTime();
		Bukkit.getServer().getPluginManager().registerEvents(new ScoreboardListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new JoinListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new QuitListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
		
		getServer().getPluginManager().registerEvents(this, this);
		
		//this.getCommand("tpa").setExecutor(new TpaCommand());
		//this.getCommand("tpaaccept").setExecutor(new TpaacceptCommand());
		this.getCommand("gm").setExecutor(new GamemodeCommands());
		this.getCommand("balance").setExecutor(new BalanceCommand());
		this.getCommand("pay").setExecutor(new PayCommand());
		this.getCommand("saveallinventories").setExecutor(new InventorySaveCommand());
		this.getCommand("softreload").setExecutor(new SoftReloadCommand());
	}
	
	@Override
	public void onDisable() {
		playerData.save();
		saveConfig();
		Connector.disconnect();
		System.out.println("Survival Plugin shut down!");
	}
	
	public static Main getPlugin(){
		   return instance;
	}
	
	@EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e){
        String msg = e.getMessage();
        List<String> words = getConfig().getStringList(".words");
        for(int i = 0; i < words.size(); i++){
            if(msg.contains(words.get(i))){
                e.setCancelled(true);
                e.getPlayer().sendMessage(ChatColor.DARK_RED  + "You are not allowed to swear!");
            }
        }
    }
	
}
