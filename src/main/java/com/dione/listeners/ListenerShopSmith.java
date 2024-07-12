package com.dione.listeners;

import com.dione.gui.GUIShopMiner;
import com.dione.gui.GUIShopSmith;
import com.dione.main.Main;
import com.dione.util.InventoryUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListenerShopSmith implements Listener {
    public static Map<Material, Double> tools = new HashMap<>();
    static{
        tools.put(Material.DIAMOND_PICKAXE, 1.0);
        tools.put(Material.DIAMOND_AXE, 1.0);
        tools.put(Material.DIAMOND_SHOVEL, 1.0);
        tools.put(Material.NETHERITE_PICKAXE, 2.5);
        tools.put(Material.NETHERITE_AXE, 2.5);
        tools.put(Material.NETHERITE_SHOVEL, 2.5);
        tools.put(Material.IRON_PICKAXE, 0.75);
        tools.put(Material.IRON_AXE, 0.75);
        tools.put(Material.IRON_SHOVEL, 0.75);
        tools.put(Material.GOLDEN_PICKAXE, 0.5);
        tools.put(Material.GOLDEN_AXE, 0.5);
        tools.put(Material.GOLDEN_SHOVEL, 0.5);
        tools.put(Material.STONE_PICKAXE, 0.5);
        tools.put(Material.STONE_AXE, 0.5);
        tools.put(Material.STONE_SHOVEL, 0.5);
        tools.put(Material.WOODEN_PICKAXE, 0.1);
        tools.put(Material.WOODEN_AXE, 0.1);
        tools.put(Material.WOODEN_SHOVEL, 0.1);

    }
    @EventHandler
    public void OnInventoryClick(InventoryClickEvent e){
        Main.getInstance().getLogger().info(e.getAction().toString());

        if(e.getInventory().equals(GUIShopSmith.inv)){
            e.setCancelled(true);
            Player player = (Player)e.getWhoClicked();
            switch (e.getRawSlot()){
                case 0:
                    player.closeInventory();
                    break;
                case 20:
                    player.openInventory(GUIShopSmith.efficiencyInv);
                    break;
                case 22:
                    player.openInventory(GUIShopSmith.fortuneInv);
                    break;
                case 24:
                    player.openInventory(GUIShopSmith.unbreakingInv);
                    break;
            }
        }
        if(e.getInventory().equals(GUIShopSmith.efficiencyInv)){
            if(e.getRawSlot() != 20 && e.getRawSlot() <= 44){
                e.setCancelled(true);
                return;
            }
            if(e.getRawSlot() == 20){
                if(!tools.containsKey(e.getCursor().getType()) && e.getCurrentItem() == null){
                    e.setCancelled(true);
                    return;
                }
                if(e.getAction().equals(InventoryAction.PLACE_ALL) || e.getAction().equals(InventoryAction.MOVE_TO_OTHER_INVENTORY)){
                    Inventory inv = e.getInventory();
                    ItemStack stack = new ItemStack(e.getCursor());
                    if(stack.getEnchantmentLevel(Enchantment.EFFICIENCY) > 0){
                        double price = Math.pow(10, 2 + stack.getEnchantmentLevel(Enchantment.EFFICIENCY) * tools.get(stack.getType()));
                        InventoryUtil.setItemLore(stack, List.of(
                                Component.text(price + " Moedas", NamedTextColor.GOLD, TextDecoration.BOLD)
                        ));
                        stack.addUnsafeEnchantment(Enchantment.EFFICIENCY,stack.getEnchantmentLevel(Enchantment.EFFICIENCY) + 1);
                    }else{
                        double price = Math.pow(10, 2);
                        InventoryUtil.setItemLore(stack, List.of(
                                Component.text(price + " Moedas", NamedTextColor.GOLD, TextDecoration.BOLD)
                        ));
                        stack.addUnsafeEnchantment(Enchantment.EFFICIENCY,1);
                    }
                    inv.setItem(24, stack);
                }
                if(e.getAction().equals(InventoryAction.PICKUP_ALL)){
                    e.getInventory().setItem(24, null);
                }
            }
            if(e.getRawSlot() == 24){

            }

        }
    }
    @EventHandler
    public void OnInventoryDrag(InventoryDragEvent e){
        Main.getInstance().getLogger().info(e.toString());
        if(e.getInventory().equals(GUIShopSmith.efficiencyInv)){
            if(e.getRawSlots().size() > 1){
                e.setCancelled(true);
                return;
            }
            int slotNum = e.getRawSlots().toArray(Integer[]::new)[0];
            if(slotNum != 20 && slotNum <= 44){
                e.setCancelled(true);
                return;
            }
            if(slotNum == 20){
                Inventory inv = e.getInventory();
                ItemStack stack = new ItemStack(e.getOldCursor());
                if(stack.getEnchantmentLevel(Enchantment.EFFICIENCY) > 0){
                    double price = Math.pow(10, 2 + stack.getEnchantmentLevel(Enchantment.EFFICIENCY) * tools.get(stack.getType()));
                    InventoryUtil.setItemLore(stack, List.of(
                            Component.text(price + " Moedas", NamedTextColor.GOLD, TextDecoration.BOLD)
                    ));
                    stack.addUnsafeEnchantment(Enchantment.EFFICIENCY,stack.getEnchantmentLevel(Enchantment.EFFICIENCY) + 1);
                }else{
                    double price = Math.pow(10, 2);
                    InventoryUtil.setItemLore(stack, List.of(
                            Component.text(price + " Moedas", NamedTextColor.GOLD, TextDecoration.BOLD)
                    ));
                    stack.addUnsafeEnchantment(Enchantment.EFFICIENCY,1);
                }
                inv.setItem(24, stack);
            }
        }
    }

}
