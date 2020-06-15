package com.github.archessmn.SeecraftPlugin_v0_0_5.commands;

import com.github.archessmn.SeecraftPlugin_v0_0_5.storage.RoleStorageYml;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SuperChatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender.isOp()) {
            String senderName = sender.getName();
            String importName = RoleStorageYml.get().getString(senderName);
            String finalName = importName.replace("%", "§");
            String message = "";
            for (int i = 0; i < args.length; i++) {
                message = message + args[i] + " ";
            }
            message = message.replaceAll("%", "§");
            Bukkit.broadcastMessage(finalName + ChatColor.WHITE + ": " + ChatColor.RESET + message);
        } else {
            sender.sendMessage(ChatColor.RED + "Sorry, you do not have access to this command.");
        }
        return true;
    }
}
