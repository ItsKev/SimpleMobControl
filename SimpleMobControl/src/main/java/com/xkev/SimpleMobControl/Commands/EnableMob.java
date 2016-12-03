package com.xkev.SimpleMobControl.Commands;

import com.xkev.SimpleMobControl.MobConfig.Mobs;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Command /enablemob <mob> to enable specific or all mobs
 */
public class EnableMob implements CommandExecutor{

    private Mobs mobs;

    public EnableMob(Mobs mobs) {
        this.mobs = mobs;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (args.length == 1) {
            if(args[0].equals("all")){
                commandSender.sendMessage("Enable all mobs!");
                mobs.enableAllMobs();
            }
            else if (!mobs.getAvailableMobs().contains(args[0])) {
                commandSender.sendMessage("Mob not available! (Case-Sensitive)");
            } else if (!mobs.getDisabledMobs().contains(args[0])) {
                commandSender.sendMessage(args[0] + " is not disabled!");
            } else {
                mobs.removeDisabledMob(args[0]);
                commandSender.sendMessage(args[0] + " was successfully removed from the list of disabled Mobs!");
            }
            return true;
        }

        return false;
    }
}
