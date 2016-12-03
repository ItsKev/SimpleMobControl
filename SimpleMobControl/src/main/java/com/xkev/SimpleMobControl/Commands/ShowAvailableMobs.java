package com.xkev.SimpleMobControl.Commands;

import com.xkev.SimpleMobControl.Main;
import com.xkev.SimpleMobControl.MobConfig.Mobs;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Command /showavailablemobs shows all available mobs.
 */
public class ShowAvailableMobs implements CommandExecutor{

    private Mobs mobs;
    private JavaPlugin javaPlugin;

    public ShowAvailableMobs(JavaPlugin javaPlugin, Mobs mobs){
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
            player.sendMessage(Main.prefix + "Available Mobs:");
            for(String mob : this.mobs.getAvailableMobs()){
                player.sendMessage(Main.prefix + mob);
            }
        }
        else{
            this.javaPlugin.getLogger().info("Available Mobs:");
            for(String mob : this.mobs.getAvailableMobs()){
                this.javaPlugin.getLogger().info(mob);
            }
        }
        return true;
    }
}
