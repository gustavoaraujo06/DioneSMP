package com.dione.shopWeapon.listeners;

import com.dione.enchant.ItemCategory;
import com.dione.enchant.gui.GUIEnchant;
import com.dione.shopSmith.gui.GUIShopSmith;
import com.dione.shopWeapon.gui.GUIShopWeapon;
import com.dione.shopWeapon.gui.GUIShopWeaponArmor;
import com.dione.shopWeapon.gui.GUIShopWeaponSword;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ListenerShopArmor implements Listener {
    @EventHandler
    public void OnInventoryClick(InventoryClickEvent event){
        if(event.getInventory().equals(GUIShopWeaponArmor.inv)){
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            switch (event.getRawSlot()){
                case 0:
                    player.openInventory(GUIShopWeapon.inv);
                    break;
                case 20:
                    player.openInventory(new GUIEnchant(Enchantment.PROTECTION,
                            Component.text("Proteção", NamedTextColor.BLUE, TextDecoration.BOLD),
                            ItemCategory.ARMOR, GUIShopWeapon.inv).inv);
                    break;
                case 22:
                    player.openInventory(new GUIEnchant(Enchantment.MENDING,
                            Component.text("Remendo", NamedTextColor.BLUE, TextDecoration.BOLD),
                            ItemCategory.ARMOR, GUIShopWeapon.inv).inv);
                    break;
                case 24:
                    player.openInventory(new GUIEnchant(Enchantment.UNBREAKING,
                            Component.text("Inquebravel", NamedTextColor.BLUE, TextDecoration.BOLD),
                            ItemCategory.ARMOR, GUIShopWeapon.inv).inv);
                    break;
                case 31:
                    player.openInventory(new GUIEnchant(null,
                            Component.text("Reparo", NamedTextColor.BLUE, TextDecoration.BOLD),
                            ItemCategory.ARMOR, GUIShopWeapon.inv).inv);
                    break;
            }
        }
    }
}
