package com.dione.tweaks.listeners;

import com.dione.main.Main;
import com.dione.shopSpawner.gui.GUIShopSpawner;
import com.dione.shopSpawner.gui.GUIShopSpawnerBuy;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

public class ListenerTweaks implements Listener {
    @EventHandler
    public void OnBlockBreak(BlockBreakEvent e){
        if(e.getBlock().getType() == Material.SPAWNER){
            Player player = (Player)e.getPlayer();
            ItemStack itemStack = player.getInventory().getItemInMainHand();
            if(itemStack.containsEnchantment(Enchantment.SILK_TOUCH)){
                e.setExpToDrop(0);

                CreatureSpawner spawner = (CreatureSpawner) e.getBlock().getState();

                ItemStack stack = new ItemStack(Material.SPAWNER);

                BlockStateMeta itemMeta = (BlockStateMeta) stack.getItemMeta();
                CreatureSpawner itemSpawner = (CreatureSpawner) itemMeta.getBlockState();
                itemSpawner.setSpawnedType(spawner.getSpawnedType());
                itemMeta.setBlockState(itemSpawner);

                itemMeta.displayName(Component.text("Spawner de ", NamedTextColor.DARK_PURPLE).append(
                        Component.translatable(spawner.getSpawnedEntity().getEntityType().translationKey(), NamedTextColor.DARK_PURPLE)
                ));

                stack.setItemMeta(itemMeta);

                e.setDropItems(false);

                player.getInventory().addItem(stack);
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.f, 1.f);

                Main.getInstance().getLogger().info(player.getName() + " quebrou 1 spawner");
            }
        }
    }
    @EventHandler
    public void OnBlockPlace(BlockPlaceEvent event){
        ItemStack hand = event.getItemInHand();
        if(hand.getType() == Material.SPAWNER){
            BlockStateMeta spawnerMeta = (BlockStateMeta) hand.getItemMeta();
            CreatureSpawner originalState = (CreatureSpawner) spawnerMeta.getBlockState();

            CreatureSpawner placedState = (CreatureSpawner) event.getBlockPlaced().getState();
            placedState.setSpawnedType(originalState.getSpawnedType());
            placedState.update();
            Player player = (Player) event.getPlayer();
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1.f, 1.f);
        }
    }
}
