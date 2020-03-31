package io.github.itskev.simplemobcontrol.gui;

import io.github.itskev.simplemobcontrol.config.Mobs;
import io.github.itskev.simplemobcontrol.config.MobsService;
import io.github.itskev.simplemobcontrol.config.SaveMobConfig;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static io.github.itskev.simplemobcontrol.util.MessageUtil.sendMessage;

@RequiredArgsConstructor
public class GUIService {

  private final Plugin plugin;
  private final MobsService mobsService;

  public GUI createAvailableMobsGUI(final Player player, final String world) {
    Inventory inventory = Bukkit.createInventory(null, 36, ChatColor.DARK_GREEN + "SMC - World " + world);
    GUIImpl gui = new GUIImpl(inventory, plugin);
    gui.fillBarWith(createItem(Material.BLACK_STAINED_GLASS_PANE, ChatColor.GOLD + ""));

    setupSlots(player, gui, world);

    gui.addClickable(createItem(Material.GRASS_BLOCK, ChatColor.GREEN + "Change world"), 27, () -> {
      List<String> worlds = mobsService.getWorlds().keySet().stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
      int indexOf = worlds.indexOf(world) + 1;
      if (indexOf >= worlds.size()) {
        indexOf = 0;
      }
      createAvailableMobsGUI(player, worlds.get(indexOf)).openInventory(player);
    });
    gui.addClickable(createItem(Material.ARROW, ChatColor.BLUE + "Previous"), 29, gui::previousPage);
    gui.addClickable(createItem(Material.ARROW, ChatColor.BLUE + "Next"), 33, gui::nextPage);
    gui.addClickable(createItem(Material.BARRIER, ChatColor.RED + "Close"), 35, player::closeInventory);
    return gui;
  }

  private void setupSlots(final Player player, final GUI gui, final String world) {
    List<Clickable> clickables = new ArrayList<>();
    Mobs mobs = mobsService.getWorlds().get(world);
    for (String mob : mobs.getAvailableMobs()) {
      Material material;
      try {
        material = Material.valueOf(mob.toUpperCase() + "_SPAWN_EGG");
      } catch (IllegalArgumentException e) {
        material = Material.DIAMOND;
      }
      String[] lore = new String[2];
      boolean isDisabled = mobs.getDisabledMobs().contains(mob);
      if (isDisabled) {
        lore[0] = ChatColor.RED + "Disabled";
        lore[1] = "Click to enabled";
      } else {
        lore[0] = ChatColor.GREEN + "Enabled";
        lore[1] = "Click to disable";
      }

      clickables.add(new Clickable(createItem(material, mob, lore), () -> {
        if (isDisabled) {
          mobs.removeDisabledMob(mob);
          new SaveMobConfig(plugin, mobsService);
          sendMessage(player, mob + " was successfully removed from the list of disabled Mobs!");
        } else {
          mobs.addDisabledMob(mob);
          new SaveMobConfig(plugin, mobsService);
          sendMessage(player, mob + " was successfully added to the list of disabled Mobs!");
        }
        setupSlots(player, gui, world);
        gui.refillSlots();
      }));
    }
    gui.addClickables(clickables);
  }

  private ItemStack createItem(Material material, String displayName, String... lore) {
    return createItem(new ItemStack(material), displayName, lore);
  }

  private ItemStack createItem(ItemStack itemStack, String displayName, String... lore) {
    ItemMeta itemMeta = itemStack.getItemMeta();
    if (itemMeta != null) {
      itemMeta.setDisplayName(displayName);
      itemMeta.setLore(Arrays.asList(lore));
      itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
      itemStack.setItemMeta(itemMeta);
    }
    return itemStack;
  }
}
