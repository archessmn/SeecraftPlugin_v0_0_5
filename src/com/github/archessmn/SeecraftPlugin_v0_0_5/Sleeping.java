package com.github.archessmn.SeecraftPlugin_v0_0_5;

import com.github.archessmn.SeecraftPlugin_v0_0_4.tasks.SkipNightAndStorm;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.scheduler.BukkitTask;

public class Sleeping implements Listener{
    main plugin;

    public Sleeping(main instance) {
        plugin = instance;
    }

    @EventHandler
    public void onPlayerBedEnter(final PlayerBedEnterEvent event) {
        Player player = event.getPlayer();
        DataStorageYml.get().set("sleepingPlayer", player);
        BukkitTask sleepingTask = new SkipNightAndStorm(this.plugin).runTaskLater(this.plugin, 20);

    }

    private Bukkit getServer() {
        return null;
    }
}
