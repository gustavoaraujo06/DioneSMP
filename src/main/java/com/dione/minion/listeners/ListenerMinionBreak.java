package com.dione.minion.listeners;

import com.dione.main.Main;
import com.dione.minion.Minion;
import com.dione.minion.MinionManager;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

public class ListenerMinionBreak implements Listener {
    @EventHandler
    public void OnMinionBreak(EntityDamageByEntityEvent event){
        NamespacedKey key = new NamespacedKey(Main.getInstance(), "minionId");
        if(event.getEntity().getPersistentDataContainer().has(key)){
            event.setCancelled(true);
            if(event.getDamager() instanceof Player player){
                event.getEntity().remove();
                UUID minionId = UUID.fromString(event.getEntity().getPersistentDataContainer().get(key, PersistentDataType.STRING));
                Minion minion = MinionManager.getMinion(minionId);
                MinionManager.removeMinion(minionId);
                ItemStack minionItem = Minion.getMinionItem(minion.getType());
                player.getInventory().addItem(minionItem);
                minion.stopWorking();
            }
        }
    }
}
