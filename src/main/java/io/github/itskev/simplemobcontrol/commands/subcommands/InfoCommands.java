package io.github.itskev.simplemobcontrol.commands.subcommands;

import io.github.itskev.simplemobcontrol.config.MobsService;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import static io.github.itskev.simplemobcontrol.util.MessageUtil.sendMessage;

@RequiredArgsConstructor
public class InfoCommands {

  private final Plugin plugin;
  private final MobsService mobsService;

  public void showHelp(CommandSender sender) {
    if (sender.hasPermission("simplemobcontrol.info")) {
      sendMessage(sender, "Simple Mob Control Help");
      sendMessage(sender, "Alias: /smc");
      sendMessage(sender, "/simplemobcontrol disabledMobs " + ChatColor.YELLOW + "[worldname]" + ChatColor.WHITE + " - Shows disabled mobs");
    }
    if (sender.hasPermission("simplemobcontrol.configure")) {
      sendMessage(sender, "/simplemobcontrol availableMobs - Shows available mobs");
      sendMessage(sender, "/simplemobcontrol disableAll " + ChatColor.YELLOW + "[worldname]" + ChatColor.WHITE + " - Disables all mobs");
      sendMessage(sender, "/simplemobcontrol disable [mobName] " + ChatColor.YELLOW + "[worldname]" + ChatColor.WHITE + " - Disables a specific mob");
      sendMessage(sender, "/simplemobcontrol enableAll " + ChatColor.YELLOW + "[worldname]" + ChatColor.WHITE + " - Enables all mobs");
      sendMessage(sender, "/simplemobcontrol enable [mobName] " + ChatColor.YELLOW + "[worldname]" + ChatColor.WHITE + " - Enables a specific mob");
    }
  }

  public void showDisabledMobs(CommandSender commandSender, String world) {
    sendMessage(commandSender, "Disabled Mobs in world " + world + ":");
    for (String mob : mobsService.getWorlds().get(world).getDisabledMobs()) {
      sendMessage(commandSender, mob);
    }
  }

  public void showAvailableMobs(CommandSender commandSender, String world) {
    sendMessage(commandSender, "Available Mobs:");
    for (String mob : mobsService.getWorlds().get(world).getAvailableMobs()) {
      sendMessage(commandSender, mob);
    }
  }
}
