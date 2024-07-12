package com.dione.util;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class InventoryUtil {
    public static void setItemName(ItemStack stack, Component component){
        ItemMeta meta = stack.getItemMeta();
        meta.displayName(component);
        stack.setItemMeta(meta);
    }
    public static void setItemLore(ItemStack stack, List<Component> lore){
        ItemMeta meta = stack.getItemMeta();
        meta.lore(lore);
        stack.setItemMeta(meta);
    }
    public static void glowItem(ItemStack stack){
        stack.addUnsafeEnchantment(Enchantment.SHARPNESS, 1);
        ItemMeta meta = stack.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        stack.setItemMeta(meta);
    }

    public static Inventory createMenu(@Nullable InventoryHolder owner, Component title, int size, Component closeTitle){
        Inventory inv = Bukkit.createInventory(owner, size, title);
        ItemStack close = new ItemStack(Material.BARRIER);
        setItemName(close, closeTitle);
        inv.setItem(0, close);
        return inv;
    }
    public static int toInventorySlot(int x, int y){
        return x + (9 * y);
    }
    public static ArrayList<Integer> toInventorySlots(int[] slots){
        ArrayList<Integer> newSlots = new ArrayList<>();

        for(int i = 0; i < slots.length; i+=2){
            newSlots.add(toInventorySlot(slots[i], slots[i + 1]));
        }

        return newSlots;
    }
    public static String toCurrency(double Value){
        NumberFormat nf = NumberFormat.getInstance(Locale.of("pt, BR"));
        nf.setMaximumFractionDigits(0);
        nf.setGroupingUsed(true);

        return nf.format(Value);
    }
}
