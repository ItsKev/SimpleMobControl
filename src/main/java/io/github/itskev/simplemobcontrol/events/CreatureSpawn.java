package io.github.itskev.simplemobcontrol.events;

import io.github.itskev.simplemobcontrol.SimpleMobControl;
import io.github.itskev.simplemobcontrol.mobconfig.Mobs;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

/**
 * Event which triggers every time a creature is spawned.
 */
public class CreatureSpawn implements Listener {

  private final SimpleMobControl plugin;

  public CreatureSpawn(SimpleMobControl plugin) {
    this.plugin = plugin;
  }

  @EventHandler
  public void onCreatureSpawn(CreatureSpawnEvent event) {

    String world = event.getLocation().getWorld().getName();
    Mobs mobs = this.plugin.getWorlds().get(world);
    if (mobs != null) {
      if (mobs.getDisabledMobs().contains(event.getEntityType().name())) {
        event.setCancelled(true);
      }
    }

  }
}
