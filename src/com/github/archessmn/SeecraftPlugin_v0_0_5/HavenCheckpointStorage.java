package com.github.archessmn.SeecraftPlugin_v0_0_5;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class HavenCheckpointStorage {

    private static File checkpointsFile;
    private static FileConfiguration checkpointsYml;

    public static void setup() {
        checkpointsFile = new File(Bukkit.getServer().getPluginManager().getPlugin("SeecraftPlugin").getDataFolder(), "checkpoints.yml");

        if (!checkpointsFile.exists()) {
            try{
                checkpointsFile.createNewFile();
            }catch (IOException e) {
                //oh no
            }
        }
        checkpointsYml = YamlConfiguration.loadConfiguration(checkpointsFile);
    }

    public static FileConfiguration get() {
        return checkpointsYml;
    }

    public static void save() {
        try {
            checkpointsYml.save(checkpointsFile);
        }catch (IOException e) {
            System.out.println("Couldn't save Data yml file!");
        }
    }

    public static void reload() {
        checkpointsYml = YamlConfiguration.loadConfiguration(checkpointsFile);
    }
}
