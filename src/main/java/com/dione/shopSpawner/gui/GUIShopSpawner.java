package com.dione.shopSpawner.gui;

import com.dione.util.InventoryUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GUIShopSpawner {
    public static Inventory inv;
    static{
        inv = InventoryUtil.createMenu(null,
                Component.text("Necromancer", NamedTextColor.DARK_RED), 45, Component.text("Fechar", NamedTextColor.RED));
        ItemStack redFrame = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        InventoryUtil.setItemName(redFrame,
                Component.text(""));
        for(int i : new int[]{
                1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44
        }){
            inv.setItem(i, redFrame);
        }

        ItemStack buy = new ItemStack(Material.CREEPER_SPAWN_EGG);
        InventoryUtil.setItemName(buy,
                Component.text("Comprar", NamedTextColor.LIGHT_PURPLE, TextDecoration.BOLD, TextDecoration.ITALIC));
        inv.setItem(22, buy);

    }
}
