package com.xkev.SimpleMobControl.Commands;

import com.xkev.SimpleMobControl.MobConfig.Mobs;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Command to disable specific mobs
 */
public class DisableMob implements CommandExecutor{

    private JavaPlugin javaPlugin;
    private Mobs mobs;

    public DisableMob(JavaPlugin javaPlugin, Mobs mobs){
        this.javaPlugin = javaPlugin;
        this.mobs = mobs;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = null;
        if (commandSender instanceof Player) {
            player = (Player) commandSender;
        }

        if(player != null){
            javaPlugin.getLogger().info("Available Mobs:");
            for(String mob : mobs.getAvailableMobs()){
                javaPlugin.getLogger().info(ChatColor.GREEN + mob);
            }
        }


        return true;
    }
}
