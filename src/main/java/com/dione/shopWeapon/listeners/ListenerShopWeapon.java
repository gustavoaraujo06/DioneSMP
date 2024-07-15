package com.dione.shopWeapon.listeners;

import com.dione.shopWeapon.gui.GUIShopWeapon;
import com.dione.shopWeapon.gui.GUIShopWeaponArmor;
import com.dione.shopWeapon.gui.GUIShopWeaponSword;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ListenerShopWeapon implements Listener {
    @EventHandler
    public void OnInventoryClick(InventoryClickEvent event){
        if(event.getInventory().equals(GUIShopWeapon.inv)){
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            switch (event.getRawSlot()){
                case 0:
                    player.closeInventory();
                    break;
                case 21:
                    player.openInventory(GUIShopWeaponSword.inv);
                    break;
                case 23:
                    player.openInventory(GUIShopWeaponArmor.inv);
                    break;
            }
        }
    }
}
