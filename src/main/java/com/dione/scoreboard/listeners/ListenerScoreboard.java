package com.dione.scoreboard.listeners;

import com.dione.main.Main;
import com.dione.util.InventoryUtil;
import net.ess3.api.events.UserBalanceUpdateEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.scoreboard.*;

import java.util.HashMap;
import java.util.UUID;

public class ListenerScoreboard implements Listener {
    private HashMap<UUID, Integer> playerKills = new HashMap<>();
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        playerKills.put(player.getUniqueId(), 0);

        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective objective = board.registerNewObjective("board", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.displayName(Component.text("Dione", NamedTextColor.RED).append(
                Component.text("SMP", NamedTextColor.WHITE)
        ));
        Score space = objective.getScore(" ");
        space.setScore(3);

        Team userBal = board.registerNewTeam("userBal");
        userBal.addEntry(ChatColor.RED.toString());
        userBal.prefix(Component.text("Balance: ", NamedTextColor.RED));
        userBal.suffix(Component.text(InventoryUtil.toCurrency(Main.econ.getBalance(player)), NamedTextColor.GOLD));
        objective.getScore(ChatColor.RED.toString()).setScore(2);

        Team userKills = board.registerNewTeam("userKills");
        userKills.addEntry(ChatColor.DARK_PURPLE.toString());
        userKills.prefix(Component.text("Kills: ", NamedTextColor.RED));
        userKills.suffix(Component.text(0, NamedTextColor.DARK_PURPLE));
        objective.getScore(ChatColor.DARK_PURPLE.toString()).setScore(1);

        player.setScoreboard(board);
    }
    @EventHandler
    public void onBalanceChange(UserBalanceUpdateEvent e){
        e.getPlayer()
                .getScoreboard()
                .getTeam("userBal")
                .suffix(Component.text(InventoryUtil.toCurrency(e.getNewBalance().doubleValue()), NamedTextColor.GOLD));
    }
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        if(e.getPlayer().getKiller() != null){
            if(e.getPlayer().getKiller() instanceof Player player){
                playerKills.replace(player.getUniqueId(),
                        playerKills.get(player.getUniqueId()) + 1);
                player.getScoreboard().getTeam("userKills").suffix(
                        Component.text(playerKills.get(player.getUniqueId()), NamedTextColor.DARK_PURPLE)
                );
            }
        }
    }

}
