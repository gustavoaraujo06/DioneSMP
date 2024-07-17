package com.dione.minion.commands;

import com.dione.main.Main;
import com.dione.minion.Minion;
import com.dione.minion.MinionType;
import com.dione.minion.PotatoMinion;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class CommandGiveMinion implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(args.length != 1){
            commandSender.sendMessage(Component.text("Uso invalido, use /giveminion <Type>", NamedTextColor.RED));
        }
        if(commandSender instanceof Player player){
            ItemStack minionItem = Minion.getMinionItem(MinionType.POTATO);
            player.getInventory().addItem(minionItem);
        }
        return false;
    }
}
