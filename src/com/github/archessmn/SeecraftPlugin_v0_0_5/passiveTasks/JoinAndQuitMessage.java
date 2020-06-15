package com.github.archessmn.SeecraftPlugin_v0_0_5.passiveTasks;

import com.github.archessmn.SeecraftPlugin_v0_0_5.storage.RoleStorageYml;
import com.github.archessmn.SeecraftPlugin_v0_0_5.main;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinAndQuitMessage implements Listener {
    main plugin;

    public JoinAndQuitMessage(main instance) {
        plugin = instance;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent joinEvent) {
        plugin.reloadConfig();
        String joinedPlayer = joinEvent.getPlayer().getDisplayName();
        String serverName = plugin.getConfig().getString("ServerName");
        String importName = RoleStorageYml.get().getString(joinedPlayer);
        String finalName = importName.replace("%", "§");
        joinEvent.getPlayer().setPlayerListName(finalName);
        joinEvent.getPlayer().setCustomName(finalName);
        joinEvent.setJoinMessage(ChatColor.YELLOW + "Welcome to " + "§6" + serverName + " " + finalName + ChatColor.YELLOW + "!");
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent leaveEvent) {
        plugin.reloadConfig();
        String leavingPlayer = leaveEvent.getPlayer().getDisplayName();
        String importName = RoleStorageYml.get().getString(leavingPlayer);
        String finalName = importName.replace("%", "§");
        leaveEvent.setQuitMessage(ChatColor.YELLOW + "Sorry to see you go " + finalName + ChatColor.YELLOW + " :(");
    }

}
