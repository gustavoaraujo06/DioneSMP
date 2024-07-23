package com.dione.minion.listeners;

import com.dione.main.Main;
import com.dione.minion.HarvestMinion;
import com.dione.minion.Minion;
import com.dione.minion.MinionManager;
import com.dione.minion.MinionType;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class ListenerPlaceMinion implements Listener {
    @EventHandler
    public void OnPlaceMinion(PlayerInteractEvent event){
        ItemStack item = event.getItem();;
        NamespacedKey key = new NamespacedKey(Main.getInstance(), "minionType");
        if (item != null) {
            ItemMeta itemMeta = item.getItemMeta();
            if(itemMeta.getPersistentDataContainer().has(key) && event.getAction() == Action.RIGHT_CLICK_BLOCK){
                event.setCancelled(true);
                MinionType type = MinionType.getType(itemMeta.getPersistentDataContainer().get(key, PersistentDataType.STRING));
                if(type == MinionType.HARVEST){
                    Block block = event.getClickedBlock();
                    if(block.getType() != Material.AIR){
                        Location location = block.getLocation();
                        location.add(0.5, 1, 0.5);
                        HarvestMinion minion = new HarvestMinion(location, Material.POTATOES, Material.POTATO);
                        minion.startTask();
                        MinionManager.addMinion(minion.getMinionId(), minion);
                    }
                }
            }
        }
    }
}
