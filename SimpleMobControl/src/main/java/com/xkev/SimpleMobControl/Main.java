package com.xkev.SimpleMobControl;

import com.xkev.SimpleMobControl.Commands.DisableMob;
import com.xkev.SimpleMobControl.Commands.ShowAvailableMobs;
import com.xkev.SimpleMobControl.Commands.ShowDisabledMobs;
import com.xkev.SimpleMobControl.Commands.EnableMob;
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
    public static String prefix = "§a[SimpleMobControl]§f ";

    @Override
    public void onEnable() {

        this.mobs = new Mobs();

        this.readMobConfig();
        this.registerCommands();
        this.registerEvents();

        this.getLogger().info("Plugin successfully loaded!");
    }

    @Override
    public void onDisable() {
        this.saveMobConfig();
    }

    private void readMobConfig(){
        ReadMobConfig mobConfig = new ReadMobConfig(this, this.mobs);
    }

    private void saveMobConfig(){
        SaveMobConfig mobConfig = new SaveMobConfig(this, this.mobs);
    }

    private void registerCommands() {
        this.getCommand("disableMob").setExecutor(new DisableMob(this, this.mobs));
        this.getCommand("showAvailableMobs").setExecutor(new ShowAvailableMobs(this, this.mobs));
        this.getCommand("showDisabledMobs").setExecutor(new ShowDisabledMobs(this, this.mobs));
        this.getCommand("enableMob").setExecutor(new EnableMob(this, this.mobs));
    }

    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new CreatureSpawn(mobs), this);
    }
}
