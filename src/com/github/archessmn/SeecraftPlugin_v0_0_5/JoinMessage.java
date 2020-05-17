package com.github.archessmn.SeecraftPlugin_v0_0_5;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinMessage implements Listener {
    main plugin;

    public JoinMessage(main instance) {
        plugin = instance;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent joinEvent) {
        plugin.reloadConfig();
        String joinedPlayer = joinEvent.getPlayer().getName();
        String serverName = plugin.getConfig().getString("ServerName");
        joinEvent.setJoinMessage(ChatColor.YELLOW + "Welcome to " + serverName + " " + joinedPlayer + "!");
    }

}
