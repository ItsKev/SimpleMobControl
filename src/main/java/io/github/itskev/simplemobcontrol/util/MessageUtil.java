package io.github.itskev.simplemobcontrol.util;

import io.github.itskev.simplemobcontrol.SimpleMobControl;
import org.bukkit.command.CommandSender;

public final class MessageUtil {

  private MessageUtil() {
  }

  public static void sendMessage(CommandSender sender, String message) {
    sender.sendMessage(SimpleMobControl.PREFIX + message);
  }
}
