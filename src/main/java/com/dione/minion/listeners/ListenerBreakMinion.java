package com.dione.minion.listeners;

import com.dione.main.Main;
import com.dione.minion.Minion;
import com.dione.minion.MinionManager;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

public class ListenerBreakMinion implements Listener {
    @EventHandler
    public void OnMinionBreak(EntityDamageByEntityEvent event){
        if(event.getDamager() instanceof Player player && event.getEntity() instanceof ArmorStand stand){
            NamespacedKey key = new NamespacedKey(Main.getInstance(), "minionId");
            if(stand.getPersistentDataContainer().has(key)){
                event.setCancelled(true);
                UUID minionId = UUID.fromString(stand.getPersistentDataContainer().get(key, PersistentDataType.STRING));
                Minion minion = MinionManager.getMinion(minionId);
                if(minion != null){
                    minion.stopTask();
                    minion.dropInventory();
                    MinionManager.removeMinion(minionId);
                    stand.remove();
                    //should give the player the minionItem :)
                }
            }
        }
    }
}
