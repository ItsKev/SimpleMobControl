package io.github.itskev.simplemobcontrol.commands;

import io.github.itskev.simplemobcontrol.commands.subcommands.DisableCommand;
import io.github.itskev.simplemobcontrol.commands.subcommands.EnableCommand;
import io.github.itskev.simplemobcontrol.commands.subcommands.InfoCommands;
import io.github.itskev.simplemobcontrol.config.Mobs;
import io.github.itskev.simplemobcontrol.config.MobsService;
import io.github.itskev.simplemobcontrol.gui.GUIService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.github.itskev.simplemobcontrol.util.MessageUtil.sendMessage;

/**
 * Simple Mob Control Commands class, all in one now. /simplemobcontrol to show help
 */
public class SimpleMobCommands implements CommandExecutor, TabCompleter {

  private static String[] commands = {"help", "disabledMobs", "availableMobs", "disable", "disableAll", "enable", "enableAll"};

  private final MobsService mobsService;
  private final GUIService guiService;
  private final InfoCommands infoCommands;
  private final EnableCommand enableCommand;
  private final DisableCommand disableCommand;

  public SimpleMobCommands(final Plugin plugin, final MobsService mobsService, final GUIService guiService) {
    this.mobsService = mobsService;
    this.guiService = guiService;
    infoCommands = new InfoCommands(plugin, mobsService);
    enableCommand = new EnableCommand(plugin, mobsService);
    disableCommand = new DisableCommand(plugin, mobsService);
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!sender.hasPermission("simplemobcontrol.info")) {
      return true;
    }

    Player player = null;
    if (sender instanceof Player) {
      player = (Player) sender;
    }

    String world = "";
    if (commandContainsWorld(args)) {
      world = args[args.length - 1];
    } else if (player != null) {
      world = player.getWorld().getName();
    }

    if (args.length == 0) {
      if (player != null && sender.hasPermission("simplemobcontrol.configure")) {
        guiService.createAvailableMobsGUI(player, world).openInventory(player);
      } else {
        infoCommands.showHelp(sender);
      }
      return true;
    }

    if (args[0].equalsIgnoreCase("help")) {
      infoCommands.showHelp(sender);
    }

    if (world.equals("")) {
      sendMessage(sender, "Add the world name you want to work with at the end of the command!");
      return true;
    }

    if (args[0].equalsIgnoreCase("disabledMobs")) {
      infoCommands.showDisabledMobs(sender, world);
    } else if (sender.hasPermission("simplemobcontrol.configure")) {
      switch (args[0].toLowerCase()) {
        case "availablemobs":
          infoCommands.showAvailableMobs(sender, world);
          break;

        case "enableall":
          enableCommand.enableAllMobs(sender, world);
          break;

        case "enable":
          enableCommand.enable(sender, args, world);
          break;

        case "disableall":
          disableCommand.disableAllMobs(sender, world);
          break;

        case "disable":
          disableCommand.disable(sender, args, world);
          break;

        default:
          infoCommands.showHelp(sender);
      }
    } else {
      sendMessage(sender, "You don't have permissions to use this command!");
    }
    return true;
  }

  @Override
  public List<String> onTabComplete(CommandSender commandSender, Command command, String label, String[] args) {
    List<String> tabCompletions = new ArrayList<>();
    if (args.length == 1) {
      tabCompletions = Arrays.stream(commands).collect(Collectors.toList());
    } else if (args.length == 2) {
      if (args[0].equalsIgnoreCase("enable") || args[0].equalsIgnoreCase("disable")) {
        tabCompletions = getAllMonsters();
      } else if (args[0].equalsIgnoreCase("disabledMobs")
          || args[0].equalsIgnoreCase("enableAll")
          || args[0].equalsIgnoreCase("disableAll")
          || args[0].equalsIgnoreCase("availableMobs")) {
        tabCompletions = new ArrayList<>(mobsService.getWorlds().keySet());
      }
    } else if (args.length == 3) {
      if (!args[1].equalsIgnoreCase("availableMobs")) {
        tabCompletions = new ArrayList<>(mobsService.getWorlds().keySet());
      }
    }

    String lastArgument = args[args.length - 1];
    return tabCompletions.stream()
        .filter(s -> s.toLowerCase().contains(lastArgument.toLowerCase()))
        .sorted(Comparator.naturalOrder())
        .collect(Collectors.toList());
  }

  private boolean commandContainsWorld(String[] args) {
    if (args.length == 0) {
      return false;
    }
    for (Map.Entry<String, Mobs> world : mobsService.getWorlds().entrySet()) {
      if (world.getKey().equalsIgnoreCase(args[args.length - 1])) {
        return true;
      }
    }
    return false;
  }

  private List<String> getAllMonsters() {
    return Arrays.stream(EntityType.values())
        .filter(entityType -> entityType.isSpawnable() && entityType.isAlive())
        .map(Enum::name)
        .sorted(Comparator.naturalOrder())
        .collect(Collectors.toList());
  }
}
