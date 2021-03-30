package de.zonlykroks.survival.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.zonlykroks.survival.api.CoinApi;

public class BalanceCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player p = (Player) sender;;
		if(args.length == 0) {
			sender.sendMessage("Dein Kontostand beträgt " + CoinApi.getCoins(p.getUniqueId().toString()) + " Coins");
		}else if(args.length == 1) {
			Player target = Bukkit.getPlayer(args[0]);
			sender.sendMessage("Der Spieler " + target.getName() + " besitzt " + CoinApi.getCoins(target.getUniqueId().toString()) + " Coins!");
		}
        return true;
    }

}
