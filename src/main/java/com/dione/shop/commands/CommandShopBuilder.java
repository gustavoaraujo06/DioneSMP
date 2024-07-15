package com.dione.shop.commands;

import com.dione.shop.gui.GUIShop;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class CommandShopBuilder implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(args.length != 1){
            commandSender.sendMessage(Component.text("Uso Incorreto! use /lojabuilder <jogador>").color(NamedTextColor.RED));
            return false;
        }
        if(Bukkit.getPlayer(args[0]) == null){
            commandSender.sendMessage(Component.text("Jogador não encontrado!").color(NamedTextColor.RED));
            return false;
        }

        HashMap<Material, Double> buyItems = new HashMap<>();
        buyItems.put(Material.OAK_LOG, 124.0);
        buyItems.put(Material.DARK_OAK_LOG, 124.0);
        buyItems.put(Material.SPRUCE_LOG, 124.0);
        buyItems.put(Material.BIRCH_LOG, 124.0);
        buyItems.put(Material.JUNGLE_LOG, 124.0);
        buyItems.put(Material.STONE, 64.0);
        buyItems.put(Material.DEEPSLATE, 64.0);
        buyItems.put(Material.COBBLED_DEEPSLATE, 64.0);
        buyItems.put(Material.COBBLESTONE, 32.0);
        buyItems.put(Material.BRICKS, 1024.0);
        buyItems.put(Material.GLASS, 128.0);
        buyItems.put(Material.SANDSTONE, 256.0);
        buyItems.put(Material.SMOOTH_STONE, 512.0);
        buyItems.put(Material.GRAVEL, 64.0);
        buyItems.put(Material.DIORITE, 128.0);
        buyItems.put(Material.ANDESITE, 128.0);
        buyItems.put(Material.GRANITE, 128.0);
        buyItems.put(Material.NETHER_BRICKS, 1024.0);
        buyItems.put(Material.QUARTZ_BLOCK, 2048.0);
        buyItems.put(Material.SEA_LANTERN, 1024.0);
        buyItems.put(Material.GLOWSTONE, 512.0);
        buyItems.put(Material.END_STONE, 512.0);
        buyItems.put(Material.PURPUR_BLOCK, 1024.0);
        buyItems.put(Material.PRISMARINE, 2048.0);
        buyItems.put(Material.PRISMARINE_BRICKS, 2048.0);
        buyItems.put(Material.DARK_PRISMARINE, 4096.0);
        buyItems.put(Material.OBSIDIAN, 4096.0);
        buyItems.put(Material.TERRACOTTA, 256.0);
        buyItems.put(Material.WHITE_TERRACOTTA, 256.0);
        buyItems.put(Material.SMOOTH_QUARTZ, 2048.0);
        buyItems.put(Material.SMOOTH_SANDSTONE, 256.0);
        buyItems.put(Material.POLISHED_ANDESITE, 256.0);
        buyItems.put(Material.POLISHED_DIORITE, 256.0);
        buyItems.put(Material.POLISHED_GRANITE, 256.0);
        buyItems.put(Material.BLUE_ICE, 2048.0);
        buyItems.put(Material.CRIMSON_PLANKS, 512.0);
        buyItems.put(Material.WARPED_PLANKS, 512.0);
        buyItems.put(Material.BASALT, 256.0);
        buyItems.put(Material.BLACKSTONE, 256.0);
        buyItems.put(Material.GILDED_BLACKSTONE, 2048.0);
        buyItems.put(Material.CHAIN, 1024.0);
        buyItems.put(Material.LANTERN, 512.0);

        // Itens de Farm
        buyItems.put(Material.SOUL_SAND, 256.0);




        GUIShop builderShop = new GUIShop(
                buyItems,
                0.5,
                Component.text("Builder", NamedTextColor.DARK_PURPLE, TextDecoration.BOLD),
                Material.PURPLE_STAINED_GLASS_PANE,
                Material.SPIDER_EYE,
                Material.MAGMA_CREAM);

        Player player = Bukkit.getPlayer(args[0]);
        player.openInventory(builderShop.inv);
        return false;
    }
}
