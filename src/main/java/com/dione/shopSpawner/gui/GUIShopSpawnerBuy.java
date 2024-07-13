package com.dione.shopSpawner.gui;

import com.dione.shopSpawner.SpawnerInfo;
import com.dione.util.InventoryUtil;
import com.dione.util.ShopItemInfo;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class GUIShopSpawnerBuy {
    public static Inventory inv;
    public static HashMap<Integer, SpawnerInfo> buyItems = new HashMap<>();
    static{
        inv = InventoryUtil.createMenu(null,
                Component.text("Comprar", NamedTextColor.DARK_RED), 18, Component.text("Fechar", NamedTextColor.RED));
        buyItems.put(1, new SpawnerInfo(EntityType.WITHER_SKELETON,10000000.0));
        buyItems.put(2, new SpawnerInfo(EntityType.IRON_GOLEM,1000000.0));
        buyItems.put(3, new SpawnerInfo(EntityType.BLAZE,1000000.0));
        buyItems.put(4, new SpawnerInfo(EntityType.ENDERMAN,1000000.0));
        buyItems.put(5, new SpawnerInfo(EntityType.SLIME,500000.0));
        buyItems.put(6, new SpawnerInfo(EntityType.MAGMA_CUBE,500000.0));
        buyItems.put(7, new SpawnerInfo(EntityType.CREEPER,500000.0));
        buyItems.put(8, new SpawnerInfo(EntityType.ZOMBIFIED_PIGLIN,100000.0));
        buyItems.put(9, new SpawnerInfo(EntityType.SKELETON,100000.0));
        buyItems.put(10, new SpawnerInfo(EntityType.SPIDER,100000.0));
        buyItems.put(11, new SpawnerInfo(EntityType.ZOMBIE,100000.0));
        buyItems.put(12, new SpawnerInfo(EntityType.COW,100000.0));
        buyItems.put(13, new SpawnerInfo(EntityType.SHEEP,100000.0));
        buyItems.put(14, new SpawnerInfo(EntityType.PIG,100000.0));
        buyItems.put(15, new SpawnerInfo(EntityType.CHICKEN,100000.0));

        for(Map.Entry<Integer, SpawnerInfo> entry : buyItems.entrySet()){
            ItemStack itemStack = getSpawner(entry.getValue().type);
            InventoryUtil.setItemLore(itemStack, List.of(
                    Component.text(InventoryUtil.toCurrency(entry.getValue().price) + " Moedas", NamedTextColor.GOLD)
            ));
            inv.setItem(entry.getKey(), itemStack);
        }


    }
    public static ItemStack getSpawner(EntityType type){
        ItemStack item = new ItemStack(Material.SPAWNER);
        BlockStateMeta itemMeta = (BlockStateMeta) item.getItemMeta();
        itemMeta.displayName(Component.text("Spawner de ", NamedTextColor.DARK_PURPLE).append(
                Component.translatable(type.translationKey(), NamedTextColor.DARK_PURPLE)
        ));
        BlockState state = itemMeta.getBlockState();
        if(state instanceof CreatureSpawner spawnerState){
            spawnerState.setSpawnedType(type);
            itemMeta.setBlockState(spawnerState);
        }
        item.setItemMeta(itemMeta);
        return item;
    }
}
