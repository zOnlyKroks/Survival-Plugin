package de.zonlykroks.survival.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import de.zonlykroks.survival.api.CoinApi;
import de.zonlykroks.survival.listeners.ScoreboardListener;


public class PayCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if(args.length == 2) {
				int coins = Integer.valueOf(args[1]).intValue();
				
				Player target = Bukkit.getPlayer(args[0]);
				
				CoinApi.removeCoins(p.getUniqueId().toString(), coins);
				p.sendMessage("Du hast " + coins +" Coins an " + target + " gesendet!");
				
				CoinApi.addCoins(target.getUniqueId().toString(), coins);
				p.sendMessage("Du hast " + coins + " Coins von " + target + " empfangen!");
				
				ScoreboardListener.sendScoreboard(p);
				ScoreboardListener.sendScoreboard(target);
			}else {
				p.sendMessage("Nutze /pay Coins Spieler!");
			}
		}else {
			sender.sendMessage("Die Konsole kann diesen Befehl nicht ausführen!");
		}
		return false;
	}

}
