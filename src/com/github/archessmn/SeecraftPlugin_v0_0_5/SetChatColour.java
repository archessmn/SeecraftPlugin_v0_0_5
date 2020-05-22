package com.github.archessmn.SeecraftPlugin_v0_0_5;

import net.minecraft.server.v1_15_R1.EntityPlayer;
import net.minecraft.server.v1_15_R1.PacketPlayOutNamedEntitySpawn;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class SetChatColour implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.isOp()) {
            RoleStorageYml.get().set(args[0], args[1]);
            Player target = Bukkit.getPlayerExact(args[0]);
            sender.sendMessage("Set name to: " + args[1]);
            EntityPlayer ep = ((CraftPlayer) target).getHandle();
            String nameBefore = ep.getName();
            ep.displayName = args[1];

            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player != sender) {
                    ((CraftPlayer) player).getHandle().playerConnection.a().sendPacket(new PacketPlayOutNamedEntitySpawn(ep));
                }
            }

            ep.displayName = nameBefore;
        } else {
            sender.sendMessage("Sorry, you don't have permission to do this. Please ask an admin.");
        }
        RoleStorageYml.save();

        return true;
    }
}
