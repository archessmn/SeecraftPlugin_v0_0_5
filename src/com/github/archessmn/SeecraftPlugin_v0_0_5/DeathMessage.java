package com.github.archessmn.SeecraftPlugin_v0_0_5;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathMessage implements Listener {
    main plugin;

    public DeathMessage(main instance) {
        plugin = instance;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        int deathXInt = e.getEntity().getLocation().getBlockX();
        int deathYInt = e.getEntity().getLocation().getBlockY();
        int deathZInt = e.getEntity().getLocation().getBlockZ();
        String deathDimension = String.valueOf(e.getEntity().getWorld().getEnvironment());



        String dimension;

        if (deathDimension == "NORMAL") {
            dimension = "overworld";
            DataStorageYml.get().set("dimension", dimension);
            DataStorageYml.save();
        } else if (deathDimension == "NETHER") {
            dimension = "nether";
            DataStorageYml.get().set("dimension", dimension);
            DataStorageYml.save();
        } else if (deathDimension == "THE_END") {
            dimension = "end";
            DataStorageYml.get().set("dimension", dimension);
            DataStorageYml.save();
        }

        String dimensionFinal = (String) DataStorageYml.get().get("dimension");

        e.getEntity().sendMessage(ChatColor.DARK_RED + "You died at: " + ChatColor.RED + deathXInt + " " + deathYInt + " " + deathZInt + ChatColor.DARK_RED + ", in the " + ChatColor.RED + dimensionFinal);


    }

}
