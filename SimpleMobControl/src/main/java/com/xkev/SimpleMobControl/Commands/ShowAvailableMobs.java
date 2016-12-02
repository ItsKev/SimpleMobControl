package com.xkev.SimpleMobControl.Commands;

import com.xkev.SimpleMobControl.MobConfig.Mobs;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by kevin on 02.12.2016.
 */
public class ShowAvailableMobs implements CommandExecutor{

    private Mobs mobs;
    private JavaPlugin javaPlugin;

    public ShowAvailableMobs(JavaPlugin javaPlugin, Mobs mobs){
        this.mobs = mobs;
        this.javaPlugin = javaPlugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = null;
        if (commandSender instanceof Player) {
            player = (Player) commandSender;
        }

        if(player != null){
            player.sendMessage("Available Mobs:");
            for(String mob : mobs.getAvailableMobs()){
                player.sendMessage(ChatColor.GREEN + mob);
            }
        }
        else{
            javaPlugin.getLogger().info("Available Mobs:");
            for(String mob : mobs.getAvailableMobs()){
                javaPlugin.getLogger().info(mob);
            }
        }
        return true;
    }
}
