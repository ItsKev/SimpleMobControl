package com.xkev.SimpleMobControl.MobConfig;


import java.util.HashSet;

/**
 * Mob Class with all the available and disabled Mobs.
 */
public class Mobs {

    private HashSet<String> availableMobs;
    private HashSet<String> disabledMobs;

    public Mobs() {

        this.availableMobs = new HashSet<>();
        this.disabledMobs = new HashSet<>();

        addStandardMobs();
    }

    /**
     * Disables a specific mob
     *
     * @param mob Mob to disable
     */
    public void addDisabledMob(String mob) {
        if (this.availableMobs.contains(mob)) {
            this.disabledMobs.add(mob);
        }
    }

    /**
     * Enables a specific mob
     *
     * @param mob Mob to enable
     */
    public void removeDisabledMob(String mob) {
        if (this.availableMobs.contains(mob)) {
            this.disabledMobs.remove(mob);
        }
    }

    /**
     * Disables all mobs available
     */
    public void disableAllMobs() {
        for (String mob : this.availableMobs) {
            this.disabledMobs.add(mob);
        }
    }

    /**
     * Enable all mobs available
     */
    public void enableAllMobs() {
        this.disabledMobs.clear();
    }

    public HashSet<String> getDisabledMobs() {
        return this.disabledMobs;
    }

    public HashSet<String> getAvailableMobs() {
        return this.availableMobs;
    }

    private void addStandardMobs() {

        //Passive mobs
        this.availableMobs.add("Bat");
        this.availableMobs.add("Chicken");
        this.availableMobs.add("Cow");
        this.availableMobs.add("Mooshroom");
        this.availableMobs.add("Pig");
        this.availableMobs.add("Rabbit");
        this.availableMobs.add("Sheep");
        this.availableMobs.add("SkeletonHorse");
        this.availableMobs.add("Squid");
        this.availableMobs.add("Villager");


        //Neutral mobs
        this.availableMobs.add("CaveSpider");
        this.availableMobs.add("Enderman");
        this.availableMobs.add("PolarBear");
        this.availableMobs.add("Spider");
        this.availableMobs.add("ZombiePigman");

        //Hostile mobs
        this.availableMobs.add("Blaze");
        this.availableMobs.add("Creeper");
        this.availableMobs.add("ElderGuardian");
        this.availableMobs.add("Endermite");
        this.availableMobs.add("Evoker");
        this.availableMobs.add("Ghast");
        this.availableMobs.add("Guardian");
        this.availableMobs.add("Husk");
        this.availableMobs.add("MagmaCube");
        this.availableMobs.add("Shulker");
        this.availableMobs.add("Silverfish");
        this.availableMobs.add("Skeleton");
        this.availableMobs.add("SkeletonHorse");
        this.availableMobs.add("Slime");
        this.availableMobs.add("Stray");
        this.availableMobs.add("Vex");
        this.availableMobs.add("Vindicator");
        this.availableMobs.add("Witch");
        this.availableMobs.add("WitherSkeleton");
        this.availableMobs.add("Zombie");
        this.availableMobs.add("ZombieVillager");

        //Tameable mobs
        this.availableMobs.add("Donkey");
        this.availableMobs.add("Horse");
        this.availableMobs.add("Llama");
        this.availableMobs.add("Mule");
        this.availableMobs.add("Ocelot");
        this.availableMobs.add("Wolf");

        //Utility mobs
        this.availableMobs.add("IronGolem");
        this.availableMobs.add("SnowGolem");

        //Boss mobs
        this.availableMobs.add("EnderDragon");
        this.availableMobs.add("Wither");
    }

}
