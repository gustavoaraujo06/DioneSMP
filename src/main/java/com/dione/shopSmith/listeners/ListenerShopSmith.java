package com.dione.shopSmith.listeners;

import com.dione.enchant.ItemCategory;
import com.dione.shopSmith.gui.GUIShopSmith;
import com.dione.enchant.gui.GUIEnchant;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
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
                   player.openInventory(new GUIEnchant(Enchantment.EFFICIENCY, Component.text("Eficiencia", NamedTextColor.AQUA, TextDecoration.BOLD), ItemCategory.TOOLS, GUIShopSmith.inv).inv);
                   break;
               case 21:
                   player.openInventory(new GUIEnchant(Enchantment.FORTUNE, Component.text("Fortuna", NamedTextColor.AQUA, TextDecoration.BOLD), ItemCategory.TOOLS, GUIShopSmith.inv).inv);
                   break;
               case 23:
                   player.openInventory(new GUIEnchant(Enchantment.UNBREAKING, Component.text("Inquebravel", NamedTextColor.AQUA, TextDecoration.BOLD), ItemCategory.TOOLS, GUIShopSmith.inv).inv);
                   break;
               case 25:
                   player.openInventory(new GUIEnchant(Enchantment.SILK_TOUCH, Component.text("Toque Suave", NamedTextColor.AQUA, TextDecoration.BOLD), ItemCategory.TOOLS, GUIShopSmith.inv).inv);
                   break;
               case 31:
                   //isso aqui na verdade é pro reparo
                   player.openInventory(new GUIEnchant(null, Component.text("Reparar", NamedTextColor.AQUA, TextDecoration.BOLD), ItemCategory.TOOLS, GUIShopSmith.inv).inv);
                   break;
           }
        }
    }
}
