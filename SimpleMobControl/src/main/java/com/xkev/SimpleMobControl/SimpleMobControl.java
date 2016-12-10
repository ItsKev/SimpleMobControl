package com.xkev.SimpleMobControl;

import com.xkev.SimpleMobControl.Commands.*;
import com.xkev.SimpleMobControl.Events.CreatureSpawn;
import com.xkev.SimpleMobControl.MobConfig.Mobs;
import com.xkev.SimpleMobControl.MobConfig.ReadMobConfig;
import com.xkev.SimpleMobControl.MobConfig.SaveMobConfig;
import org.bukkit.World;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SimpleMobControl Method
 */
public class SimpleMobControl extends JavaPlugin {


    private Map<String, Mobs> worlds;
    public static String prefix = "§a[SimpleMobControl]§f ";

    @Override
    public void onEnable() {
        this.worlds = new HashMap<>();
        List<World> availableWorlds = getServer().getWorlds();
        for(World world:availableWorlds){
            worlds.put(world.getName(), new Mobs());
        }

        this.readMobConfig();
        this.saveMobConfig();
        this.registerCommands();
        this.registerEvents();
    }

    @Override
    public void onDisable() {

    }

    public Map<String, Mobs> getWorlds(){
        return worlds;
    }

    private void readMobConfig(){
        ReadMobConfig mobConfig = new ReadMobConfig(this);
    }

    private void saveMobConfig(){
        SaveMobConfig mobConfig = new SaveMobConfig(this);
    }

    private void registerCommands() {
        this.getCommand("SimpleMobControl").setExecutor(new SimpleMobCommands(this));
        this.getCommand("SimpleMobControl").setTabCompleter(new SimpleMobCommands(this));
    }

    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new CreatureSpawn(this), this);
    }
}
