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

public class CommandShopMiner implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(args.length != 1){
            commandSender.sendMessage(Component.text("Uso Incorreto! use /lojaminer <jogador>").color(NamedTextColor.RED));
            return false;
        }
        if(Bukkit.getPlayer(args[0]) == null){
            commandSender.sendMessage(Component.text("Jogador não encontrado!").color(NamedTextColor.RED));
            return false;
        }
        HashMap<Material, Double> buyItems = new HashMap<>();
        buyItems.put(Material.DIAMOND, 2048.0);
        buyItems.put(Material.EMERALD, 2048.0);
        buyItems.put(Material.IRON_INGOT, 1024.0);
        buyItems.put(Material.GOLD_INGOT, 1024.0);
        buyItems.put(Material.COAL, 128.0);
        buyItems.put(Material.CHARCOAL, 128.0);
        buyItems.put(Material.REDSTONE, 2048.0);
        buyItems.put(Material.LAPIS_LAZULI, 2048.0);


        GUIShop minerShop = new GUIShop(
                buyItems,
                0.1,
                Component.text("Minerador", NamedTextColor.GOLD, TextDecoration.BOLD),
                Material.CYAN_STAINED_GLASS_PANE,
                Material.DIAMOND,
                Material.NAME_TAG);
        Player player = Bukkit.getPlayer(args[0]);
        player.openInventory(minerShop.inv);
        return false;
    }
}
