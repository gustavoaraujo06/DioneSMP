package com.dione.shopSpawner.listeners;

import com.dione.main.Main;
import com.dione.shopSpawner.SpawnerInfo;
import com.dione.shopSpawner.gui.GUIShopSpawner;
import com.dione.shopSpawner.gui.GUIShopSpawnerBuy;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ListenerShopSpawner implements Listener {
    @EventHandler
    public void OnInventoryClick(InventoryClickEvent e){
        if(e.getInventory().equals(GUIShopSpawner.inv)){
            e.setCancelled(true);
            if(e.getRawSlot() == 22){
                Player player = (Player)e.getWhoClicked();
                player.openInventory(GUIShopSpawnerBuy.inv);
            }
        }
        if(e.getInventory().equals(GUIShopSpawnerBuy.inv)){
            e.setCancelled(true);
            Player player = (Player)e.getWhoClicked();
            if(e.getRawSlot() == 0){
                player.closeInventory();
                return;
            }
            if(GUIShopSpawnerBuy.buyItems.containsKey(e.getRawSlot())){
                SpawnerInfo info = GUIShopSpawnerBuy.buyItems.get(e.getRawSlot());
                if(Main.econ.has(player, info.price)){
                    Main.econ.withdrawPlayer(player, info.price);
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.f, 1.f);
                    ItemStack stack = GUIShopSpawnerBuy.getSpawner(info.type);
                    player.getInventory().addItem(stack);
                }else{
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.f, 1.f);
                }
            }
        }
    }

}
