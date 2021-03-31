package de.zonlykroks.survival.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.zonlykroks.survival.Main;
import net.md_5.bungee.api.ChatColor;

public class TpaacceptCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player p = (Player) sender;

        if (args.length == 0) {

            Player p2 = Main.tpa.get(p);

            p2.teleport(p.getLocation());

            p.sendMessage(ChatColor.GREEN + "Teleportation geglückt");
            p2.sendMessage(ChatColor.GREEN + "Teleportation geglückt");

        } else {
            p.sendMessage("/tpaccept");
        }
        return false;
    }

}
