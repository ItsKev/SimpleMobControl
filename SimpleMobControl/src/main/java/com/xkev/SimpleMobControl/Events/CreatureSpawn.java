package com.xkev.SimpleMobControl.Events;

import com.xkev.SimpleMobControl.MobConfig.Mobs;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

/**
 * Event which triggers every time a creature is spawned.
 */
public class CreatureSpawn implements Listener {

    private final Mobs mobs;

    public CreatureSpawn(Mobs mobs) {
        this.mobs = mobs;
    }

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {

        if (this.mobs.getDisabledMobs().contains(event.getEntity().getName().replaceAll("\\s+",""))) {
            event.setCancelled(true);
        }
    }
}
