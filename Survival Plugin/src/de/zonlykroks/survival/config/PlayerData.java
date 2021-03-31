package de.zonlykroks.survival.config;

import java.io.IOException;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;

import de.zonlykroks.survival.Main;

public class PlayerData extends AbstractConfigFile {

    public PlayerData(Main main) {
        super(main, "playerdata.yml");
    }

    public static void setFirstEverJoin(Player player, String time) {
        config.set(player.getUniqueId().toString() + ".time.firsteverjoin", time);
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setJoin(Player player, String time) {
        config.set(player.getUniqueId().toString() + ".time.latestjoin", time);
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setLeave(Player player, String time) {
        config.set(player.getUniqueId().toString() + ".time.lastonline", time);
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setLastLocation(Player player, String location) {
        config.set(player.getUniqueId().toString() + ".logoff location", location);
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setDeaths(Player player, int deaths) {
        config.set(player.getUniqueId().toString() + ".deaths", deaths);
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setDeathCause(Player player, EntityDamageEvent entityDamageEvent) {
        config.set(player.getUniqueId().toString() + ".death cause", entityDamageEvent);
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setIP(Player player, String ip) {
        config.set(player.getUniqueId().toString() + ".ip adress", ip);
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
