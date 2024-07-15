package com.dione.shopWeapon.listeners;

import com.dione.enchant.ItemCategory;
import com.dione.enchant.gui.GUIEnchant;
import com.dione.shopWeapon.gui.GUIShopWeapon;
import com.dione.shopWeapon.gui.GUIShopWeaponSword;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ListenerShopSword implements Listener {
    @EventHandler
    public void OnInventoryClick(InventoryClickEvent event){
        if(event.getInventory().equals(GUIShopWeaponSword.inv)){
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            switch (event.getRawSlot()){
                case 0:
                    player.openInventory(GUIShopWeapon.inv);
                    break;
                case 19:
                    player.openInventory(new GUIEnchant(Enchantment.SHARPNESS,
                            Component.text("Sharpness", NamedTextColor.RED, TextDecoration.BOLD),
                            ItemCategory.SWORD, GUIShopWeapon.inv).inv);
                    break;
                case 21:
                    player.openInventory(new GUIEnchant(Enchantment.KNOCKBACK,
                            Component.text("Knockback", NamedTextColor.RED, TextDecoration.BOLD),
                            ItemCategory.SWORD, GUIShopWeapon.inv).inv);
                    break;
                case 23:
                    player.openInventory(new GUIEnchant(Enchantment.FIRE_ASPECT,
                            Component.text("Fire Aspect", NamedTextColor.RED, TextDecoration.BOLD),
                            ItemCategory.SWORD, GUIShopWeapon.inv).inv);
                    break;
                case 25:
                    player.openInventory(new GUIEnchant(Enchantment.LOOTING,
                            Component.text("Looting", NamedTextColor.RED, TextDecoration.BOLD),
                            ItemCategory.SWORD, GUIShopWeapon.inv).inv);
                    break;
                case 31:
                    player.openInventory(new GUIEnchant(null,
                            Component.text("Reparo", NamedTextColor.BLUE, TextDecoration.BOLD),
                            ItemCategory.SWORD, GUIShopWeapon.inv).inv);
                    break;
            }
        }
    }
}
