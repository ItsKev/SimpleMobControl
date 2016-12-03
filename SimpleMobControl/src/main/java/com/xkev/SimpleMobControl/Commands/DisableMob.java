package com.xkev.SimpleMobControl.Commands;

import com.xkev.SimpleMobControl.MobConfig.Mobs;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Command /disablemob <mob> to disable specific or all mobs
 */
public class DisableMob implements CommandExecutor {

    private Mobs mobs;

    public DisableMob(Mobs mobs) {
        this.mobs = mobs;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (args.length == 1) {
            if(args[0].equals("all")){
                commandSender.sendMessage("Disable all mobs!");
                mobs.disableAllMobs();
            }
            else if (!mobs.getAvailableMobs().contains(args[0])) {
                commandSender.sendMessage("Mob not available! (Case-Sensitive)");
            } else if (mobs.getDisabledMobs().contains(args[0])) {
                commandSender.sendMessage(args[0] + " is already disabled!");
            } else {
                mobs.addDisabledMob(args[0]);
                commandSender.sendMessage(args[0] + " was successfully added to the list of disabled Mobs!");
            }
            return true;
        }

        return false;
    }
}
