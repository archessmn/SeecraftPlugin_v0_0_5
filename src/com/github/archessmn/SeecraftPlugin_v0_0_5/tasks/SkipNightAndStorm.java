package com.github.archessmn.SeecraftPlugin_v0_0_5.tasks;

import com.github.archessmn.SeecraftPlugin_v0_0_5.storage.DataStorageYml;
import com.github.archessmn.SeecraftPlugin_v0_0_5.storage.RoleStorageYml;
import com.github.archessmn.SeecraftPlugin_v0_0_5.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;


public class SkipNightAndStorm extends BukkitRunnable {
    main plugin;

    public SkipNightAndStorm(main instance) {
        plugin = instance;
    }

    Player player = (Player) DataStorageYml.get().get("sleepingPlayer");

    @Override
    public void run() {
        System.out.println("Task delayed");
        if (player.isSleeping()) {
            int skipped = 0;
            for (World world : Bukkit.getWorlds()) {
                if (world.getTime() >= 13000) {
                    world.setTime(0);
                    world.setStorm(true);
                    world.setStorm(false);
                    skipped = 1;
                } else if (world.hasStorm()) {
                    world.setStorm(true);
                    world.setStorm(false);
                    skipped = 2;
                } else {
                    skipped = 0;
                }

            }
            String importCodes = RoleStorageYml.get().getString(player.getDisplayName());
            String finalCodes = importCodes.replace("%", "§");
            player.setPlayerListName(finalCodes);
            player.setCustomName(finalCodes);
            switch (skipped) {
                case 0:
                    break;
                case 1:
                    Bukkit.broadcastMessage(finalCodes + ChatColor.YELLOW +  " skipped the night!");
                    break;
                case 2:
                    Bukkit.broadcastMessage(finalCodes + ChatColor.YELLOW +  " skipped the storm!");
                    break;
            }
        }


    }

    private Bukkit getServer() {
        return null;
    }
}
