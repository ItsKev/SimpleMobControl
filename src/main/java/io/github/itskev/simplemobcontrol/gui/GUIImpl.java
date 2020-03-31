package io.github.itskev.simplemobcontrol.gui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GUIImpl implements GUI {

  private Inventory inventory;
  private Plugin plugin;
  private Map<ItemStack, GUICallback> clickables;
  private List<Clickable> slots;
  private int currentPage = 0;

  public GUIImpl(final Inventory inventory, final Plugin plugin) {
    this.inventory = inventory;
    this.plugin = plugin;
    clickables = new HashMap<>();
    slots = new ArrayList<>();
  }

  @Override
  public void previousPage() {
    if (currentPage > 0) {
      currentPage--;
    }
    refillSlots();
  }

  @Override
  public void nextPage() {
    int pages = (int) Math.ceil(slots.size() / (float) (inventory.getSize() - 9));
    if (currentPage + 1 < pages) {
      currentPage++;
    }
    refillSlots();
  }

  @Override
  public Inventory getInventory() {
    return inventory;
  }

  @Override
  public void fillBarWith(final ItemStack item) {
    List<Integer> slotsToFill = new ArrayList<>();
    for (int i = inventory.getSize() - 10; i < inventory.getSize(); i++) {
      slotsToFill.add(i);
    }

    slotsToFill.forEach(slot -> inventory.setItem(slot, item));
  }

  @Override
  public void openInventory(final Player player) {
    currentPage = 0;
    refillSlots();

    player.openInventory(inventory);
    GUIEventHandler.getInstance(plugin).addListener(player, this);
  }

  @Override
  public void addClickables(final List<Clickable> clickables) {
    slots = clickables;
  }

  @Override
  public void addClickable(final ItemStack itemStack, final int slot, final GUICallback callback) {
    inventory.setItem(slot, itemStack);
    clickables.putIfAbsent(itemStack, callback);
  }

  @Override
  public void removeClickable(final int slot) {
    ItemStack item = inventory.getItem(slot);
    inventory.setItem(slot, null);
    clickables.remove(item);
  }

  @Override
  public Map<ItemStack, GUICallback> getClickables() {
    return clickables;
  }

  @Override
  public void refillSlots() {
    List<Integer> availableSlots = new ArrayList<>();
    int rows = inventory.getSize() / 9;
    for (int i = 0; i < rows - 1; i++) {
      for (int j = 0; j < 9; j++) {
        availableSlots.add(i * 9 + j);
      }
    }
    availableSlots.forEach(slot -> inventory.setItem(slot, null));

    List<Clickable> itemStacks = slots.stream().skip(currentPage * availableSlots.size())
        .limit(availableSlots.size()).collect(Collectors.toList());
    for (int i = 0; i < availableSlots.size(); i++) {
      if (itemStacks.size() <= i) {
        break;
      }
      Clickable clickable = itemStacks.get(i);
      inventory.setItem(availableSlots.get(i), clickable.getItemStack());
      clickables.put(clickable.getItemStack(), clickable.getGuiCallback());
    }
  }
}
