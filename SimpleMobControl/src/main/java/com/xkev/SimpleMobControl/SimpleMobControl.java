package com.xkev.SimpleMobControl;

import com.xkev.SimpleMobControl.Commands.*;
import com.xkev.SimpleMobControl.Events.CreatureSpawn;
import com.xkev.SimpleMobControl.MobConfig.Mobs;
import com.xkev.SimpleMobControl.MobConfig.ReadMobConfig;
import com.xkev.SimpleMobControl.MobConfig.SaveMobConfig;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * SimpleMobControl Method
 */
public class SimpleMobControl extends JavaPlugin {


    private Mobs mobs;
    public static String prefix = "§a[SimpleMobControl]§f ";

    @Override
    public void onEnable() {
        this.mobs = new Mobs();

        this.readMobConfig();
        this.registerCommands();
        this.registerEvents();
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
        this.getCommand("SimpleMobControl").setExecutor(new SimpleMobCommands(this, this.mobs));
    }

    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new CreatureSpawn(mobs), this);
    }
}
