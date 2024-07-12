package com.dione.shopSmith.gui;

import com.dione.util.InventoryUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class GUIShopSmithEnchant implements InventoryHolder {
    public Enchantment type;
    public Inventory inv;
    public GUIShopSmithEnchant(Enchantment type){
        this.type = type;

        assert type == Enchantment.EFFICIENCY || type == Enchantment.FORTUNE || type == Enchantment.UNBREAKING || type == Enchantment.SILK_TOUCH;

        String title = "";
        if (type == Enchantment.EFFICIENCY){
            title = "Eficiencia";
        }else if (type == Enchantment.FORTUNE){
            title = "Fortuna";
        }else if (type == Enchantment.UNBREAKING){
            title = "Inquebravel";
        }else{
            title = "Toque Suave";
        }
        inv = InventoryUtil.createMenu(this,
                Component.text(title, NamedTextColor.AQUA),
                45,
                Component.text("Fechar", NamedTextColor.RED)
        );
        ItemStack whiteFrame = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
        InventoryUtil.setItemName(whiteFrame, Component.text(""));
        ItemStack purpleFrame = new ItemStack(Material.PURPLE_STAINED_GLASS_PANE);
        InventoryUtil.setItemName(purpleFrame, Component.text(""));
        ItemStack greenFrame = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        InventoryUtil.setItemName(greenFrame, Component.text(""));

        for(int i : new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 13, 17, 18, 22, 26, 27,31,35,36,37,38,39,40,41,42,43,44}){
            inv.setItem(i, purpleFrame);
        }
        for(int i : InventoryUtil.toInventorySlots(new int[]{
                1, 1,
                2, 1,
                3, 1,
                1, 2,
                3, 2,
                1, 3,
                2, 3,
                3, 3
        })){
            inv.setItem(i, whiteFrame);
        }
        for(int i : InventoryUtil.toInventorySlots(new int[]{
                5, 1,
                6, 1,
                7, 1,
                5, 2,
                7, 2,
                5, 3,
                6, 3,
                7, 3,
        })){
            inv.setItem(i, greenFrame);
        }
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inv;
    }
}
