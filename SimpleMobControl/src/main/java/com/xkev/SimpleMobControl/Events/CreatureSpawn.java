package com.xkev.SimpleMobControl.Events;

import com.xkev.SimpleMobControl.Main;
import com.xkev.SimpleMobControl.MobConfig.Mobs;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

/**
 * Event which triggers every time a creature is spawned.
 */
public class CreatureSpawn implements Listener {

    private final Main plugin;
    private final Mobs mobs;

    public CreatureSpawn(Main plugin, Mobs mobs) {
        this.plugin = plugin;
        this.mobs = mobs;
    }

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        if (this.mobs.getDisabledMobs().contains(event.getEntity().getName())) {
            event.setCancelled(true);
        }
    }
}
