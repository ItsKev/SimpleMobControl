package io.github.itskev.simplemobcontrol.listener;

import io.github.itskev.simplemobcontrol.config.Mobs;
import io.github.itskev.simplemobcontrol.config.MobsService;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

/**
 * Event which triggers every time a creature is spawned.
 */
@RequiredArgsConstructor
public class CreatureListener implements Listener {

  private final MobsService mobsService;

  @EventHandler
  public void onCreatureSpawn(CreatureSpawnEvent event) {
    String world = event.getLocation().getWorld().getName();
    Mobs mobs = this.mobsService.getWorlds().get(world);
    if (mobs != null && mobs.getDisabledMobs().contains(event.getEntityType().name())) {
      event.setCancelled(true);
    }
  }
}
