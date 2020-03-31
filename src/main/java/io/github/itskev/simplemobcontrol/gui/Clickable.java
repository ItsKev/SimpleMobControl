package io.github.itskev.simplemobcontrol.gui;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bukkit.inventory.ItemStack;

@Data
@RequiredArgsConstructor
public class Clickable {

  private final ItemStack itemStack;
  private final GUICallback guiCallback;

}
