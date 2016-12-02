package com.xkev.SimpleMobControl;

import com.xkev.SimpleMobControl.Commands.DisableMob;
import com.xkev.SimpleMobControl.Commands.ShowAvailableMobs;
import com.xkev.SimpleMobControl.Commands.ShowDisabledMobs;
import com.xkev.SimpleMobControl.Events.CreatureSpawn;
import com.xkev.SimpleMobControl.MobConfig.Mobs;
import com.xkev.SimpleMobControl.MobConfig.ReadMobConfig;
import com.xkev.SimpleMobControl.MobConfig.SaveMobConfig;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main Method
 */
public class Main extends JavaPlugin {


    private Mobs mobs;

    @Override
    public void onEnable() {

        mobs = new Mobs();

        readMobConfig();
        registerCommands();
        registerEvents();

        getLogger().info("Plugin successfully loaded!");
    }

    @Override
    public void onDisable() {
        saveMobConfig();
    }

    private void readMobConfig(){
        ReadMobConfig mobConfig = new ReadMobConfig(this, mobs);
    }

    private void saveMobConfig(){
        SaveMobConfig mobConfig = new SaveMobConfig(this, mobs);
    }

    private void registerCommands() {
        getCommand("disableMob").setExecutor(new DisableMob(mobs));
        getCommand("showAvailableMobs").setExecutor(new ShowAvailableMobs(this, mobs));
        getCommand("showDisabledMobs").setExecutor(new ShowDisabledMobs(this, mobs));
    }

    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new CreatureSpawn(this, mobs), this);
    }
}
