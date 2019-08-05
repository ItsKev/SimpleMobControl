package me.itskev.SimpleMobControl.MobConfig;

import org.bukkit.entity.EntityType;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Mob Class with all the available and disabled Mobs.
 */
public class Mobs {

    private Set<String> availableMobs;
    private Set<String> disabledMobs;

    public Mobs() {
        this.availableMobs = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        this.disabledMobs = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);

        addStandardMobs();
    }

    public void addDisabledMob(String mob) {
        if (this.availableMobs.contains(mob)) {
            this.disabledMobs.add(mob);
        }
    }

    public void removeDisabledMob(String mob) {
        if (this.availableMobs.contains(mob)) {
            this.disabledMobs.remove(mob);
        }
    }

    public void disableAllMobs() {
        this.disabledMobs.addAll(this.availableMobs);
    }

    public void enableAllMobs() {
        this.disabledMobs.clear();
    }

    public Set<String> getDisabledMobs() {
        return this.disabledMobs;
    }

    public Set<String> getAvailableMobs() {
        return this.availableMobs;
    }

    private void addStandardMobs() {
        List<EntityType> monsters = Arrays.stream(EntityType.values())
                .filter(entityType -> entityType.isSpawnable() && entityType.isAlive())
                .sorted(Comparator.comparing(Enum::name))
                .collect(Collectors.toList());
        monsters.forEach(entityType -> availableMobs.add(entityType.name()));
    }

}
