package com.github.archessmn.SeecraftPlugin_v0_0_5;

import com.github.archessmn.SeecraftPlugin_v0_0_5.commands.*;
import com.github.archessmn.SeecraftPlugin_v0_0_5.passiveTasks.*;
import com.github.archessmn.SeecraftPlugin_v0_0_5.storage.*;
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
        pm.registerEvents(new JoinAndQuitMessage(this), this);
        pm.registerEvents(new DeathMessage(this), this);
        pm.registerEvents(new CustomChat(this), this);
        this.getCommand("find").setExecutor(new CheckpointFindCommand());
        this.getCommand("register").setExecutor(new CheckpointRegister());
        this.getCommand("remove").setExecutor(new CheckpointRemoveCommand());
        this.getCommand("testfind").setExecutor(new TestFindCommand());
        this.getCommand("colour").setExecutor(new NameCommand());
        this.getCommand("superchat").setExecutor(new SuperChatCommand());

    }

    public void onDisable() {
        DataStorageYml.save();
        HavenCheckpointStorage.save();
        RoleStorageYml.save();
    }
}
