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

public class CommandShopFarmer implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(args.length != 1){
            commandSender.sendMessage(Component.text("Uso Incorreto! use /lojafarmer <jogador>").color(NamedTextColor.RED));
            return false;
        }
        if(Bukkit.getPlayer(args[0]) == null){
            commandSender.sendMessage(Component.text("Jogador não encontrado!").color(NamedTextColor.RED));
            return false;
        }

        HashMap<Material, Double> buyItems = new HashMap<>();
        buyItems.put(Material.BROWN_MUSHROOM, 2048.0);
        buyItems.put(Material.RED_MUSHROOM, 2048.0);
        buyItems.put(Material.MELON_SEEDS, 2048.0);
        buyItems.put(Material.PUMPKIN_SEEDS, 2048.0);
        buyItems.put(Material.CACTUS, 1536.0);
        buyItems.put(Material.BAMBOO, 1280.0);
        buyItems.put(Material.SUGAR_CANE, 1024.0);
        buyItems.put(Material.BEETROOT_SEEDS, 1024.0);
        buyItems.put(Material.CARROT, 1024.0);
        buyItems.put(Material.POTATO, 1024.0);
        buyItems.put(Material.NETHER_WART, 1024.0);

        HashMap<Material, Double> sellItems = new HashMap<>();
        sellItems.put(Material.BROWN_MUSHROOM, 75.0);
        sellItems.put(Material.RED_MUSHROOM, 75.0);
        sellItems.put(Material.MELON_SLICE, 30.0);
        sellItems.put(Material.PUMPKIN, 75.0);
        sellItems.put(Material.CACTUS, 40.0);
        sellItems.put(Material.BAMBOO, 35.0);
        sellItems.put(Material.SUGAR_CANE, 30.0);
        sellItems.put(Material.BEETROOT, 30.0);
        sellItems.put(Material.CARROT, 30.0);
        sellItems.put(Material.POTATO, 30.0);
        sellItems.put(Material.NETHER_WART, 30.0);


        GUIShop farmerShop = new GUIShop(
                buyItems,
                sellItems,
                Component.text("Fazendeiro", NamedTextColor.GOLD, TextDecoration.BOLD),
                Material.ORANGE_STAINED_GLASS_PANE,
                Material.WHEAT_SEEDS,
                Material.WHEAT);

        Player player = Bukkit.getPlayer(args[0]);
        player.openInventory(farmerShop.inv);
        return false;
    }
}
