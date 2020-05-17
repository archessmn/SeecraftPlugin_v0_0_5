package com.github.archessmn.SeecraftPlugin_v0_0_5;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CheckpointRemove implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player s = (Player) sender;
        Location loc = ((Player) sender).getLocation();
        int senderX = loc.getBlockX();
        int senderY = loc.getBlockY();
        int senderZ = loc.getBlockZ();

        //finds closest multiple of 1000
        DataStorageYml.get().set("senderX", senderX);
        DataStorageYml.get().set("senderY", senderY);
        DataStorageYml.get().set("senderZ", senderZ);

        GetCheckpointCoords.getCheckpointCoords();

        String checkpointX2 = DataStorageYml.get().getString("checkpointX");
        String checkpointZ2 = DataStorageYml.get().getString("checkpointZ");

        String checkpointXString = String.valueOf(checkpointX2);
        String checkpointZString = String.valueOf(checkpointZ2);
        String checkLocX = String.valueOf(senderX);
        String checkLocY = String.valueOf(senderY);
        String checkLocZ = String.valueOf(senderZ);
        String checkLoc = (checkLocX + " " + checkLocY + " " + checkLocZ);
        String checkpointCoords = (checkpointXString + " " + checkpointZString);
        //END OF FIND CHECKPOINT COORDS

        if (HavenCheckpointStorage.get().contains(checkpointCoords)) {
            s.sendMessage(ChatColor.RED + "Removing Checkpoint " + ChatColor.GOLD + checkpointCoords + ".");
            HavenCheckpointStorage.get().set(checkpointCoords, null);
        } else {
            s.sendMessage(ChatColor.RED + "A checkpoint does not exist in this location, remember that each location is in a 1000 by 1000 area from the center, so for example -1500 -3500 to -2500 -4500");

        }

        HavenCheckpointStorage.save();

        return true;
    }
}
