package com.github.archessmn.SeecraftPlugin_v0_0_5;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class DataStorageYml {

    private static File dataFile;
    private static FileConfiguration dataYml;

    public static void setup() {
        dataFile = new File(Bukkit.getServer().getPluginManager().getPlugin("SeecraftPlugin").getDataFolder(), "data.yml");

        if (!dataFile.exists()) {
            try{
                dataFile.createNewFile();
            }catch (IOException e) {
                //oh no
            }
        }
        dataYml = YamlConfiguration.loadConfiguration(dataFile);
    }

    public static FileConfiguration get() {
        return dataYml;
    }

    public static void save() {
        try {
            dataYml.save(dataFile);
        }catch (IOException e) {
            System.out.println("Couldn't save Data yml file!");
        }
    }

    public static void reload() {
        dataYml = YamlConfiguration.loadConfiguration(dataFile);
    }
}
