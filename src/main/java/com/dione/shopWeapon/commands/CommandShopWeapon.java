package com.dione.shopWeapon.commands;

import com.dione.shopSmith.gui.GUIShopSmith;
import com.dione.shopWeapon.gui.GUIShopWeapon;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandShopWeapon implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(args.length != 1){
            commandSender.sendMessage(Component.text("Uso Incorreto! use /lojaweapon <jogador>").color(NamedTextColor.RED));
            return false;
        }
        if(Bukkit.getPlayer(args[0]) == null){
            commandSender.sendMessage(Component.text("Jogador não encontrado!").color(NamedTextColor.RED));
            return false;
        }
        Player player = Bukkit.getPlayer(args[0]);
        player.openInventory(GUIShopWeapon.inv);
        return false;
    }
}
