package io.github.itskev.simplemobcontrol.config;

import lombok.Getter;
import org.bukkit.entity.EntityType;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Mob Class with all the available and disabled Mobs.
 */
@Getter
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

  private void addStandardMobs() {
    List<EntityType> monsters = Arrays.stream(EntityType.values())
        .filter(entityType -> entityType.isSpawnable() && entityType.isAlive())
        .sorted(Comparator.comparing(Enum::name))
        .collect(Collectors.toList());
    monsters.forEach(entityType -> availableMobs.add(entityType.name()));
  }
}
