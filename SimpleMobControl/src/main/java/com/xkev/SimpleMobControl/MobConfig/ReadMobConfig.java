package com.xkev.SimpleMobControl.MobConfig;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Reads all the Disabled Mobs out of the Config file.
 */
public class ReadMobConfig {

    public ReadMobConfig(JavaPlugin plugin, Mobs mobs) {

        plugin.getConfig().options().copyDefaults(true);
        for (String disabledMob : plugin.getConfig().getStringList("Disabled Mobs")) {
            mobs.addDisabledMobs(disabledMob);
        }

        plugin.saveConfig();
    }

}
