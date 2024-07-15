package com.dione.shop.listeners;

import com.dione.shop.gui.GUIShop;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ListenerShop implements Listener {
    @EventHandler
    public void OnInventoryClick(InventoryClickEvent event){
        if(event.getInventory().getHolder() instanceof GUIShop menu){
            if(event.getInventory().equals(menu.inv)){
                event.setCancelled(true);
                Player player = (Player) event.getWhoClicked();
                switch (event.getRawSlot()){
                    case 0:
                        player.closeInventory();
                        break;
                    case 21:
                        player.openInventory(menu.buyInv);
                        break;
                    case 23:
                        player.openInventory(menu.sellInv);
                        break;
                }
            }

        }
    }
}
