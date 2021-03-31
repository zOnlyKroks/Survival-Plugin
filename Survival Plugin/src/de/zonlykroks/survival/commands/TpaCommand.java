package de.zonlykroks.survival.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.zonlykroks.survival.Main;
import net.md_5.bungee.api.ChatColor;

public class TpaCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player p = (Player) sender;


        if (args.length == 1) {
            try {
                Player p2 = Bukkit.getPlayer(args[0]);

                Main.tpa.put(p2, p);

                p2.sendMessage(ChatColor.GREEN + p.getName() + " möchte sich zu dir Teleportieren! Benutze " + ChatColor.RED + "/tpaccept " + ChatColor.GREEN + p + " um ihn zu dir zu teleportieren!");
                p.sendMessage(ChatColor.GREEN + p2.getName() + ChatColor.GREEN + " hat die Teleportationsanfrage erhalten");

            } catch (Exception ex) {
                p.sendMessage(ChatColor.RED + "Der Spieler " + args[0] + " ist nicht online!");
            }

        } else {
            p.sendMessage(ChatColor.RED + "/tpa [Name des Spielers");
        }
        return false;
    }

}
