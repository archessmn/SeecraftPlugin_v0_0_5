package com.github.archessmn.SeecraftPlugin_v0_0_5;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class CheckpointRegister implements CommandExecutor {
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

        int checkpointX2 = DataStorageYml.get().getInt("checkpointX");
        int checkpointZ2 = DataStorageYml.get().getInt("checkpointZ");

        String checkpointXString = String.valueOf(checkpointX2);
        String checkpointZString = String.valueOf(checkpointZ2);
        String checkLocX = String.valueOf(senderX);
        String checkLocY = String.valueOf(senderY);
        String checkLocZ = String.valueOf(senderZ);
        String checkLoc = (checkLocX + " " + checkLocY + " " + checkLocZ);
        String checkpointCoords = (checkpointXString + " " + checkpointZString);

        int checkpointX3 = DataStorageYml.get().getInt("checkpointX");
        int checkpointZ3 = DataStorageYml.get().getInt("checkpointZ");

        if (HavenCheckpointStorage.get().getInt("greatestX") < checkpointX3) {
            HavenCheckpointStorage.get().set("greatestX", checkpointX3);
        } else if (HavenCheckpointStorage.get().getInt("lowestX") > checkpointX3) {
            HavenCheckpointStorage.get().set("lowestX", checkpointX3);
        }

        if (HavenCheckpointStorage.get().getInt("greatestZ") < checkpointZ3) {
            HavenCheckpointStorage.get().set("greatestZ", checkpointZ3);
        } else if (HavenCheckpointStorage.get().getInt("lowestZ") > checkpointZ3) {
            HavenCheckpointStorage.get().set("lowestZ", checkpointZ3);
        }

        FileConfiguration hcs = HavenCheckpointStorage.get();

        int diffX = (hcs.getInt("greatestX") - hcs.getInt("lowestX"));


        HavenCheckpointStorage.get().get("greatestX");
        HavenCheckpointStorage.get().get("greatestY");


        //END OF FIND CHECKPOINT COORDS

        if (HavenCheckpointStorage.get().contains(checkpointCoords)) {
            s.sendMessage(ChatColor.RED + "This location has already been used, do /find Haven_Checkpoint to find where. to register a location please go to a location where the coordinates are multiples of 1000");
        } else {
            HavenCheckpointStorage.get().set(checkpointCoords, loc);
            HavenCheckpointStorage.get().set(checkpointCoords + ".xPos", loc.getBlockX());
            HavenCheckpointStorage.get().set(checkpointCoords + ".yPos", loc.getBlockY());
            HavenCheckpointStorage.get().set(checkpointCoords + ".zPos", loc.getBlockZ());
            s.sendMessage(ChatColor.GREEN + "New HAVEN checkpoint registered successfully! Name: " + checkpointCoords + " Position: " + checkLoc + ".");
        }

        HavenCheckpointStorage.save();

        return true;
    }

}
