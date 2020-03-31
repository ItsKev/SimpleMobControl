package io.github.itskev.simplemobcontrol.gui;

import io.github.itskev.simplemobcontrol.config.Mobs;
import io.github.itskev.simplemobcontrol.config.MobsService;
import io.github.itskev.simplemobcontrol.config.SaveMobConfig;
import io.github.itskev.simplemobcontrol.util.XMaterial;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
    Inventory inventory = Bukkit.createInventory(null, 45, ChatColor.DARK_GREEN + "SMC - " + world);
    GUIImpl gui = new GUIImpl(inventory, plugin);
    gui.fillBarWith(createItem(XMaterial.BLACK_STAINED_GLASS_PANE.parseItem(), ChatColor.GOLD + ""));

    setupAvailableMobsSlots(player, gui, world);

    gui.addClickable(createItem(XMaterial.RED_WOOL.parseItem(), ChatColor.RED + "Disable all mobs"), 30, () -> {
      mobsService.getWorlds().get(world).disableAllMobs();
      new SaveMobConfig(plugin, mobsService);
      setupAvailableMobsSlots(player, gui, world);
      gui.refillSlots();
    });
    gui.addClickable(createItem(XMaterial.GREEN_WOOL.parseItem(), ChatColor.GREEN + "Enable all mobs"), 32, () -> {
      mobsService.getWorlds().get(world).enableAllMobs();
      new SaveMobConfig(plugin, mobsService);
      setupAvailableMobsSlots(player, gui, world);
      gui.refillSlots();
    });

    gui.addClickable(createItem(XMaterial.GRASS_BLOCK.parseItem(), ChatColor.GREEN + "Change world"), 36, () -> {
      List<String> worlds = mobsService.getWorlds().keySet().stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
      int indexOf = worlds.indexOf(world) + 1;
      if (indexOf >= worlds.size()) {
        indexOf = 0;
      }
      createAvailableMobsGUI(player, worlds.get(indexOf)).openInventory(player);
    });
    gui.addClickable(createItem(XMaterial.ARROW.parseItem(), ChatColor.BLUE + "Previous"), 38, gui::previousPage);
    gui.addClickable(createItem(XMaterial.NETHER_STAR.parseItem(), ChatColor.RED + "Disabled Mobs"), 40, () ->
        createDisabledMobsGUI(player, world).openInventory(player));
    gui.addClickable(createItem(XMaterial.ARROW.parseItem(), ChatColor.BLUE + "Next"), 42, gui::nextPage);
    gui.addClickable(createItem(XMaterial.BARRIER.parseItem(), ChatColor.RED + "Close"), 44, player::closeInventory);
    return gui;
  }

  public GUI createDisabledMobsGUI(final Player player, final String world) {
    Inventory inventory = Bukkit.createInventory(null, 45, ChatColor.DARK_GREEN + "Disabled Mobs - " + world);
    GUIImpl gui = new GUIImpl(inventory, plugin);
    gui.fillBarWith(createItem(XMaterial.BLACK_STAINED_GLASS_PANE.parseItem(), ChatColor.GOLD + ""));

    setupDisabledMobsSlots(player, gui, world);

    gui.addClickable(createItem(XMaterial.RED_WOOL.parseItem(), ChatColor.RED + "Disable all mobs"), 30, () -> {
      mobsService.getWorlds().get(world).disableAllMobs();
      new SaveMobConfig(plugin, mobsService);
      setupDisabledMobsSlots(player, gui, world);
      gui.refillSlots();
    });
    gui.addClickable(createItem(XMaterial.GREEN_WOOL.parseItem(), ChatColor.GREEN + "Enable all mobs"), 32, () -> {
      mobsService.getWorlds().get(world).enableAllMobs();
      new SaveMobConfig(plugin, mobsService);
      setupDisabledMobsSlots(player, gui, world);
      gui.refillSlots();
    });

    gui.addClickable(createItem(XMaterial.GRASS_BLOCK.parseItem(), ChatColor.GREEN + "Change world"), 36, () -> {
      List<String> worlds = mobsService.getWorlds().keySet().stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
      int indexOf = worlds.indexOf(world) + 1;
      if (indexOf >= worlds.size()) {
        indexOf = 0;
      }
      createDisabledMobsGUI(player, worlds.get(indexOf)).openInventory(player);
    });
    gui.addClickable(createItem(XMaterial.ARROW.parseItem(), ChatColor.BLUE + "Previous"), 38, gui::previousPage);
    gui.addClickable(createItem(XMaterial.NETHER_STAR.parseItem(), ChatColor.GREEN + "Available Mobs"), 40, () ->
        createAvailableMobsGUI(player, world).openInventory(player));
    gui.addClickable(createItem(XMaterial.ARROW.parseItem(), ChatColor.BLUE + "Next"), 42, gui::nextPage);
    gui.addClickable(createItem(XMaterial.BARRIER.parseItem(), ChatColor.RED + "Close"), 44, player::closeInventory);
    return gui;
  }

  private void setupAvailableMobsSlots(final Player player, final GUI gui, final String world) {
    List<Clickable> clickables = new ArrayList<>();
    Mobs mobs = mobsService.getWorlds().get(world);
    for (String mob : mobs.getAvailableMobs()) {
      ItemStack itemStack;
      try {
        itemStack = XMaterial.valueOf(mob.toUpperCase() + "_SPAWN_EGG").parseItem();
      } catch (IllegalArgumentException e) {
        itemStack = XMaterial.DIAMOND.parseItem();
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

      clickables.add(new Clickable(createItem(itemStack, mob, lore), () -> {
        if (isDisabled) {
          mobs.removeDisabledMob(mob);
          new SaveMobConfig(plugin, mobsService);
          sendMessage(player, mob + " was successfully removed from the list of disabled Mobs!");
        } else {
          mobs.addDisabledMob(mob);
          new SaveMobConfig(plugin, mobsService);
          sendMessage(player, mob + " was successfully added to the list of disabled Mobs!");
        }
        setupAvailableMobsSlots(player, gui, world);
        gui.refillSlots();
      }));
    }
    gui.addClickables(clickables);
  }

  private void setupDisabledMobsSlots(final Player player, final GUI gui, final String world) {
    List<Clickable> clickables = new ArrayList<>();
    Mobs mobs = mobsService.getWorlds().get(world);
    for (String mob : mobs.getDisabledMobs()) {
      ItemStack itemStack;
      try {
        itemStack = XMaterial.valueOf(mob.toUpperCase() + "_SPAWN_EGG").parseItem();
      } catch (IllegalArgumentException e) {
        itemStack = XMaterial.DIAMOND.parseItem();
      }
      String[] lore = new String[2];
      lore[0] = ChatColor.RED + "Disabled";
      lore[1] = "Click to enabled";

      clickables.add(new Clickable(createItem(itemStack, mob, lore), () -> {
        mobs.removeDisabledMob(mob);
        new SaveMobConfig(plugin, mobsService);
        sendMessage(player, mob + " was successfully removed from the list of disabled Mobs!");

        setupDisabledMobsSlots(player, gui, world);
        gui.refillSlots();
      }));
    }
    gui.addClickables(clickables);
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
