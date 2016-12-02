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

    public void addDisabledMobs(String mob) {
        if (availableMobs.contains(mob)) {
            disabledMobs.add(mob);
        }
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
        availableMobs.add("MushroomCow");
        availableMobs.add("Pig");
        availableMobs.add("Rabbit");
        availableMobs.add("Sheep");
        availableMobs.add("SkeletonHorse");
        availableMobs.add("Squid");


        //Neutral mobs
        availableMobs.add("CaveSpider");
        availableMobs.add("Enderman");
        availableMobs.add("PolarBear");
        availableMobs.add("Spider");
        availableMobs.add("PigZombie");

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
        availableMobs.add("SnowMan");

        //Boss mobs
        availableMobs.add("EnderDragon");
        availableMobs.add("Wither");
    }

}
