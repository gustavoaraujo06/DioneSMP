package com.dione.minion.listeners;

import com.dione.logger.Logger;
import com.dione.main.Main;
import com.dione.minion.Minion;
import com.dione.minion.MinionManager;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

public class ListenerInteractMinion implements Listener {
    @EventHandler
    public void OnInteractMinion(PlayerInteractAtEntityEvent event){
        if(event.getRightClicked() instanceof ArmorStand stand){
            NamespacedKey key = new NamespacedKey(Main.getInstance(), "minionId");
            if(stand.getPersistentDataContainer().has(key)){
                event.setCancelled(true);
                String minionIdString = stand.getPersistentDataContainer().get(key, PersistentDataType.STRING);
                UUID minionId = UUID.fromString(minionIdString);
                Minion minion = MinionManager.getMinion(minionId);
                if(minion != null){
                    event.getPlayer().openInventory(minion.getInventory());
                }
            }
        }
    }
}
