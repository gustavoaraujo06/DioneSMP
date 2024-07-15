package com.dione.enchant;

import com.dione.util.InventoryUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EnchantUtil {
    public static boolean isItemValid(ItemStack item, ItemCategory itemCategory){
        if(!ItemTable.ITEM_INFO.containsKey(item.getType())){
            return false;
        }
        return ItemTable.ITEM_INFO.get(item.getType()).category == itemCategory;
    }
    public static boolean isItemValid(Material itemMaterial, ItemCategory itemCategory){
        if(!ItemTable.ITEM_INFO.containsKey(itemMaterial)){
            return false;
        }
        return ItemTable.ITEM_INFO.get(itemMaterial).category == itemCategory;
    }
    public static ItemStack getEnchantedItem(ItemStack item, @Nullable Enchantment enchantment, boolean toDisplay){
        ItemStack itemClone = item.clone();
        if(toDisplay){
            InventoryUtil.setItemLore(itemClone,
                    List.of(Component.text(InventoryUtil.toCurrency(getEnchantmentPrice(item, enchantment)) + " Moedas", NamedTextColor.GOLD))
            );
        }
        if(enchantment == null){
            Damageable itemMeta = (Damageable) itemClone.getItemMeta();
            itemMeta.setDamage(0);
            itemClone.setItemMeta(itemMeta);
            return itemClone;
        }
        int enchantmentLevel = item.getEnchantmentLevel(enchantment);
        itemClone.addUnsafeEnchantment(enchantment, enchantmentLevel + 1);
        return itemClone;
    }
    public static long getEnchantmentPrice(ItemStack item, @Nullable Enchantment enchantment){
        if(enchantment == null){
            return Math.round(75000 * ItemTable.TIER_MULTIPLIER.get(ItemTable.ITEM_INFO.get(item.getType()).tier));
        }

        int enchantmentLevel = item.getEnchantmentLevel(enchantment);
        ItemTier tier = ItemTable.ITEM_INFO.get(item.getType()).tier;
        double multiplier = ItemTable.TIER_MULTIPLIER.get(tier) * ItemTable.ENCHANT_MULTIPLIER.get(enchantment);
        return Math.round(Math.pow(10, 2 + enchantmentLevel) * multiplier);
    }

}
