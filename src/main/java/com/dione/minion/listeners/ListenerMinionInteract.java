package com.dione.minion.listeners;

import com.dione.logger.Logger;
import com.dione.main.Main;
import com.dione.minion.Minion;
import com.dione.minion.MinionManager;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

public class ListenerMinionInteract implements Listener {
    @EventHandler
    public void OnMinionInteract(PlayerArmorStandManipulateEvent event){
        NamespacedKey key = new NamespacedKey(Main.getInstance(), "minionId");
        Logger.Log("ue vei");
        if(event.getRightClicked().getPersistentDataContainer().has(key)){
            Logger.Log("ue mais ainda vei");
            event.setCancelled(true);
            UUID minionId = UUID.fromString(event.getRightClicked().getPersistentDataContainer().get(key, PersistentDataType.STRING));
            if(MinionManager.getMinion(minionId) != null){
                Minion minion = MinionManager.getMinion(minionId);
                Player player = event.getPlayer();
                player.openInventory(minion.getInventory());
            }
        }
    }
}
