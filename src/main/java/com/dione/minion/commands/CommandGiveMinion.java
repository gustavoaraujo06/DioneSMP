package com.dione.minion.commands;

import com.dione.minion.HarvestMinion;
import com.dione.minion.MinionType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandGiveMinion implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(args.length != 1){
            commandSender.sendMessage(Component.text("Uso incorreto!, use /giveminion <type>!", NamedTextColor.RED));
            return false;
        }
        if(commandSender instanceof Player player){
            MinionType type = MinionType.getType(args[0]);
            if(type == MinionType.HARVEST){
                player.getInventory().addItem(HarvestMinion.newMinionItemStack());
            }
        }
        return false;
    }
}
