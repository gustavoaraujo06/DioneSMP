package com.dione.enchant;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

import java.util.HashMap;

public  class ItemTable {
    public static final HashMap<Material, MaterialInfo> ITEM_INFO = new HashMap<>(){
        {
            put(Material.DIAMOND_PICKAXE, new MaterialInfo(ItemCategory.TOOLS, ItemTier.DIAMOND));
            put(Material.DIAMOND_AXE, new MaterialInfo(ItemCategory.TOOLS, ItemTier.DIAMOND));
            put(Material.DIAMOND_SHOVEL, new MaterialInfo(ItemCategory.TOOLS, ItemTier.DIAMOND));
            put(Material.DIAMOND_HOE, new MaterialInfo(ItemCategory.TOOLS, ItemTier.DIAMOND));
            put(Material.DIAMOND_HELMET, new MaterialInfo(ItemCategory.ARMOR, ItemTier.DIAMOND));
            put(Material.DIAMOND_CHESTPLATE, new MaterialInfo(ItemCategory.ARMOR, ItemTier.DIAMOND));
            put(Material.DIAMOND_LEGGINGS, new MaterialInfo(ItemCategory.ARMOR, ItemTier.DIAMOND));
            put(Material.DIAMOND_BOOTS, new MaterialInfo(ItemCategory.ARMOR, ItemTier.DIAMOND));
            put(Material.DIAMOND_SWORD, new MaterialInfo(ItemCategory.SWORD, ItemTier.DIAMOND));
            put(Material.IRON_PICKAXE, new MaterialInfo(ItemCategory.TOOLS, ItemTier.IRON));
            put(Material.IRON_AXE, new MaterialInfo(ItemCategory.TOOLS, ItemTier.IRON));
            put(Material.IRON_SHOVEL, new MaterialInfo(ItemCategory.TOOLS, ItemTier.IRON));
            put(Material.IRON_HOE, new MaterialInfo(ItemCategory.TOOLS, ItemTier.IRON));
            put(Material.IRON_HELMET, new MaterialInfo(ItemCategory.ARMOR, ItemTier.IRON));
            put(Material.IRON_CHESTPLATE, new MaterialInfo(ItemCategory.ARMOR, ItemTier.IRON));
            put(Material.IRON_LEGGINGS, new MaterialInfo(ItemCategory.ARMOR, ItemTier.IRON));
            put(Material.IRON_BOOTS, new MaterialInfo(ItemCategory.ARMOR, ItemTier.IRON));
            put(Material.IRON_SWORD, new MaterialInfo(ItemCategory.SWORD, ItemTier.IRON));
            put(Material.GOLDEN_PICKAXE, new MaterialInfo(ItemCategory.TOOLS, ItemTier.GOLD));
            put(Material.GOLDEN_AXE, new MaterialInfo(ItemCategory.TOOLS, ItemTier.GOLD));
            put(Material.GOLDEN_SHOVEL, new MaterialInfo(ItemCategory.TOOLS, ItemTier.GOLD));
            put(Material.GOLDEN_HOE, new MaterialInfo(ItemCategory.TOOLS, ItemTier.GOLD));
            put(Material.GOLDEN_HELMET, new MaterialInfo(ItemCategory.ARMOR, ItemTier.GOLD));
            put(Material.GOLDEN_CHESTPLATE, new MaterialInfo(ItemCategory.ARMOR, ItemTier.GOLD));
            put(Material.GOLDEN_LEGGINGS, new MaterialInfo(ItemCategory.ARMOR, ItemTier.GOLD));
            put(Material.GOLDEN_BOOTS, new MaterialInfo(ItemCategory.ARMOR, ItemTier.GOLD));
            put(Material.GOLDEN_SWORD, new MaterialInfo(ItemCategory.SWORD, ItemTier.GOLD));
            put(Material.NETHERITE_PICKAXE, new MaterialInfo(ItemCategory.TOOLS, ItemTier.NETHERITE));
            put(Material.NETHERITE_AXE, new MaterialInfo(ItemCategory.TOOLS, ItemTier.NETHERITE));
            put(Material.NETHERITE_SHOVEL, new MaterialInfo(ItemCategory.TOOLS, ItemTier.NETHERITE));
            put(Material.NETHERITE_HOE, new MaterialInfo(ItemCategory.TOOLS, ItemTier.NETHERITE));
            put(Material.NETHERITE_HELMET, new MaterialInfo(ItemCategory.ARMOR, ItemTier.NETHERITE));
            put(Material.NETHERITE_CHESTPLATE, new MaterialInfo(ItemCategory.ARMOR, ItemTier.NETHERITE));
            put(Material.NETHERITE_LEGGINGS, new MaterialInfo(ItemCategory.ARMOR, ItemTier.NETHERITE));
            put(Material.NETHERITE_BOOTS, new MaterialInfo(ItemCategory.ARMOR, ItemTier.NETHERITE));
            put(Material.NETHERITE_SWORD, new MaterialInfo(ItemCategory.SWORD, ItemTier.NETHERITE));
            put(Material.STONE_PICKAXE, new MaterialInfo(ItemCategory.TOOLS, ItemTier.STONE));
            put(Material.STONE_AXE, new MaterialInfo(ItemCategory.TOOLS, ItemTier.STONE));
            put(Material.STONE_SHOVEL, new MaterialInfo(ItemCategory.TOOLS, ItemTier.STONE));
            put(Material.STONE_HOE, new MaterialInfo(ItemCategory.TOOLS, ItemTier.STONE));
            put(Material.STONE_SWORD, new MaterialInfo(ItemCategory.SWORD, ItemTier.STONE));
            put(Material.WOODEN_PICKAXE, new MaterialInfo(ItemCategory.TOOLS, ItemTier.WOOD));
            put(Material.WOODEN_AXE, new MaterialInfo(ItemCategory.TOOLS, ItemTier.WOOD));
            put(Material.WOODEN_SHOVEL, new MaterialInfo(ItemCategory.TOOLS, ItemTier.WOOD));
            put(Material.WOODEN_HOE, new MaterialInfo(ItemCategory.TOOLS, ItemTier.WOOD));
            put(Material.LEATHER_HELMET, new MaterialInfo(ItemCategory.ARMOR, ItemTier.WOOD));
            put(Material.LEATHER_CHESTPLATE, new MaterialInfo(ItemCategory.ARMOR, ItemTier.WOOD));
            put(Material.LEATHER_LEGGINGS, new MaterialInfo(ItemCategory.ARMOR, ItemTier.WOOD));
            put(Material.LEATHER_BOOTS, new MaterialInfo(ItemCategory.ARMOR, ItemTier.WOOD));
            put(Material.WOODEN_SWORD, new MaterialInfo(ItemCategory.SWORD, ItemTier.WOOD));
        }
    };
    public static final HashMap<ItemTier, Double> TIER_MULTIPLIER = new HashMap<>(){
        {
            put(ItemTier.DIAMOND, 1.0);
            put(ItemTier.IRON, 0.75);
            put(ItemTier.GOLD, 0.5);
            put(ItemTier.STONE, 0.5);
            put(ItemTier.WOOD, 0.25);
            put(ItemTier.NETHERITE, 2.5);
        }
    };
    public static final HashMap<Enchantment, Double> ENCHANT_MULTIPLIER = new HashMap<>(){
        {
            //tools
            put(Enchantment.EFFICIENCY, 1.0);
            put(Enchantment.FORTUNE, 50.0);
            put(Enchantment.UNBREAKING, 350.0);
            put(Enchantment.SILK_TOUCH, 40000.0);

            //armor
            put(Enchantment.PROTECTION, 10.0);
            put(Enchantment.MENDING, 40000.0);

            //sword
            put(Enchantment.SHARPNESS, 1.0);
            put(Enchantment.KNOCKBACK, 10000.0);
            put(Enchantment.LOOTING, 75.0);
            put(Enchantment.FIRE_ASPECT, 1000.0);
        }
    };
}
