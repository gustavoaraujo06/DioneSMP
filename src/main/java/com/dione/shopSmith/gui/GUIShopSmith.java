package com.dione.shopSmith.gui;

import com.dione.util.InventoryUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class GUIShopSmith {
    public static Inventory inv;

    static{
        inv = SetupSmithMenu();
    }
    public static Inventory SetupSmithMenu(){
        Inventory newInv = Bukkit.createInventory(null, 45,
                Component.text("Ferreiro", NamedTextColor.DARK_PURPLE, TextDecoration.BOLD));
        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closeMeta = close.getItemMeta();
        closeMeta.displayName(Component.text("Fechar", NamedTextColor.RED));
        close.setItemMeta(closeMeta);
        newInv.setItem(0, close);

        ItemStack frame = new ItemStack(Material.PURPLE_STAINED_GLASS_PANE);
        InventoryUtil.setItemName(frame, Component.text(""));

        for(int i : new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44}){
            newInv.setItem(i, frame);
        }

        ItemStack efficiencyItem = new ItemStack(Material.STONE);
        InventoryUtil.setItemName(efficiencyItem, Component.text("Eficiencia", NamedTextColor.AQUA));
        InventoryUtil.setItemLore(efficiencyItem, List.of(
                Component.text("Aumenta o nivel de Eficiencia da sua ferramenta"),
                Component.text("por um preço...", NamedTextColor.GOLD)
        ));
        InventoryUtil.glowItem(efficiencyItem);

        ItemStack fortuneItem = new ItemStack(Material.DIAMOND);
        InventoryUtil.setItemName(fortuneItem, Component.text("Fortuna", NamedTextColor.AQUA));
        InventoryUtil.setItemLore(fortuneItem, List.of(
                Component.text("Aumenta o nivel de Fortuna da sua ferramenta"),
                Component.text("por um preço...", NamedTextColor.GOLD)
        ));
        InventoryUtil.glowItem(fortuneItem);

        ItemStack unbreakingItem = new ItemStack(Material.BEDROCK);
        InventoryUtil.setItemName(unbreakingItem, Component.text("Inquebravel", NamedTextColor.AQUA));
        InventoryUtil.setItemLore(unbreakingItem, List.of(
                Component.text("Aumenta o nivel de Inquebravel da sua ferramenta"),
                Component.text("por um preço...", NamedTextColor.GOLD)
        ));
        InventoryUtil.glowItem(unbreakingItem);

        ItemStack silkTouchItem = new ItemStack(Material.ICE);
        InventoryUtil.setItemName(silkTouchItem, Component.text("Toque Suave", NamedTextColor.AQUA));
        InventoryUtil.setItemLore(silkTouchItem, List.of(
                Component.text("Aumenta o nivel de Toque Suave da sua ferramenta"),
                Component.text("por um preço...", NamedTextColor.GOLD)
        ));
        InventoryUtil.glowItem(silkTouchItem);

        newInv.setItem(19, efficiencyItem);
        newInv.setItem(21, fortuneItem);
        newInv.setItem(23, unbreakingItem);
        newInv.setItem(25, silkTouchItem);

        return newInv;

    }

}
