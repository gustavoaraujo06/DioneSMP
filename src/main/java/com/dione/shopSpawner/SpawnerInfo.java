package com.dione.shopSpawner;

import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public class SpawnerInfo {
    public double price;
    public EntityType type;
    public SpawnerInfo(EntityType type, double price){
        this.type = type;
        this.price = price;
    }
}
