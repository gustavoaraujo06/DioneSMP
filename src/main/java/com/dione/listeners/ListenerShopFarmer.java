package com.dione.listeners;

import com.dione.gui.GUIShopFarmer;
import com.dione.gui.GUIShopMiner;
import com.dione.main.Main;
import com.dione.util.ShopItemInfo;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ListenerShopFarmer implements Listener {
    @EventHandler
    public void OnInventoryClick(InventoryClickEvent e){
        Player player = (Player)e.getWhoClicked();
        if(e.getInventory().equals(GUIShopFarmer.inv)){
            e.setCancelled(true);
            switch (e.getRawSlot()){
                case 0:
                    player.closeInventory();
                    break;
                case 21:
                    player.openInventory(GUIShopFarmer.buyInv);
                    break;
                case 23:
                    player.openInventory(GUIShopFarmer.sellInv);
                    break;
            }
        }
        if(e.getInventory().equals(GUIShopFarmer.buyInv)){
            e.setCancelled(true);
            if(e.getRawSlot() == 0){
                player.openInventory(GUIShopFarmer.inv);
                return;
            }
            if(GUIShopFarmer.buyItems.containsKey(e.getRawSlot())){
                ShopItemInfo itemInfo = GUIShopFarmer.buyItems.get(e.getRawSlot());
                if(Main.econ.has(player, itemInfo.price)){
                    ItemStack item = new ItemStack(itemInfo.material);
                    player.getInventory().addItem(item);
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.f, 1.f);
                    player.sendMessage(Component.text("Você Comprou 1 ").color(NamedTextColor.GOLD)
                            .append(item.displayName().color(NamedTextColor.AQUA)));
                    Main.econ.withdrawPlayer(player, itemInfo.price);
                }else{
                    player.sendMessage(Component.text("Você Não Tem Dinheiro Suficiente", NamedTextColor.RED));
                }
            }
        }
        if(e.getInventory().equals(GUIShopFarmer.sellInv)){
            e.setCancelled(true);
            if(e.getRawSlot() == 0){
                player.openInventory(GUIShopFarmer.inv);
                return;
            }
            if(GUIShopFarmer.sellItems.containsKey(e.getRawSlot())){
                ShopItemInfo itemInfo = GUIShopFarmer.sellItems.get(e.getRawSlot());
                ItemStack item = new ItemStack(itemInfo.material);
                if(player.getInventory().contains(item)){
                    Main.econ.depositPlayer(player, itemInfo.price);
                    player.getInventory().removeItem(item);
                    player.sendMessage(Component.text("Você Vendeu 1 ").color(NamedTextColor.GOLD)
                            .append(item.displayName().color(NamedTextColor.AQUA)));
                }
            }
        }
    }
}
