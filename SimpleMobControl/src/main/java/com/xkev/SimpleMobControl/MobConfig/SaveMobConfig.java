package com.xkev.SimpleMobControl.MobConfig;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Save all current Mobs in the DisabledMobs List to the Config file.
 */
public class SaveMobConfig {

    public SaveMobConfig(JavaPlugin javaPlugin, Mobs mobs) {

        List<String> disabledMobs = new ArrayList<>(mobs.getDisabledMobs());
        Collections.sort(disabledMobs);

        javaPlugin.getConfig().set("Disabled Mobs", disabledMobs);
        javaPlugin.saveConfig();
    }
}
