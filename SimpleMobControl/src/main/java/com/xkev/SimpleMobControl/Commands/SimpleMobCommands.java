package com.xkev.SimpleMobControl.Commands;

import com.xkev.SimpleMobControl.MobConfig.Mobs;
import com.xkev.SimpleMobControl.MobConfig.SaveMobConfig;
import com.xkev.SimpleMobControl.SimpleMobControl;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Simple Mob Control Commands class, all in one now. /simplemobcontrol to show help
 */
public class SimpleMobCommands implements CommandExecutor {

    private JavaPlugin javaPlugin;
    private Mobs mobs;

    public SimpleMobCommands(JavaPlugin javaPlugin, Mobs mobs) {
        this.javaPlugin = javaPlugin;
        this.mobs = mobs;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {


        //Show help
        if (args.length == 0) {
            showHelp(commandSender);
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("disabledMobs") && commandSender.hasPermission("simplemobcontrol.info")) {
                showDisabledMobs(commandSender);
            } else if (commandSender.hasPermission("simplemobcontrol.configure")) {
                if (args[0].equalsIgnoreCase("availableMobs")) {
                    showAvailableMobs(commandSender);
                } else if (args[0].equalsIgnoreCase("disableall")) {
                    disableAllMobs(commandSender);
                } else if (args[0].equalsIgnoreCase("enableall")) {
                    enableAllMobs(commandSender);
                }
            } else {
                sendMessage(commandSender, "You don't have permissions to use this command!");
            }

        } else if (args.length == 2) {
            if (commandSender.hasPermission("simplemobcontrol.configure")) {
                if (args[0].equalsIgnoreCase("disable")) {
                    if (!this.mobs.getAvailableMobs().contains(args[1])) {
                        sendMessage(commandSender, "Mob not available! (Case-Sensitive)");
                    } else if (this.mobs.getDisabledMobs().contains(args[1])) {
                        sendMessage(commandSender, args[1] + " is already disabled!");
                    } else {
                        this.mobs.addDisabledMob(args[1]);
                        new SaveMobConfig(this.javaPlugin, this.mobs);
                        sendMessage(commandSender, args[1] + " was successfully added to the list of disabled Mobs!");
                    }
                } else if (args[0].equalsIgnoreCase("enable")) {
                    if (!this.mobs.getAvailableMobs().contains(args[1])) {
                        sendMessage(commandSender, "Mob not available! (Case-Sensitive)");
                    } else if (!this.mobs.getDisabledMobs().contains(args[1])) {
                        sendMessage(commandSender, args[1] + " is not disabled!");
                    } else {
                        this.mobs.removeDisabledMob(args[1]);
                        new SaveMobConfig(this.javaPlugin, this.mobs);
                        sendMessage(commandSender, args[1] + " was successfully removed from the list of disabled Mobs!");
                    }
                } else {
                    showHelp(commandSender);
                }
            } else {
                sendMessage(commandSender, "You don't have permissions to use this command!");
            }
        }


        return true;
    }

    private void sendMessage(CommandSender commandSender, String message) {
        Player player = null;
        if (commandSender instanceof Player) {
            player = (Player) commandSender;
        }

        if (player != null) {
            player.sendMessage(SimpleMobControl.prefix + message);
        } else {
            javaPlugin.getLogger().info(message);
        }
    }

    private void showHelp(CommandSender commandSender) {

        if (commandSender.hasPermission("simplemobcontrol.info")) {
            sendMessage(commandSender, "------------ Simple Mob Control Help ------------");
            sendMessage(commandSender, "/simplemobcontrol disabledMobs - Shows disabled mobs");
        }
        if (commandSender.hasPermission("simplemobcontrol.configure")) {
            sendMessage(commandSender, "/simplemobcontrol availableMobs - Shows available mobs");
            sendMessage(commandSender, "/simplemobcontrol disableall - Disables all mobs");
            sendMessage(commandSender, "/simplemobcontrol disable [mobName] - Disables a specific mob");
            sendMessage(commandSender, "/simplemobcontrol enableall - Enables all mobs");
            sendMessage(commandSender, "/simplemobcontrol enable [mobName] - Enables a specific mob");
        }
    }

    private void showDisabledMobs(CommandSender commandSender) {
        sendMessage(commandSender, "Disabled Mobs:");
        for (String mob : this.mobs.getDisabledMobs()) {
            sendMessage(commandSender, mob);
        }
    }

    private void showAvailableMobs(CommandSender commandSender) {
        sendMessage(commandSender, "Available Mobs:");
        for (String mob : this.mobs.getAvailableMobs()) {
            sendMessage(commandSender, mob);
        }
    }

    private void disableAllMobs(CommandSender commandSender) {
        this.mobs.disableAllMobs();
        new SaveMobConfig(this.javaPlugin, this.mobs);
        sendMessage(commandSender, "Disabled all mobs!");
    }

    private void enableAllMobs(CommandSender commandSender) {
        this.mobs.enableAllMobs();
        new SaveMobConfig(this.javaPlugin, this.mobs);
        sendMessage(commandSender, "Enabled all mobs!");
    }
}
