package io.github.itskev.simplemobcontrol.gui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

public interface GUI {

  void previousPage();

  void nextPage();

  Inventory getInventory();

  void fillBarWith(ItemStack item);

  void openInventory(Player player);

  void addClickables(List<Clickable> clickables);

  void addClickable(ItemStack itemStack, int slot, GUICallback callback);

  void removeClickable(int slot);

  Map<ItemStack, GUICallback> getClickables();

  void refillSlots();

}
