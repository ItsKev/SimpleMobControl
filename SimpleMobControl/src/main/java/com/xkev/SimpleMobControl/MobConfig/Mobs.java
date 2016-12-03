package com.xkev.SimpleMobControl.MobConfig;


import java.util.ArrayList;
import java.util.List;

/**
 * Mob Class with all the available and disabled Mobs.
 */
public class Mobs {

    private List<String> availableMobs;
    private List<String> disabledMobs;

    public Mobs() {

        availableMobs = new ArrayList<>();
        disabledMobs = new ArrayList<>();

        addStandardMobs();
    }

    /**
     * Disables a specific mob
     * @param mob Mob to disable
     */
    public void addDisabledMob(String mob) {
        if (availableMobs.contains(mob)) {
            if(!disabledMobs.contains(mob)){
                disabledMobs.add(mob);
            }
        }
    }

    /**
     * Enables a specific mob
     * @param mob Mob to enable
     */
    public void removeDisabledMob(String mob){
        if (availableMobs.contains(mob)) {
            if(disabledMobs.contains(mob)){
                disabledMobs.remove(mob);
            }
        }
    }

    /**
     * Disables all mobs available
     */
    public void disableAllMobs(){
        for(String mob: availableMobs){
            if(!disabledMobs.contains(mob)){
                disabledMobs.add(mob);
            }
        }
    }

    /**
     * Enable all mobs available
     */
    public void enableAllMobs(){
        disabledMobs.clear();
    }

    public List<String> getDisabledMobs() {
        return disabledMobs;
    }

    public List<String> getAvailableMobs() {
        return availableMobs;
    }

    private void addStandardMobs() {

        //Passive mobs
        availableMobs.add("Bat");
        availableMobs.add("Chicken");
        availableMobs.add("Cow");
        availableMobs.add("Mooshroom");
        availableMobs.add("Pig");
        availableMobs.add("Rabbit");
        availableMobs.add("Sheep");
        availableMobs.add("SkeletonHorse");
        availableMobs.add("Squid");
        availableMobs.add("Villager");


        //Neutral mobs
        availableMobs.add("CaveSpider");
        availableMobs.add("Enderman");
        availableMobs.add("PolarBear");
        availableMobs.add("Spider");
        availableMobs.add("ZombiePigman");

        //Hostile mobs
        availableMobs.add("Blaze");
        availableMobs.add("Creeper");
        availableMobs.add("ElderGuardian");
        availableMobs.add("Endermite");
        availableMobs.add("Evoker");
        availableMobs.add("Ghast");
        availableMobs.add("Guardian");
        availableMobs.add("Husk");
        availableMobs.add("MagmaCube");
        availableMobs.add("Shulker");
        availableMobs.add("Silverfish");
        availableMobs.add("Skeleton");
        availableMobs.add("SkeletonHorse");
        availableMobs.add("Slime");
        availableMobs.add("Stray");
        availableMobs.add("Vex");
        availableMobs.add("Vindicator");
        availableMobs.add("Witch");
        availableMobs.add("WitherSkeleton");
        availableMobs.add("Zombie");
        availableMobs.add("ZombieVillager");

        //Tameable mobs
        availableMobs.add("Donkey");
        availableMobs.add("Horse");
        availableMobs.add("Llama");
        availableMobs.add("Mule");
        availableMobs.add("Ocelot");
        availableMobs.add("Wolf");

        //Utility mobs
        availableMobs.add("IronGolem");
        availableMobs.add("SnowGolem");

        //Boss mobs
        availableMobs.add("EnderDragon");
        availableMobs.add("Wither");
    }

}
