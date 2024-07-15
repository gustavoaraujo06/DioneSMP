package com.dione.shopWeapon.gui;

import com.dione.util.InventoryUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class GUIShopWeaponSword {
    public static Inventory inv;
    static{
        inv = InventoryUtil.createMenu(null,
                Component.text("Espada", NamedTextColor.RED, TextDecoration.BOLD), 45,
                Component.text("Fechar", NamedTextColor.RED));
        ItemStack blueFrame = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
        InventoryUtil.setItemName(blueFrame, Component.text(""));
        for(int i : new int[]{
                1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44
        }){
            inv.setItem(i, blueFrame);
        }

        ItemStack sharpness = new ItemStack(Material.IRON_SWORD);
        InventoryUtil.setItemName(sharpness, Component.text("Sharpness", NamedTextColor.RED, TextDecoration.BOLD));
        InventoryUtil.setItemLore(sharpness, List.of(
                Component.text("Aumenta o nivel de Sharpness da sua espada", NamedTextColor.DARK_PURPLE),
                Component.text("Por um preço...", NamedTextColor.GOLD)

        ));
        InventoryUtil.glowItem(sharpness);

        ItemStack knockback = new ItemStack(Material.SLIME_BLOCK);
        InventoryUtil.setItemName(knockback, Component.text("Knockback", NamedTextColor.RED, TextDecoration.BOLD));
        InventoryUtil.setItemLore(knockback, List.of(
                Component.text("Aumenta o nivel de Knocback da sua espada", NamedTextColor.DARK_PURPLE),
                Component.text("Por um preço...", NamedTextColor.GOLD)

        ));
        InventoryUtil.glowItem(knockback);

        ItemStack fireAspect = new ItemStack(Material.LAVA_BUCKET);
        InventoryUtil.setItemName(fireAspect, Component.text("Fire Aspect", NamedTextColor.RED, TextDecoration.BOLD));
        InventoryUtil.setItemLore(fireAspect, List.of(
                Component.text("Aumenta o nivel de Fire Aspect da sua espada", NamedTextColor.DARK_PURPLE),
                Component.text("Por um preço...", NamedTextColor.GOLD)

        ));
        InventoryUtil.glowItem(fireAspect);

        ItemStack looting = new ItemStack(Material.ROTTEN_FLESH);
        InventoryUtil.setItemName(looting, Component.text("Looting", NamedTextColor.RED, TextDecoration.BOLD));
        InventoryUtil.setItemLore(looting, List.of(
                Component.text("Aumenta o nivel de Looting da sua espada", NamedTextColor.DARK_PURPLE),
                Component.text("Por um preço...", NamedTextColor.GOLD)

        ));
        InventoryUtil.glowItem(looting);

        ItemStack repair = new ItemStack(Material.ANVIL);
        InventoryUtil.setItemName(repair, Component.text("Reparo", NamedTextColor.RED, TextDecoration.BOLD));
        InventoryUtil.setItemLore(repair, List.of(
                Component.text("Repara sua espada", NamedTextColor.DARK_PURPLE),
                Component.text("Por um preço...", NamedTextColor.GOLD)

        ));
        InventoryUtil.glowItem(repair);

        inv.setItem(19, sharpness);
        inv.setItem(21, knockback);
        inv.setItem(23, fireAspect);
        inv.setItem(25, looting);
        inv.setItem(31, repair);

    }
}
