package de.zonlykroks.survival.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommands implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	    if (sender instanceof Player) {
	      Player p = (Player)sender;
	      if (cmd.getName().equalsIgnoreCase("gm"))
	        if (p.hasPermission("system.gm.use")) {
	          if (args.length == 1) {
	            if (args[0].equalsIgnoreCase("0")) {
	              p.setGameMode(GameMode.SURVIVAL);
	              p.sendMessage("§aSpielmodus wurde zu §7" + p.getGameMode() + " §ageändert");
	            } else if (args[0].equalsIgnoreCase("1")) {
	              p.setGameMode(GameMode.CREATIVE);
	              p.sendMessage("§aSpielmodus wurde zu §7" + p.getGameMode() + " §ageändert");
	            } else if (args[0].equalsIgnoreCase("2")) {
	              p.setGameMode(GameMode.ADVENTURE);
	              p.sendMessage("§aSpielmodus wurde zu §7" + p.getGameMode() + " §ageändert");
	            } else if (args[0].equalsIgnoreCase("3")) {
	              p.setGameMode(GameMode.SPECTATOR);
	              p.sendMessage("§aSpielmodus wurde zu §7" + p.getGameMode() + " §ageändert");
	            } else {
	              p.sendMessage("§a/gm <0, 1, 2 oder 3> [<Spieler>]");
	            } 
	          } else if (args.length == 2) {
	            if (p.hasPermission("system.gm.others")) {
	              try {
	                Player a = Bukkit.getPlayer(args[1]);
	                if (args[0].equalsIgnoreCase("0")) {
	                  a.setGameMode(GameMode.SURVIVAL);
	                  p.sendMessage("§aDu hast den Spielmodus von §7" + a.getName() + " §aerfolgreich zu  §7" + a.getGameMode() + " §ageändert" );
	                  a.sendMessage("§aDein Spielmodus wurde zu §7" + p.getGameMode() + " §ageändert.");
	                } else if (args[0].equalsIgnoreCase("1")) {
	                  a.setGameMode(GameMode.CREATIVE);
	                  p.sendMessage("§aDu hast den Spielmodus von §7" + a.getName() + " §aerfolgreich zu  §7" + a.getGameMode() + " §ageändert" );
	                  a.sendMessage("§aDein Spielmodus wurde zu §7" + p.getGameMode() + " §ageändert.");
	                  
	                } else if (args[0].equalsIgnoreCase("2")) {
	                  a.setGameMode(GameMode.ADVENTURE);
	                  p.sendMessage("§aDu hast den Spielmodus von §7" + a.getName() + " §aerfolgreich zu  §7" + a.getGameMode() + " §ageändert" );
	                  a.sendMessage("§aDein Spielmodus wurde zu §7" + p.getGameMode() + " §ageändert.");
	                  
	                } else if (args[0].equalsIgnoreCase("3")) {
	                  a.setGameMode(GameMode.SPECTATOR);
	                  p.sendMessage("§aDu hast den Spielmodus von §7" + a.getName() + " §aerfolgreich zu  §7" + a.getGameMode() + " §ageändert" );
	                  a.sendMessage("§aDein Spielmodus wurde zu §7" + p.getGameMode() + " §ageändert.");
	                  
	                } else {
	                  p.sendMessage("/gm <0, 1, 2 oder 3> [<Spieler>]");
	                } 
	              } catch (NullPointerException d) {
	                p.sendMessage("Dieser Spieler ist zurzeit nicht online!");
	              } 
	            } else {
	              p.sendMessage("hast du keine Rechte!");
	            } 
	          } else {
	            p.sendMessage("/gm <0, 1, 2, oder 3> [<Spieler>]");
	          } 
	        } else {
	          p.sendMessage("hast du keine Rechte!");
	        }  
	    } 
	    return false;
	  }

}
