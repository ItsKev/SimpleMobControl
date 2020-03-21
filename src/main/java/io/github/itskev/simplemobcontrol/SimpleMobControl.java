package io.github.itskev.simplemobcontrol;

import io.github.itskev.simplemobcontrol.commands.SimpleMobCommands;
import io.github.itskev.simplemobcontrol.events.CreatureSpawn;
import io.github.itskev.simplemobcontrol.mobconfig.Mobs;
import io.github.itskev.simplemobcontrol.mobconfig.ReadMobConfig;
import io.github.itskev.simplemobcontrol.mobconfig.SaveMobConfig;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleMobControl extends JavaPlugin {

  private Map<String, Mobs> worlds;
  public static String prefix = ChatColor.GREEN + "[SimpleMobControl] " + ChatColor.WHITE;

  @Override
  public void onEnable() {
    this.worlds = new HashMap<>();
    List<World> availableWorlds = getServer().getWorlds();
    for (World world : availableWorlds) {
      worlds.put(world.getName(), new Mobs());
    }

    this.readMobConfig();
    this.saveMobConfig();
    this.registerCommands();
    this.registerEvents();
  }

  @Override
  public void onDisable() {

  }

  public Map<String, Mobs> getWorlds() {
    return worlds;
  }

  private void readMobConfig() {
    new ReadMobConfig(this);
  }

  private void saveMobConfig() {
    new SaveMobConfig(this);
  }

  private void registerCommands() {
    this.getCommand("SimpleMobControl").setExecutor(new SimpleMobCommands(this));
    this.getCommand("SimpleMobControl").setTabCompleter(new SimpleMobCommands(this));
  }

  private void registerEvents() {
    PluginManager pm = getServer().getPluginManager();
    pm.registerEvents(new CreatureSpawn(this), this);
  }
}
