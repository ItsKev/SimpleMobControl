package com.xkev.SimpleMobControl.Commands;

import com.xkev.SimpleMobControl.Main;
import com.xkev.SimpleMobControl.MobConfig.Mobs;
import com.xkev.SimpleMobControl.MobConfig.SaveMobConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Command /disablemob <mob> to disable specific or all mobs
 */
public class DisableMob implements CommandExecutor {

    private Mobs mobs;
    private JavaPlugin javaPlugin;

    public DisableMob(JavaPlugin javaPlugin, Mobs mobs) {
        this.javaPlugin = javaPlugin;
        this.mobs = mobs;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player player = null;
        if (commandSender instanceof Player) {
            player = (Player) commandSender;
        }


        if (args.length == 1) {
            if (args[0].equals("all")) {
                mobs.disableAllMobs();
                new SaveMobConfig(this.javaPlugin, this.mobs);
                if (player != null) {
                    player.sendMessage(Main.prefix + "Disabled all mobs!");
                } else {
                    javaPlugin.getLogger().info("Disabled all mobs!");
                }
            } else if (!mobs.getAvailableMobs().contains(args[0])) {
                if (player != null) {
                    player.sendMessage(Main.prefix + "Mob not available! (Case-Sensitive)");
                } else {
                    javaPlugin.getLogger().info("Mob not available! (Case-Sensitive)");
                }
            } else if (mobs.getDisabledMobs().contains(args[0])) {
                if (player != null) {
                    player.sendMessage(Main.prefix + args[0] + " is already disabled!");
                } else {
                    javaPlugin.getLogger().info(args[0] + " is already disabled!");
                }
            } else {
                mobs.addDisabledMob(args[0]);
                new SaveMobConfig(this.javaPlugin, this.mobs);
                if (player != null) {
                    player.sendMessage(Main.prefix + args[0] + " was successfully added to the list of disabled Mobs!");
                } else {
                    javaPlugin.getLogger().info(args[0] + " was successfully added to the list of disabled Mobs!");
                }
            }
            return true;
        }

        return false;
    }
}
