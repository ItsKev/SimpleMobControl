package io.github.itskev.simplemobcontrol;

import io.github.itskev.simplemobcontrol.commands.SimpleMobCommands;
import io.github.itskev.simplemobcontrol.config.MobsService;
import io.github.itskev.simplemobcontrol.config.ReadMobConfig;
import io.github.itskev.simplemobcontrol.config.SaveMobConfig;
import io.github.itskev.simplemobcontrol.gui.GUIService;
import io.github.itskev.simplemobcontrol.listener.CreatureListener;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SimpleMobControl extends JavaPlugin {

  public static final String PREFIX = ChatColor.GREEN + "[SMC] " + ChatColor.WHITE;

  @Override
  public void onEnable() {
    MobsService mobsService = new MobsService(this);
    new ReadMobConfig(this, mobsService);
    new SaveMobConfig(this, mobsService);

    registerCommands(mobsService);
    registerEvents(mobsService);
  }

  private void registerCommands(final MobsService mobsService) {
    GUIService guiService = new GUIService(this, mobsService);
    getCommand("SimpleMobControl").setExecutor(new SimpleMobCommands(this, mobsService, guiService));
    getCommand("SimpleMobControl").setTabCompleter(new SimpleMobCommands(this, mobsService, guiService));
  }

  private void registerEvents(final MobsService mobsService) {
    PluginManager pluginManager = getServer().getPluginManager();
    pluginManager.registerEvents(new CreatureListener(mobsService), this);
  }
}
