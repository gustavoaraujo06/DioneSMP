package com.dione.gui;

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
    public static Inventory efficiencyInv;
    public static Inventory fortuneInv;
    public static Inventory unbreakingInv;
    static{
        efficiencyInv = SetupEnchantmentMenu("Eficiencia");
        fortuneInv = SetupEnchantmentMenu("Fortuna");
        unbreakingInv = SetupEnchantmentMenu("Inquebravel");
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
                Component.text("Aumenta o nivel de eficiencia da sua ferramenta"),
                Component.text("por um preço...", NamedTextColor.GOLD)
        ));
        InventoryUtil.glowItem(efficiencyItem);

        ItemStack fortuneItem = new ItemStack(Material.DIAMOND);
        InventoryUtil.setItemName(fortuneItem, Component.text("Fortuna", NamedTextColor.AQUA));
        InventoryUtil.setItemLore(fortuneItem, List.of(
                Component.text("Aumenta o nivel de fortuna da sua ferramenta"),
                Component.text("por um preço...", NamedTextColor.GOLD)
        ));
        InventoryUtil.glowItem(fortuneItem);

        ItemStack unbreakingItem = new ItemStack(Material.BEDROCK);
        InventoryUtil.setItemName(unbreakingItem, Component.text("Inquebravel", NamedTextColor.AQUA));
        InventoryUtil.setItemLore(unbreakingItem, List.of(
                Component.text("Aumenta o nivel de inquebravel da sua ferramenta"),
                Component.text("por um preço...", NamedTextColor.GOLD)
        ));
        InventoryUtil.glowItem(unbreakingItem);

        newInv.setItem(20, efficiencyItem);
        newInv.setItem(22, fortuneItem);
        newInv.setItem(24, unbreakingItem);

        return newInv;

    }
    public static Inventory SetupEnchantmentMenu(String title){
        Inventory newInv = Bukkit.createInventory(null, 45,
                Component.text(title, NamedTextColor.AQUA, TextDecoration.BOLD));
        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closeMeta = close.getItemMeta();
        closeMeta.displayName(Component.text("Fechar", NamedTextColor.RED));
        close.setItemMeta(closeMeta);
        newInv.setItem(0, close);

        ItemStack frameWhite = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
        InventoryUtil.setItemName(frameWhite, Component.text(""));
        ItemStack framePurple = new ItemStack(Material.PURPLE_STAINED_GLASS_PANE);
        InventoryUtil.setItemName(framePurple, Component.text(""));
        ItemStack frameGreen = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        InventoryUtil.setItemName(frameGreen, Component.text(""));

        for(int i : new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 13, 17, 18, 22, 26, 27, 31, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44}){
            newInv.setItem(i, framePurple);
        }
        for(int i : new int[]{10, 11, 12, 19, 21, 28, 29, 30}){
            newInv.setItem(i, frameWhite);
        }
        for(int i : new int[]{14, 15, 16, 23, 25, 32, 33, 34}){
            newInv.setItem(i, frameGreen);
        }

        return newInv;

    }
}
