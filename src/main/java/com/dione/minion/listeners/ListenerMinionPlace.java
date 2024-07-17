package com.dione.minion.listeners;

import com.dione.logger.Logger;
import com.dione.main.Main;
import com.dione.minion.PotatoMinion;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class ListenerMinionPlace implements Listener {
    @EventHandler
    public void OnItemUse(PlayerInteractEvent event){
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(event.getItem() != null){
                ItemStack item = event.getItem();
                ItemMeta meta = item.getItemMeta();
                NamespacedKey key = new NamespacedKey(Main.getInstance(), "minionType");
                if(meta.getPersistentDataContainer().has(key)){
                    event.setCancelled(true);
                    event.getPlayer().getInventory().removeItem(event.getItem());
                    switch (meta.getPersistentDataContainer().get(key, PersistentDataType.INTEGER)){
                        case 0:
                            new PotatoMinion(event.getInteractionPoint());
                            break;
                    }
                }
            }
        }
    }
}
