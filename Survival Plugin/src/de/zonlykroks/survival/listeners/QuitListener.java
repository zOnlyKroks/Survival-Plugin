package de.zonlykroks.survival.listeners;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import de.zonlykroks.survival.Main;
import de.zonlykroks.survival.config.PlayerData;

public class QuitListener implements Listener {

    @EventHandler
    public static void onPlayerQuit(PlayerQuitEvent event) {
        Player p = event.getPlayer();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        PlayerData.setLeave(p, dtf.format(now));

        PlayerData.setLastLocation(p, "X: " + p.getLocation().getX() + " Y: " + p.getLocation().getY() + " Z: " + p.getLocation().getZ());

        savePlayerInv(p, p.getWorld());
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
            pInv.set(startInventory + ".enchantment", stack.getEnchantments());
        }

        i = 0;
        for (ItemStack armor: inv.getArmorContents()) {
            if (armor == null) continue;
            i++;
            String startArmor = w.getName() + ".armor." + Integer.toString(i);


            pInv.set(startArmor + ".amount", armor.getAmount());
            pInv.set(startArmor + ".durability", armor.getDurability());
            pInv.set(startArmor + ".type", armor.getType().name());
            pInv.set(startArmor + ".enchantment", armor.getEnchantments());
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
