package io.github.itskev.simplemobcontrol.config;

import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * Save all current Mobs in the DisabledMobs List to the Config file.
 */
public class SaveMobConfig {

  public SaveMobConfig(final Plugin plugin, final MobsService mobsService) {
    for (Map.Entry<String, Mobs> world : mobsService.getWorlds().entrySet()) {
      List<String> disabledMobs = new ArrayList<>(world.getValue().getDisabledMobs());
      Collections.sort(disabledMobs);

      plugin.getConfig().set(world.getKey() + ".Disabled Mobs", disabledMobs);
      plugin.saveConfig();
    }
  }
}
