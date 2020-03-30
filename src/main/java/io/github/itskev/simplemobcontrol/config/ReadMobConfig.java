package io.github.itskev.simplemobcontrol.config;

import org.bukkit.plugin.Plugin;

import java.util.Map;

/**
 * Reads all the Disabled Mobs out of the Config file.
 */
public class ReadMobConfig {

  public ReadMobConfig(final Plugin plugin, final MobsService mobsService) {
    plugin.getConfig().options().copyDefaults(true);
    for (Map.Entry<String, Mobs> world : mobsService.getWorlds().entrySet()) {
      for (String disabledMob : plugin.getConfig().getStringList(world.getKey() + ".Disabled Mobs")) {
        world.getValue().addDisabledMob(disabledMob);
      }
    }
    plugin.saveConfig();
  }
}
