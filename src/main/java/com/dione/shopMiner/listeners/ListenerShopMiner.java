package com.dione.shopMiner.listeners;

import com.dione.shopMiner.gui.GUIShopMiner;
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

public class ListenerShopMiner implements Listener {
    @EventHandler
    public void OnInventoryClick(InventoryClickEvent e){
        if(e.getInventory().equals(GUIShopMiner.inv)){
            e.setCancelled(true);
            Player player = (Player)e.getWhoClicked();
            switch(e.getRawSlot()){
                case 0:
                    player.closeInventory();
                    break;
                case 21:
                    player.openInventory(GUIShopMiner.buyInv);
                    break;
                case 23:
                    player.openInventory(GUIShopMiner.sellInv);
                    break;
            }
        }
        if(e.getInventory().equals(GUIShopMiner.buyInv)){
            e.setCancelled(true);
            Player player = (Player)e.getWhoClicked();
            if(e.getRawSlot() == 0){
                player.openInventory(GUIShopMiner.inv);
                return;
            }
            ShopItemInfo item = GUIShopMiner.buyItems.get(e.getRawSlot());
            if(Main.econ.has(player, item.price)){
                Main.econ.withdrawPlayer(player, item.price);
                player.getInventory().addItem(new ItemStack(item.material));
                player.sendMessage(Component.text("Você Comprou 1 ").color(NamedTextColor.GOLD)
                        .append(new ItemStack(item.material).displayName().color(NamedTextColor.AQUA)));
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.f, 1.f);
            }
            else{
                player.sendMessage(Component.text("Você Não Tem Dinheiro Suficiente", NamedTextColor.RED));
            }
        }
        if(e.getInventory().equals(GUIShopMiner.sellInv)){
            e.setCancelled(true);
            Player player = (Player)e.getWhoClicked();
            if(e.getRawSlot() == 0){
                player.openInventory(GUIShopMiner.inv);
                return;
            }
            ShopItemInfo item = GUIShopMiner.sellItems.get(e.getRawSlot());
            if(player.getInventory().contains(item.material)){
                player.getInventory().removeItem(new ItemStack(item.material, 1));
                Main.econ.depositPlayer(player, item.price);
                player.sendMessage(Component.text("Você Vendeu 1 ").color(NamedTextColor.GOLD)
                        .append(new ItemStack(item.material).displayName().color(NamedTextColor.AQUA)));
            }
        }
    }
}
