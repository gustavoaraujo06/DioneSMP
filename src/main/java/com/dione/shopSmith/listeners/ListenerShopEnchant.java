package com.dione.shopSmith.listeners;

import com.dione.main.Main;
import com.dione.shopSmith.ToolTable;
import com.dione.shopSmith.gui.GUIShopSmith;
import com.dione.shopSmith.gui.GUIShopSmithEnchant;
import com.dione.util.InventoryUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ListenerShopEnchant implements Listener {
    @EventHandler
    public void PreventItemDisappear(InventoryCloseEvent e){
        if(e.getInventory().getHolder() instanceof  GUIShopSmithEnchant menu){
            if(e.getInventory().getItem(20) != null){
                Player player = (Player) e.getPlayer();
                if(e.getInventory().getItem(20) != null){
                    ItemStack item = e.getInventory().getItem(20);
                    e.getInventory().setItem(20, null);
                    player.getInventory().addItem(item);
                }
            }
        }
    }
    @EventHandler
    public void OnInventoryClick(InventoryClickEvent e){
        if(e.getInventory().getHolder() instanceof GUIShopSmithEnchant menu){
            if(e.getRawSlot() == 0){
                Player player = (Player) e.getWhoClicked();
                if(e.getInventory().getItem(20) != null){
                    ItemStack item = e.getInventory().getItem(20);
                    e.getInventory().setItem(20, null);
                    player.getInventory().addItem(item);
                }
                player.openInventory(GUIShopSmith.inv);
            }
            if(e.getRawSlot() == 20){
                if(e.getAction() == InventoryAction.PLACE_ALL) {
                    if(!ToolTable.isToolValid(e.getCursor().getType())){
                        e.setCancelled(true);
                        return;
                    }
                    e.getInventory().setItem(24, getEnchantedItem(e.getCursor(), menu.type, true));

                }
                if(e.getAction() == InventoryAction.PICKUP_ALL){
                    e.getInventory().setItem(24, null);
                }
            }if(e.getRawSlot() != 20 && e.getRawSlot() <= 44){
                e.setCancelled(true);
            }if(e.getRawSlot() == 24){
                Player player = (Player)e.getWhoClicked();
                if(e.getInventory().getItem(20) == null){
                    return;
                }
                ItemStack item = e.getInventory().getItem(20);
                if(Main.econ.has(player, ToolTable.getPrice(item, menu.type))){
                    ItemStack enchantedItem = getEnchantedItem(item, menu.type, false);

                    Main.econ.withdrawPlayer(player, ToolTable.getPrice(item, menu.type));

                    e.getInventory().setItem(20, null);
                    e.getInventory().setItem(24, null);
                    player.getInventory().addItem(enchantedItem);

                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.f, 1.f);
                }else{
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.f, 1.f);
                }

            }
        }
    }
    @EventHandler
    public void OnInventoryDrag(InventoryDragEvent e){
        if(e.getInventory().getHolder() instanceof GUIShopSmithEnchant menu){
            for(int i : e.getRawSlots()){
                if(i == 20){
                    e.getInventory().setItem(24, getEnchantedItem(e.getOldCursor(), menu.type, true));
                }
                if(i != 20 && i <= 44){
                    e.setCancelled(true);
                }
            }
        }
    }
    public ItemStack getEnchantedItem(ItemStack item, Enchantment type, boolean toDisplay){
        ItemStack enchantedItem = item.clone();
        if(type == Enchantment.INFINITY){
            Damageable meta = (Damageable)enchantedItem.getItemMeta();
            meta.setDamage(0);
            enchantedItem.setItemMeta(meta);
            if(toDisplay){
                InventoryUtil.setItemLore(enchantedItem,
                        List.of(
                                Component.text(InventoryUtil.toCurrency(ToolTable.getPrice(item, type)) + " Moedas", NamedTextColor.GOLD)
                        )) ;
            }
            return enchantedItem;
        }
        int enchantmentLevel = enchantedItem.getEnchantmentLevel(type);
        enchantedItem.addUnsafeEnchantment(type, enchantmentLevel + 1);
        if(toDisplay){
            InventoryUtil.setItemLore(enchantedItem,
                    List.of(
                            Component.text(InventoryUtil.toCurrency(ToolTable.getPrice(item, type)) + " Moedas", NamedTextColor.GOLD)
                    )) ;
        }
        return enchantedItem;
    }
}
