package com.github.archessmn.SeecraftPlugin_v0_0_5;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {
    @Override
    public void onEnable() {
        //Config
        this.reloadConfig();
        this.saveDefaultConfig();
        this.saveConfig();
        this.getConfig();
        FileConfiguration config = this.getConfig();
        config.addDefault("ServerName", "the server");

        //Test Data Storage
        DataStorageYml.setup();
        DataStorageYml.get().options().copyDefaults(true);
        DataStorageYml.get().addDefault("Data1", "It works!");
        DataStorageYml.save();

        //Checkpoint Storage
        HavenCheckpointStorage.setup();
        HavenCheckpointStorage.get().options().copyDefaults(true);
        HavenCheckpointStorage.reload();

        //Role Storage
        RoleStorageYml.setup();
        RoleStorageYml.get().options().copyDefaults(true);
        RoleStorageYml.reload();


        //Commands and Actions
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new Sleeping(this), this);
        pm.registerEvents(new JoinMessage(this), this);
        pm.registerEvents(new DeathMessage(this), this);
        pm.registerEvents(new CustomChat(this), this);
        this.getCommand("find").setExecutor(new ComandFind());
        this.getCommand("register").setExecutor(new CheckpointRegister());
        this.getCommand("remove").setExecutor(new CheckpointRemove());
        this.getCommand("testfind").setExecutor(new TestFind());
        this.getCommand("colour").setExecutor(new SetChatColour());

    }
}
