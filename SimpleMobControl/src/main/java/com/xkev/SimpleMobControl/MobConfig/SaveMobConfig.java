package com.xkev.SimpleMobControl.MobConfig;

import org.bukkit.plugin.java.JavaPlugin;


/**
 * Save all current Mobs in the DisabledMobs List to the Config file.
 */
public class SaveMobConfig {

    public SaveMobConfig(JavaPlugin javaPlugin, Mobs mobs) {

        javaPlugin.getConfig().set("Disabled Mobs", mobs.getDisabledMobs());
        javaPlugin.saveConfig();
    }
}
