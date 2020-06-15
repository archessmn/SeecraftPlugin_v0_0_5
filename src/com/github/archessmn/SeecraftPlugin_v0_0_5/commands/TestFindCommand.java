package com.github.archessmn.SeecraftPlugin_v0_0_5.commands;

//N = -Z
//E = X
//S = Z
//W = -X



import com.github.archessmn.SeecraftPlugin_v0_0_5.storage.DataStorageYml;
import com.github.archessmn.SeecraftPlugin_v0_0_5.storage.HavenCheckpointStorage;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class TestFindCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Location loc = ((Player) sender).getLocation();
        int senderX = loc.getBlockX();
        int senderY = loc.getBlockY();
        int senderZ = loc.getBlockZ();
        int remainderSenderX;
        int remainderSenderZ;
        int checkpointX;
        int checkpointZ;

        remainderSenderX = (senderX % 1000);
        remainderSenderZ = (senderZ % 1000);
        if (senderX >=0) {
            if (senderX >= 500) {
                DataStorageYml.get().set("findE/W", "W");
                checkpointX = (senderX - remainderSenderX) / 1000;
                DataStorageYml.get().set("checkpointX", checkpointX);
                HavenCheckpointStorage.save();
            } else if (senderX < 500) {
                DataStorageYml.get().set("findE/W", "E");
                checkpointX = (senderX + (1000 - remainderSenderX));
                DataStorageYml.get().set("checkpointX", checkpointX);
                HavenCheckpointStorage.save();
            }
        } else {
            if (remainderSenderX > (- 500)) {
                DataStorageYml.get().set("findE/W", "W");
                checkpointX = (senderX - remainderSenderX) / 1000;
                DataStorageYml.get().set("checkpointX", checkpointX);
                HavenCheckpointStorage.save();
            } else {
                DataStorageYml.get().set("findE/W", "E");
                checkpointX = (senderX - (- remainderSenderX)) / 1000;
                DataStorageYml.get().set("checkpointX", checkpointX);
                HavenCheckpointStorage.save();
            }
        }
        if (senderZ >= 0) {
            if (remainderSenderZ < 500) {
                DataStorageYml.get().set("findN/S", "S");
                checkpointZ = (senderZ - remainderSenderZ) /1000;
                DataStorageYml.get().set("checkpointZ", checkpointZ);
                HavenCheckpointStorage.save();
            } else {
                DataStorageYml.get().set("findN/S", "N");
                checkpointZ = (senderZ + (1000 - remainderSenderZ)) / 1000;
                DataStorageYml.get().set("checkpointZ", checkpointZ);
                HavenCheckpointStorage.save();
            }
        } else {
            if (remainderSenderZ > (- 500)) {
                DataStorageYml.get().set("findN/S", "N");
                checkpointZ = (senderZ - remainderSenderZ) / 1000;
                DataStorageYml.get().set("checkpointZ", checkpointZ);
                HavenCheckpointStorage.save();
            } else {
                DataStorageYml.get().set("findN/S", "S");
                checkpointZ = (senderZ - (- remainderSenderZ)) / 1000;
                DataStorageYml.get().set("checkpointZ", checkpointZ);
                HavenCheckpointStorage.save();
            }
        }
        int checkpointX2 = DataStorageYml.get().getInt("checkpointX");
        int checkpointZ2 = DataStorageYml.get().getInt("checkpointZ");

        String checkpointXString = String.valueOf(checkpointX2);
        String checkpointZString = String.valueOf(checkpointZ2);
        String checkLocX = String.valueOf(senderX);
        String checkLocY = String.valueOf(senderY);
        String checkLocZ = String.valueOf(senderZ);
        String checkLoc = (checkLocX + " " + checkLocY + " " + checkLocZ);
        String checkpointCoords = (checkpointXString + " " + checkpointZString);
        String playerNS = DataStorageYml.get().getString("findN/S");
        String playerEW = DataStorageYml.get().getString("findE/W");
        String quadrant = (playerNS + " " + playerEW);


        sender.sendMessage("Checkpoint coords: " + checkpointCoords + " in quadrant: " + quadrant);
        FileConfiguration hcs = HavenCheckpointStorage.get();

        if (checkpointX2 > hcs.getInt("highestX")) {
            hcs.set("highestX", checkpointX2);
        } else if (checkpointX2 < hcs.getInt("lowestX")) {
            hcs.set("lowestX", checkpointX2);
        }
        if (checkpointZ2 > hcs.getInt("highestZ")) {
            hcs.set("highestZ", checkpointZ2);
        } else if (checkpointZ2 < hcs.getInt("lowestZ")) {
            hcs.set("lowestZ", checkpointZ2);
        }

        HavenCheckpointStorage.reload();
        hcs.set("closest.distance", 0);

        for (int x = HavenCheckpointStorage.get().getInt("lowestX"); x == HavenCheckpointStorage.get().getInt("highestX"); x++) {
            for (int z = HavenCheckpointStorage.get().getInt("lowestZ"); z == HavenCheckpointStorage.get().getInt("highestZ"); z++) {
                if ((hcs.getInt(x + " " + z + ".xPos") != 0) && (hcs.getInt(x + " " + z + ".zPos") != 0)) {
                    double distanceX = (senderX) - hcs.getInt(x + " " + z + ".xPos");
                    double distanceZ = (senderZ) - hcs.getInt(x + " " + z + ".zPos");
                    double distance = Math.sqrt((distanceX * distanceX) + (distanceZ * distanceZ));
                    if(distance < hcs.getDouble("closest.distance")) {
                        hcs.set("closest.distance", distance);
                        hcs.set("closest.checkpoint", x + " " + z);
                    }
                }
            }
        }
        
        sender.sendMessage(ChatColor.YELLOW + "The closest checkpoint is at: " + ChatColor.GOLD + hcs.get(hcs.get("closest.checkpoint") + ".xPos") + " " + hcs.get(hcs.get("closest.checkpoint") + ".zPos") + ChatColor.YELLOW + " which is " + ChatColor.GOLD+ hcs.get("closest.distance") + ChatColor.YELLOW + " blocks away.");




        return true;
    }

}
