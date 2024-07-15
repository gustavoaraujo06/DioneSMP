package com.dione.shopWeapon.gui;

import com.dione.util.InventoryUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GUIShopWeapon {
    public static Inventory inv;
    static{
        inv = InventoryUtil.createMenu(null,
                Component.text("Armeiro", NamedTextColor.LIGHT_PURPLE, TextDecoration.BOLD),
                45,
                Component.text("Fechar", NamedTextColor.RED));

        ItemStack blueFrame = new ItemStack (Material.BLUE_STAINED_GLASS_PANE);
        InventoryUtil.setItemName(blueFrame, Component.text(""));

        for(int i : new int[]{
                1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44
        }){
            inv.setItem(i, blueFrame);
        }

        ItemStack weaponItem = new ItemStack(Material.DIAMOND_SWORD);
        InventoryUtil.setItemName(weaponItem,Component.text("Espadas", NamedTextColor.DARK_PURPLE));

        ItemStack armorItem = new ItemStack(Material.DIAMOND_CHESTPLATE);
        InventoryUtil.setItemName(armorItem,Component.text("Armaduras", NamedTextColor.DARK_PURPLE));

        inv.setItem(21, weaponItem);
        inv.setItem(23, armorItem);

    }
}
