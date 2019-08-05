package me.itskev.SimpleMobControl.MobConfig;

import me.itskev.SimpleMobControl.SimpleMobControl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * Save all current Mobs in the DisabledMobs List to the Config file.
 */
public class SaveMobConfig {

    public SaveMobConfig(SimpleMobControl javaPlugin) {

        for(Map.Entry<String, Mobs> world : javaPlugin.getWorlds().entrySet()){
            List<String> disabledMobs = new ArrayList<>(world.getValue().getDisabledMobs());
            Collections.sort(disabledMobs);

            javaPlugin.getConfig().set(world.getKey() + ".Disabled Mobs", disabledMobs);
            javaPlugin.saveConfig();
        }

    }
}
