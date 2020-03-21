package io.github.itskev.simplemobcontrol.mobconfig;

import io.github.itskev.simplemobcontrol.SimpleMobControl;

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
