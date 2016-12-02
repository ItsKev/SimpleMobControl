package com.xkev.SimpleMobControl.Commands;

import com.xkev.SimpleMobControl.MobConfig.Mobs;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Command /disablemob <mob> to disable specific mobs
 */
public class DisableMob implements CommandExecutor {

    private Mobs mobs;

    public DisableMob(Mobs mobs) {
        this.mobs = mobs;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (args.length == 1) {
            if (!mobs.getAvailableMobs().contains(args[0])) {
                commandSender.sendMessage("Mob not available! (Case-Sensitive)");
            } else if (mobs.getDisabledMobs().contains(args[0])) {
                commandSender.sendMessage(args[0] + " is already disabled!");
            } else {
                mobs.addDisabledMobs(args[0]);
                commandSender.sendMessage(args[0] + " was successfully added to the list of disabled Mobs!");
            }
            return true;
        }


        return false;
    }
}
