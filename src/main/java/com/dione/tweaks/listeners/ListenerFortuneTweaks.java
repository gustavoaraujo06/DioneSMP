package com.dione.tweaks.listeners;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.data.Ageable;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.joml.Matrix2d;

public class ListenerFortuneTweaks implements Listener {
    @EventHandler
    public void OnBlockBreak(BlockBreakEvent event){
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        //para machado
        if(toolType(item.getType()).equals("axe")){
            Material blockType = event.getBlock().getType();
            //para melancia
            if(blockType == Material.MELON){
                event.setDropItems(false);
                ItemStack melonSlice = new ItemStack(Material.MELON_SLICE);
                int fortuneLevel = item.getEnchantmentLevel(Enchantment.FORTUNE);
                int amount = 1 + (int)Math.pow(2, fortuneLevel);
                melonSlice.setAmount(amount);
                event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), melonSlice);
            }
        }
        if(toolType(item.getType()).equals("hoe")){
            Material blockType = event.getBlock().getType();

            int fortuneLevel = player.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.FORTUNE);
            if(blockType == Material.WHEAT || blockType == Material.BEETROOTS){
                event.setDropItems(false);
                Ageable seed = (Ageable) event.getBlock().getBlockData();
                if(seed.getAge() == seed.getMaximumAge()){

                    ItemStack seedDrop;
                    ItemStack seedSeed;

                    if(blockType == Material.BEETROOTS){
                        seedDrop = new ItemStack(Material.BEETROOT);
                        seedSeed = new ItemStack(Material.BEETROOT_SEEDS);
                    }else{
                        seedDrop = new ItemStack(Material.WHEAT);
                        seedSeed = new ItemStack(Material.WHEAT_SEEDS);
                    }

                    int amount = 1 + (int)Math.pow(2, fortuneLevel);

                    seedDrop.setAmount(amount);
                    seedSeed.setAmount(2);

                    event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), seedDrop);
                    event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), seedSeed);
                }
            }
            if(blockType == Material.CARROTS || blockType == Material.POTATOES || blockType == Material.NETHER_WART){
                Ageable seed = (Ageable) event.getBlock().getBlockData();
                event.setDropItems(false);
                if(seed.getAge() == seed.getMaximumAge()){
                    ItemStack seedDrop = switch (blockType) {
                        case Material.CARROTS -> new ItemStack(Material.CARROT);
                        case Material.POTATOES -> new ItemStack(Material.POTATO);
                        default -> new ItemStack(Material.NETHER_WART);
                    };

                    int amount = 1 + (int)Math.pow(3, fortuneLevel);

                    seedDrop.setAmount(amount);

                    event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), seedDrop);
                }
            }

        }

    }
    public String toolType(Material type){
        switch (type){
            case DIAMOND_AXE:
            case IRON_AXE:
            case STONE_AXE:
            case GOLDEN_AXE:
            case WOODEN_AXE:
            case NETHERITE_AXE:{
                return "axe";
            }
            case DIAMOND_PICKAXE:
            case IRON_PICKAXE:
            case STONE_PICKAXE:
            case GOLDEN_PICKAXE:
            case WOODEN_PICKAXE:
            case NETHERITE_PICKAXE:{
                return "pickaxe";
            }
            case DIAMOND_HOE:
            case IRON_HOE:
            case STONE_HOE:
            case GOLDEN_HOE:
            case WOODEN_HOE:
            case NETHERITE_HOE:{
                return "hoe";
            }
            default:
                return "";
        }
    }
}
