package de.zonlykroks.survival.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import de.zonlykroks.survival.Main;


public class InventorySaveCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player p = (Player) sender;

        savePlayerInv(p, p.getWorld());
        p.sendMessage("Die Inventare von allen Spielern wurde gesichert.");

        return false;
    }

    @SuppressWarnings("deprecation")
    public static void savePlayerInv(Player p, World w) {
        File playerInvConfigFile = new File(Main.getPlugin().getDataFolder() + File.separator + "players" + File.separator + p.getName(), "inventory.yml");
        FileConfiguration pInv = YamlConfiguration.loadConfiguration(playerInvConfigFile);
        PlayerInventory inv = p.getInventory();
        int i = 0;

        for (ItemStack stack: inv.getContents()) {
            if (stack == null) continue;

            i++;
            String startInventory = w.getName() + ".inv." + Integer.toString(i);


            pInv.set(startInventory + ".amount", stack.getAmount());
            pInv.set(startInventory + ".durability", Short.toString(stack.getDurability()));
            pInv.set(startInventory + ".type", stack.getType().name());
            pInv.set(startInventory + ".enchantment", stack.getEnchantments().toString());
        }

        i = 0;
        for (ItemStack armor: inv.getArmorContents()) {
            if (armor == null) continue;
            i++;
            String startArmor = w.getName() + ".armor." + Integer.toString(i);


            pInv.set(startArmor + ".amount", armor.getAmount());
            pInv.set(startArmor + ".durability", armor.getDurability());
            pInv.set(startArmor + ".type", armor.getType().name());
            pInv.set(startArmor + ".enchantment", armor.getEnchantments().toString());
        }


        if (p.getExp() != 0) {
            pInv.set(w.getName() + ".exp", p.getExp());
        }

        try {
            pInv.save(playerInvConfigFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}