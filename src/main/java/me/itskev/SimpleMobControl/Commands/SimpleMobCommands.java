package me.itskev.SimpleMobControl.Commands;

import me.itskev.SimpleMobControl.MobConfig.Mobs;
import me.itskev.SimpleMobControl.MobConfig.SaveMobConfig;
import me.itskev.SimpleMobControl.SimpleMobControl;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Simple Mob Control Commands class, all in one now. /simplemobcontrol to show help
 */
public class SimpleMobCommands implements CommandExecutor, TabCompleter {

    private SimpleMobControl plugin;
    private String[] commands = {"disabledMobs", "availableMobs", "disable", "disableAll", "enable", "enableAll"};

    public SimpleMobCommands(SimpleMobControl plugin) {
        this.plugin = plugin;

    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        Player player = null;
        if (commandSender instanceof Player) {
            player = (Player) commandSender;
        }

        String world = "";
        if (commandContainsWorldname(args)) {
            world = args[args.length - 1];
        } else if (player != null) {
            world = player.getWorld().getName();
        }

        //Show help
        if (args.length == 0) {
            showHelp(commandSender);
        } else if (!world.equals("")) {
            if (args[0].equalsIgnoreCase("disabledMobs") && commandSender.hasPermission("simplemobcontrol.info")) {
                showDisabledMobs(commandSender, world);
            } else if (commandSender.hasPermission("simplemobcontrol.configure")) {
                if (args[0].equalsIgnoreCase("availableMobs")) {
                    showAvailableMobs(commandSender, world);
                } else if (args[0].equalsIgnoreCase("disableAll")) {
                    disableAllMobs(commandSender, world);
                } else if (args[0].equalsIgnoreCase("enableAll")) {
                    enableAllMobs(commandSender, world);
                } else if (args[0].equalsIgnoreCase("disable")) {
                    if (args.length < 2 || !this.plugin.getWorlds().get(world).getAvailableMobs().contains(args[1])) {
                        sendMessage(commandSender, "Mob not available!");
                    } else if (this.plugin.getWorlds().get(world).getDisabledMobs().contains(args[1])) {
                        sendMessage(commandSender, args[1] + " is already disabled!");
                    } else {
                        this.plugin.getWorlds().get(world).addDisabledMob(args[1]);
                        new SaveMobConfig(this.plugin);
                        sendMessage(commandSender, args[1] + " was successfully added to the list of disabled Mobs!");
                    }
                } else if (args[0].equalsIgnoreCase("enable")) {
                    if (!this.plugin.getWorlds().get(world).getAvailableMobs().contains(args[1])) {
                        sendMessage(commandSender, "Mob not available!");
                    } else if (!this.plugin.getWorlds().get(world).getDisabledMobs().contains(args[1])) {
                        sendMessage(commandSender, args[1] + " is not disabled!");
                    } else {
                        this.plugin.getWorlds().get(world).removeDisabledMob(args[1]);
                        new SaveMobConfig(this.plugin);
                        sendMessage(commandSender, args[1] + " was successfully removed from the list of disabled Mobs!");
                    }
                } else {
                    showHelp(commandSender);
                }
            } else {
                sendMessage(commandSender, "You don't have permissions to use this command!");
            }
        } else {
            sendMessage(commandSender, "Add the world name you want to work with at the end of the command!");
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String label, String[] args) {

        String world = "";
        for (Map.Entry<String, Mobs> worlds : this.plugin.getWorlds().entrySet()) {
            world = worlds.getKey();
        }


        if (args.length == 1) {
            List<String> commandList = new ArrayList<>();
            if (!args[0].equals("")) {
                for (String s : commands) {
                    if (s.toLowerCase().startsWith(args[0].toLowerCase())) {
                        commandList.add(s);
                    }
                }
            } else {
                Collections.addAll(commandList, commands);
            }
            Collections.sort(commandList);
            return commandList;
        } else if (args.length == 2) {
            List<String> entities = new ArrayList<>();
            if (!args[1].equals("")) {
                for (String mob : this.plugin.getWorlds().get(world).getAvailableMobs()) {
                    if (mob.toLowerCase().startsWith(args[1].toLowerCase())) {
                        entities.add(mob);
                    }
                }
            } else {
                entities.addAll(this.plugin.getWorlds().get(world).getAvailableMobs());
            }
            Collections.sort(entities);
            return entities;
        }
        return null;
    }

    private void sendMessage(CommandSender commandSender, String message) {
        Player player = null;
        if (commandSender instanceof Player) {
            player = (Player) commandSender;
        }

        if (player != null) {
            player.sendMessage(SimpleMobControl.prefix + message);
        } else {
            plugin.getServer().getConsoleSender().sendMessage(SimpleMobControl.prefix + message);
        }
    }

    private void showHelp(CommandSender commandSender) {

        if (commandSender.hasPermission("simplemobcontrol.info")) {
            sendMessage(commandSender, "Simple Mob Control Help");
            sendMessage(commandSender, "Alias: /smc");
            sendMessage(commandSender, "/simplemobcontrol disabledMobs - Shows disabled mobs");
        }
        if (commandSender.hasPermission("simplemobcontrol.configure")) {
            sendMessage(commandSender, "/simplemobcontrol availableMobs - Shows available mobs");
            sendMessage(commandSender, "/simplemobcontrol disableAll " + ChatColor.YELLOW + "[worldname]" + ChatColor.WHITE + " - Disables all mobs");
            sendMessage(commandSender, "/simplemobcontrol disable [mobName] " + ChatColor.YELLOW + "[worldname]" + ChatColor.WHITE + " - Disables a specific mob");
            sendMessage(commandSender, "/simplemobcontrol enableAll " + ChatColor.YELLOW + "[worldname]" + ChatColor.WHITE + " - Enables all mobs");
            sendMessage(commandSender, "/simplemobcontrol enable [mobName] " + ChatColor.YELLOW + "[worldname]" + ChatColor.WHITE + " - Enables a specific mob");
        }
    }

    private void showDisabledMobs(CommandSender commandSender, String world) {
        sendMessage(commandSender, "Disabled Mobs in " + world + ":");
        for (String mob : this.plugin.getWorlds().get(world).getDisabledMobs()) {
            sendMessage(commandSender, mob);
        }
    }

    private void showAvailableMobs(CommandSender commandSender, String world) {
        sendMessage(commandSender, "Available Mobs:");
        for (String mob : this.plugin.getWorlds().get(world).getAvailableMobs()) {
            sendMessage(commandSender, mob);
        }
    }

    private void disableAllMobs(CommandSender commandSender, String world) {
        this.plugin.getWorlds().get(world).disableAllMobs();
        new SaveMobConfig(this.plugin);
        sendMessage(commandSender, "Disabled all mobs!");
    }

    private void enableAllMobs(CommandSender commandSender, String world) {
        this.plugin.getWorlds().get(world).enableAllMobs();
        new SaveMobConfig(this.plugin);
        sendMessage(commandSender, "Enabled all mobs!");
    }

    private boolean commandContainsWorldname(String[] args) {
        if (args.length == 0) {
            return false;
        }
        for (Map.Entry<String, Mobs> world : this.plugin.getWorlds().entrySet()) {
            if (world.getKey().equalsIgnoreCase(args[args.length - 1])) {
                return true;
            }
        }
        return false;
    }
}
