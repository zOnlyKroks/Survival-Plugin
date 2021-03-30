package de.zonlykroks.survival.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.zonlykroks.survival.Main;
import de.zonlykroks.survival.config.AbstractConfigFile;

public class SoftReloadCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Main.getPlugin().reloadConfig();
			Player p = (Player) sender;
			p.sendMessage("Die Configs wurden neu geladen");
		}
		return false;
	}

}
