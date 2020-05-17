package com.github.archessmn.SeecraftPlugin_v0_0_5;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class RoleStorageYml {

    private static File roleFile;
    private static FileConfiguration roleYml;

    public static void setup() {
        roleFile = new File(Bukkit.getServer().getPluginManager().getPlugin("SeecraftPlugin").getDataFolder(), "role.yml");

        if (!roleFile.exists()) {
            try{
                roleFile.createNewFile();
            }catch (IOException e) {
                //oh no
            }
        }
        roleYml = YamlConfiguration.loadConfiguration(roleFile);
    }

    public static FileConfiguration get() {
        return roleYml;
    }

    public static void save() {
        try {
            roleYml.save(roleFile);
        }catch (IOException e) {
            System.out.println("Couldn't save role yml file!");
        }
    }

    public static void reload() {
        roleYml = YamlConfiguration.loadConfiguration(roleFile);
    }
}
