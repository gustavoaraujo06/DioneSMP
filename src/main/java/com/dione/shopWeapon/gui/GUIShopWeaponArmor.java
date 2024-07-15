package com.dione.shopWeapon.gui;

import com.dione.util.InventoryUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class GUIShopWeaponArmor {
    public static Inventory inv;
    static{
        inv = InventoryUtil.createMenu(null,
                Component.text("Armadura", NamedTextColor.BLUE, TextDecoration.BOLD), 45,
                Component.text("Fechar", NamedTextColor.RED));
        ItemStack blueFrame = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
        InventoryUtil.setItemName(blueFrame, Component.text(""));
        for(int i : new int[]{
                1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44
        }){
            inv.setItem(i, blueFrame);
        }

        ItemStack protection = new ItemStack(Material.DIAMOND_CHESTPLATE);
        InventoryUtil.setItemName(protection, Component.text("Proteção", NamedTextColor.BLUE, TextDecoration.BOLD));
        InventoryUtil.setItemLore(protection, List.of(
                Component.text("Aumenta o nivel de Proteção da sua armadura", NamedTextColor.DARK_PURPLE),
                Component.text("Por um preço...", NamedTextColor.GOLD)

        ));
        InventoryUtil.glowItem(protection);

        ItemStack mending = new ItemStack(Material.ENCHANTED_BOOK);
        InventoryUtil.setItemName(mending, Component.text("Remendo", NamedTextColor.BLUE, TextDecoration.BOLD));
        InventoryUtil.setItemLore(mending, List.of(
                Component.text("Aumenta o nivel de Remendo da sua armadura", NamedTextColor.DARK_PURPLE),
                Component.text("Por um preço...", NamedTextColor.GOLD)

        ));
        InventoryUtil.glowItem(mending);

        ItemStack unbreaking = new ItemStack(Material.BEDROCK);
        InventoryUtil.setItemName(unbreaking, Component.text("Inquebravel", NamedTextColor.BLUE, TextDecoration.BOLD));
        InventoryUtil.setItemLore(unbreaking, List.of(
                Component.text("Aumenta o nivel de Inquebravel da sua armadura", NamedTextColor.DARK_PURPLE),
                Component.text("Por um preço...", NamedTextColor.GOLD)

        ));
        InventoryUtil.glowItem(unbreaking);

        ItemStack repair = new ItemStack(Material.ANVIL);
        InventoryUtil.setItemName(repair, Component.text("Reparo", NamedTextColor.BLUE, TextDecoration.BOLD));
        InventoryUtil.setItemLore(repair, List.of(
                Component.text("Repara sua armadura", NamedTextColor.DARK_PURPLE),
                Component.text("Por um preço...", NamedTextColor.GOLD)

        ));
        InventoryUtil.glowItem(repair);


        inv.setItem(20, protection);
        inv.setItem(22, mending);
        inv.setItem(24, unbreaking);
        inv.setItem(31, repair);

    }
}
