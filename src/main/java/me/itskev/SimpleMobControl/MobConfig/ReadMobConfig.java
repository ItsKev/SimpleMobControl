package me.itskev.SimpleMobControl.MobConfig;

import me.itskev.SimpleMobControl.SimpleMobControl;

import java.util.Map;

/**
 * Reads all the Disabled Mobs out of the Config file.
 */
public class ReadMobConfig {

    public ReadMobConfig(SimpleMobControl plugin) {

        plugin.getConfig().options().copyDefaults(true);
        for (Map.Entry<String, Mobs> world : plugin.getWorlds().entrySet()) {
            for (String disabledMob : plugin.getConfig().getStringList(world.getKey() + ".Disabled Mobs")) {
                world.getValue().addDisabledMob(disabledMob);
            }
        }
        plugin.saveConfig();
    }

}
