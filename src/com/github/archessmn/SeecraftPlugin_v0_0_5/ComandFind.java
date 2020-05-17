package com.github.archessmn.SeecraftPlugin_v0_0_5;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ComandFind implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd,String label, String[] args) {
        Player s = (Player)sender;
        if(cmd.getName().equalsIgnoreCase("find")) {
            if (s instanceof Player) {
                if (args.length == 0 || args.length < 1) {
                    s.sendMessage("Incorrect usage: use /find followed by either spawn or haven_checkpoint");
                }
                if (args[0].equalsIgnoreCase("haven_checkpoint") || args[0].equalsIgnoreCase("spawn")) {
                    if (args[0].equalsIgnoreCase("haven_checkpoint") && args.length == 1) {
                        Location loc = ((Player) sender).getLocation();
                        int senderX = loc.getBlockX();
                        int senderY = loc.getBlockY();
                        int senderZ = loc.getBlockZ();
                        int checkpointX;
                        int checkpointZ;

                        HavenCheckpointStorage.reload();

                        //GETS CHECKPOINT COORDS
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

                        int closestCheckpointLocX = HavenCheckpointStorage.get().getInt(checkpointCoords + ".xPos");
                        int closestCheckpointLocY = HavenCheckpointStorage.get().getInt(checkpointCoords + ".yPos");
                        int closestCheckpointLocZ = HavenCheckpointStorage.get().getInt(checkpointCoords + ".zPos");

                        if (HavenCheckpointStorage.get().contains(checkpointCoords)) {
                            s.sendMessage(ChatColor.YELLOW + "The closest checkpoint is Checkpoint ID: " + checkpointCoords + " at coordinates: " + closestCheckpointLocX + " " + closestCheckpointLocY + " " + closestCheckpointLocZ +".");
                        } else {
                            s.sendMessage(ChatColor.RED + "Sorry, a checkpoint has not yet been registered within 1000 blocks of your location, please move closer to spawn to find one.");
                        }

                    }
                    if (args[0].equalsIgnoreCase("spawn") && args.length == 1) {
                        //Locates spawn and gives distance
                        Location loc = ((Player) sender).getLocation();
                        int senderX = loc.getBlockX();
                        int senderZ = loc.getBlockZ();
                        double spawnX = (0 - 130.5);
                        double spawnZ = (0 - 30.5);
                        double xToSpawn = (senderX - spawnX);
                        double zToSpawn = (senderZ - spawnZ);
                        double metersToSpawn = Math.sqrt((xToSpawn*xToSpawn)+(zToSpawn*zToSpawn));
                        s.sendMessage("Spawn is at -130,-30, which is " + metersToSpawn + " blocks away!");
                        //END OF LOCATING SPAWN
                    }
                }
            }
        }
        return false;
    }
}
