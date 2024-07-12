package com.dione.shopSmith;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class ToolTable {
    public static Map<Enchantment, Double> enchantmentMultiplier = new HashMap<>();
    public static Map<String, Double> tierMultiplier = new HashMap<>();

    public static Set<Material> allowedTools = new HashSet<>();
    static{
        enchantmentMultiplier.put(Enchantment.EFFICIENCY, 1.0);
        enchantmentMultiplier.put(Enchantment.FORTUNE, 350.0);
        enchantmentMultiplier.put(Enchantment.UNBREAKING, 500.0);
        enchantmentMultiplier.put(Enchantment.SILK_TOUCH, 35000.0);

        tierMultiplier.put("diamond", 1.0);
        tierMultiplier.put("iron", 0.75);
        tierMultiplier.put("stone", 0.3);
        tierMultiplier.put("wood", 0.2);
        tierMultiplier.put("gold", 0.5);
        tierMultiplier.put("netherite", 2.5);

        allowedTools.add(Material.DIAMOND_PICKAXE);
        allowedTools.add(Material.DIAMOND_AXE);
        allowedTools.add(Material.DIAMOND_SHOVEL);
        allowedTools.add(Material.IRON_PICKAXE);
        allowedTools.add(Material.IRON_AXE);
        allowedTools.add(Material.IRON_SHOVEL);
        allowedTools.add(Material.GOLDEN_PICKAXE);
        allowedTools.add(Material.GOLDEN_AXE);
        allowedTools.add(Material.GOLDEN_SHOVEL);
        allowedTools.add(Material.WOODEN_PICKAXE);
        allowedTools.add(Material.WOODEN_AXE);
        allowedTools.add(Material.WOODEN_SHOVEL);
        allowedTools.add(Material.STONE_PICKAXE);
        allowedTools.add(Material.STONE_AXE);
        allowedTools.add(Material.STONE_SHOVEL);
        allowedTools.add(Material.NETHERITE_PICKAXE);
        allowedTools.add(Material.NETHERITE_AXE);
        allowedTools.add(Material.NETHERITE_SHOVEL);

    }

    public static double getMultiplier(Enchantment type, Material tier){
        switch (tier){
            case Material.DIAMOND_PICKAXE:
            case Material.DIAMOND_AXE:
            case Material.DIAMOND_SHOVEL:{
                return tierMultiplier.get("diamond") * enchantmentMultiplier.get(type);
            }
            case Material.IRON_PICKAXE:
            case Material.IRON_AXE:
            case Material.IRON_SHOVEL:{
                return tierMultiplier.get("iron") * enchantmentMultiplier.get(type);
            }
            case Material.GOLDEN_PICKAXE:
            case Material.GOLDEN_AXE:
            case Material.GOLDEN_SHOVEL:{
                return tierMultiplier.get("gold") * enchantmentMultiplier.get(type);
            }
            case Material.STONE_PICKAXE:
            case Material.STONE_AXE:
            case Material.STONE_SHOVEL:{
                return tierMultiplier.get("stone") * enchantmentMultiplier.get(type);
            }
            case Material.WOODEN_PICKAXE:
            case Material.WOODEN_AXE:
            case Material.WOODEN_SHOVEL:{
                return tierMultiplier.get("wood") * enchantmentMultiplier.get(type);
            }
            case Material.NETHERITE_PICKAXE:
            case Material.NETHERITE_AXE:
            case Material.NETHERITE_SHOVEL:{
                return tierMultiplier.get("netherite") * enchantmentMultiplier.get(type);
            }
            default:
                return tierMultiplier.get("diamond") * enchantmentMultiplier.get(type);
        }
    }
    public static boolean isToolValid(Material material){
        return allowedTools.contains(material);
    }
    public static long getPrice(ItemStack item, Enchantment type){
        int enchantmentLevel = item.getEnchantmentLevel(type);
        return Math.round(Math.max(100.0, Math.pow(10, 2 + enchantmentLevel) * getMultiplier(type, item.getType())));
    }
}
