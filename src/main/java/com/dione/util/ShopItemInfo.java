package com.dione.util;

import org.bukkit.Material;

public class ShopItemInfo {
    public Material material;
    public double price;

    public ShopItemInfo(Material material, double price){
        this.material = material;
        this.price = price;
    }
}
