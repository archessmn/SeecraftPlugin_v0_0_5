package com.github.archessmn.SeecraftPlugin_v0_0_5;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Objects;

public class CustomChat implements Listener {
    main plugin;

    public CustomChat(main instance) {
        plugin = instance;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent chatEvent) {
        String player = chatEvent.getPlayer().getName();
        String message = chatEvent.getMessage();
        RoleStorageYml.reload();
        String importName = RoleStorageYml.get().getString(player);
        String finalName = importName.replace("%", "§");
        chatEvent.getPlayer().setPlayerListName(finalName);
        chatEvent.getPlayer().setCustomName(finalName);
        chatEvent.setFormat(finalName + ChatColor.WHITE + ": " + ChatColor.WHITE + message);
    }
}
