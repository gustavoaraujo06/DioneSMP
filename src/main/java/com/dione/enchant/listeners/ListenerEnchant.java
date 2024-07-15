package com.dione.enchant.listeners;

import com.dione.enchant.EnchantUtil;
import com.dione.logger.Logger;
import com.dione.main.Main;
import com.dione.enchant.gui.GUIEnchant;
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

import java.util.List;

public class ListenerEnchant implements Listener {
    @EventHandler
    public void PreventItemDisappear(InventoryCloseEvent e){
        if(e.getInventory().getHolder() instanceof  GUIEnchant menu){
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
        if(e.getInventory().getHolder() instanceof GUIEnchant menu){
            if(e.getRawSlot() == 0){
                Player player = (Player) e.getWhoClicked();
                if(e.getInventory().getItem(20) != null){
                    ItemStack item = e.getInventory().getItem(20);
                    e.getInventory().setItem(20, null);
                    player.getInventory().addItem(item);
                }
                player.openInventory(menu.parentInv);
            }
            if(e.getRawSlot() == 20){
                if(e.getAction() == InventoryAction.PLACE_ALL) {
                    if(!EnchantUtil.isItemValid(e.getCursor(), menu.itemCategory)){
                        e.setCancelled(true);
                        return;
                    }
                    e.getInventory().setItem(24, EnchantUtil.getEnchantedItem(e.getCursor(), menu.type, true));

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
                long price = EnchantUtil.getEnchantmentPrice(item, menu.type);
                if(Main.econ.has(player, price)){
                    ItemStack enchantedItem = EnchantUtil.getEnchantedItem(item, menu.type, false);

                    Main.econ.withdrawPlayer(player, EnchantUtil.getEnchantmentPrice(item, menu.type));

                    e.getInventory().setItem(20, null);
                    e.getInventory().setItem(24, null);
                    player.getInventory().addItem(enchantedItem);

                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.f, 1.f);

                    //logger stuff
                    int enchantmentLevel = enchantedItem.getEnchantmentLevel(menu.type);
                    Logger.Log(player.getName() +
                            " Encantou " +
                            Logger.componentToString(enchantedItem.displayName()) +
                            " Com " +
                            Logger.componentToString(menu.type.displayName(enchantmentLevel)) +
                            " Por " +
                            InventoryUtil.toCurrency(price));
                }else{
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.f, 1.f);
                }

            }
        }
    }
    @EventHandler
    public void OnInventoryDrag(InventoryDragEvent e){
        if(e.getInventory().getHolder() instanceof GUIEnchant menu){
            for(int i : e.getRawSlots()){
                if(i == 20){
                    ItemStack oldItem = e.getOldCursor();
                    if(!EnchantUtil.isItemValid(oldItem, menu.itemCategory)){
                        e.setCancelled(true);
                        return;
                    }
                    e.getInventory().setItem(24, EnchantUtil.getEnchantedItem(e.getOldCursor(), menu.type, true));
                }
                if(i != 20 && i <= 44){
                    e.setCancelled(true);
                }
            }
        }
    }
}
