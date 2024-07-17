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

public class CommandShopWitch implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(args.length != 1){
            commandSender.sendMessage(Component.text("Uso Incorreto! use /lojawitch <jogador>").color(NamedTextColor.RED));
            return false;
        }
        if(Bukkit.getPlayer(args[0]) == null){
            commandSender.sendMessage(Component.text("Jogador não encontrado!").color(NamedTextColor.RED));
            return false;
        }

        HashMap<Material, Double> buyItems = new HashMap<>();
        buyItems.put(Material.ROTTEN_FLESH, 512.0);
        buyItems.put(Material.ENDER_PEARL, 512.0);
        buyItems.put(Material.BLAZE_ROD, 512.0);
        buyItems.put(Material.STRING, 512.0);
        buyItems.put(Material.BONE, 512.0);
        buyItems.put(Material.LEATHER, 512.0);
        buyItems.put(Material.FEATHER, 512.0);
        buyItems.put(Material.SLIME_BALL, 512.0);
        buyItems.put(Material.GUNPOWDER, 512.0);
        buyItems.put(Material.SPIDER_EYE, 512.0);
        buyItems.put(Material.MAGMA_CREAM, 512.0);




        GUIShop witchShop = new GUIShop(
                buyItems,
                0.1,
                Component.text("Bruxa", NamedTextColor.DARK_PURPLE, TextDecoration.BOLD),
                Material.PURPLE_STAINED_GLASS_PANE,
                Material.SPIDER_EYE,
                Material.MAGMA_CREAM);

        Player player = Bukkit.getPlayer(args[0]);
        player.openInventory(witchShop.inv);
        return false;
    }
}
