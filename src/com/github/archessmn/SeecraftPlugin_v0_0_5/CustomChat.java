package com.github.archessmn.SeecraftPlugin_v0_0_5;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class CustomChat implements Listener {
    main plugin;

    public CustomChat(main instance) {
        plugin = instance;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent chatEvent) {
        String player = chatEvent.getPlayer().getName();
        String message = chatEvent.getMessage();
        chatEvent.setFormat(chatEvent.getPlayer().getDisplayName().replaceAll("&([0-9a-f])", RoleStorageYml.get().getString(player)) + ChatColor.WHITE + ": " + ChatColor.WHITE + message);
    }
}
