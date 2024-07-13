package com.dione.shopSmith.listeners;

import com.dione.shopSmith.gui.GUIShopSmith;
import com.dione.shopSmith.gui.GUIShopSmithEnchant;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ListenerShopSmith implements Listener {
    @EventHandler
    public void OnInventoryClick(InventoryClickEvent e){
       if(e.getInventory().equals(GUIShopSmith.inv)){
           Player player = (Player)e.getWhoClicked();
           e.setCancelled(true);
           switch (e.getRawSlot()){
               case 0:
                   player.closeInventory();
                   break;
               case 19:
                   player.openInventory(new GUIShopSmithEnchant(Enchantment.EFFICIENCY).inv);
                   break;
               case 21:
                   player.openInventory(new GUIShopSmithEnchant(Enchantment.FORTUNE).inv);
                   break;
               case 23:
                   player.openInventory(new GUIShopSmithEnchant(Enchantment.UNBREAKING).inv);
                   break;
               case 25:
                   player.openInventory(new GUIShopSmithEnchant(Enchantment.SILK_TOUCH).inv);
                   break;
               case 31:
                   //isso aqui na verdade é pro reparo
                   player.openInventory(new GUIShopSmithEnchant(Enchantment.INFINITY).inv);
                   break;
           }
        }
    }
}
