package de.zonlykroks.survival.util;

import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.World;

import de.zonlykroks.survival.Main;

public class RealTime {

    @SuppressWarnings("deprecation")
    public static void setRealTime() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), () -> {
            Date date = new Date();
            long time = date.getHours() * 1000 + (long)(date.getMinutes() * 16.66666666666667) + (long)(date.getSeconds() * 0.1666666666666667) - 6000;

            for (World world: Bukkit.getWorlds())
                world.setTime(time);
        }, 0, 600);

    }
}
