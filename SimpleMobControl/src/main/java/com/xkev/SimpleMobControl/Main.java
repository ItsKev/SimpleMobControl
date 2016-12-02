package com.xkev.SimpleMobControl;

import com.xkev.SimpleMobControl.Commands.DisableMob;
import com.xkev.SimpleMobControl.Commands.ShowAvailableMobs;
import com.xkev.SimpleMobControl.Events.CreatureSpawn;
import com.xkev.SimpleMobControl.MobConfig.Mobs;
import com.xkev.SimpleMobControl.MobConfig.ReadMobConfig;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main Method
 */
public class Main extends JavaPlugin {

    private ReadMobConfig mobConfig;
    private Mobs mobs;

    @Override
    public void onEnable() {

        mobs = new Mobs();
        mobConfig = new ReadMobConfig(this, mobs);

        registerCommands();
        registerEvents();

        getLogger().info("Test 1!");
        getLogger().info("Plugin successfully loaded!");
    }

    @Override
    public void onDisable() {

    }

    private void registerCommands() {
        getCommand("disableMob").setExecutor(new DisableMob(this, mobs));
        getCommand("showAvailableMobs").setExecutor(new ShowAvailableMobs(this, mobs));
    }

    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new CreatureSpawn(this, mobs), this);
    }
}
