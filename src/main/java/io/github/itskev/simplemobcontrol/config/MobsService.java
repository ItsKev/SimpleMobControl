package io.github.itskev.simplemobcontrol.config;

import lombok.Getter;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class MobsService {

  private Map<String, Mobs> worlds;

  public MobsService(final Plugin plugin) {
    this.worlds = new HashMap<>();
    List<World> availableWorlds = plugin.getServer().getWorlds();
    for (World world : availableWorlds) {
      worlds.put(world.getName(), new Mobs());
    }
  }
}
