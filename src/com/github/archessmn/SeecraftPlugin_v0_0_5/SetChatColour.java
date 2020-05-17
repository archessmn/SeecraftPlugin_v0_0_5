package com.github.archessmn.SeecraftPlugin_v0_0_5;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SetChatColour implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.isOp()) {
            RoleStorageYml.get().set(args[0], args[1]);
        }

        return true;
    }
}
