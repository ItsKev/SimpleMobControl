package com.xkev.SimpleMobControl.MobConfig;

import org.bukkit.plugin.java.JavaPlugin;
import java.util.List;

/**
 * Created by kevin on 02.12.2016.
 */
public class ReadMobConfig {

    private List<String> disabledMobs;

    public ReadMobConfig(JavaPlugin plugin, Mobs mobs) {

        plugin.getConfig().options().copyDefaults(true);
        plugin.saveConfig();
        for (String disabledMob : plugin.getConfig().getStringList("Disabled Mobs")) {
            mobs.addDisabledMobs(disabledMob);
        }
        /*for(String disabledMob: mobs.getDisabledMobs()){
            plugin.getLogger().info(disabledMob);
        }*/



        /*World world = Bukkit.getWorld("world");
        plugin.getLogger().info(world.getName());
        CraftWorld craftWorld = (CraftWorld) world;
        for (LivingEntity mob : mobs){
            plugin.getLogger().info(mob.getName());
        }



        for (EntityType entityType : EntityType.values()) {
            if (entityType.){
                plugin.getLogger().info(entityType.name());
            }
            if(entityClass.isInstance(LivingEntity.class)){
                plugin.getLogger().info(entityType.name());
            }

        }*/

        plugin.saveConfig();
    }

}
