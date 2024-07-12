package com.dione.gui;

import com.dione.util.InventoryUtil;
import com.dione.util.ShopItemInfo;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GUIShopFarmer {
    public static Inventory inv;
    public static Inventory buyInv;
    public static HashMap<Integer, ShopItemInfo> buyItems;
    public static Inventory sellInv;
    public static HashMap<Integer, ShopItemInfo> sellItems;
    static{
        setSellInv();
        setBuyInv();
        setInv();
    }

    public static void setSellInv() {
        sellInv = Bukkit.createInventory(null, 18,
                Component.text("Vender", NamedTextColor.AQUA, TextDecoration.BOLD));

        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closeMeta = close.getItemMeta();
        closeMeta.displayName(Component.text("Voltar", NamedTextColor.RED));
        close.setItemMeta(closeMeta);
        sellInv.setItem(0, close);

        sellItems = new HashMap<>();
        sellItems.put(1, new ShopItemInfo(Material.BROWN_MUSHROOM, 20.0));
        sellItems.put(2, new ShopItemInfo(Material.RED_MUSHROOM, 20.0));
        sellItems.put(3, new ShopItemInfo(Material.MELON, 205.0));
        sellItems.put(4, new ShopItemInfo(Material.PUMPKIN, 205.0));
        sellItems.put(5, new ShopItemInfo(Material.CACTUS, 15.0));
        sellItems.put(6, new ShopItemInfo(Material.BAMBOO, 13.0));
        sellItems.put(7, new ShopItemInfo(Material.SUGAR_CANE, 10.0));
        sellItems.put(8, new ShopItemInfo(Material.BEETROOT, 10.0));
        sellItems.put(9, new ShopItemInfo(Material.CARROT, 10.0));
        sellItems.put(10, new ShopItemInfo(Material.POTATO, 10.0));
        sellItems.put(11, new ShopItemInfo(Material.WHEAT, 10.0));

        for(Map.Entry<Integer, ShopItemInfo> entry : sellItems.entrySet()){
            ItemStack item = new ItemStack(entry.getValue().material);
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.lore(List.of(
                    Component.text(InventoryUtil.toCurrency(entry.getValue().price) + " Moedas", NamedTextColor.GOLD, TextDecoration.BOLD)
            ));
            item.setItemMeta(itemMeta);

            sellInv.setItem(entry.getKey(), item);
        }
    }

    public static void setBuyInv() {
        buyInv = Bukkit.createInventory(null, 18,
                Component.text("Comprar", NamedTextColor.GOLD, TextDecoration.BOLD));

        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closeMeta = close.getItemMeta();
        closeMeta.displayName(Component.text("Voltar", NamedTextColor.RED));
        close.setItemMeta(closeMeta);
        buyInv.setItem(0, close);

        buyItems = new HashMap<>();
        buyItems.put(1, new ShopItemInfo(Material.BROWN_MUSHROOM, 2048.0));
        buyItems.put(2, new ShopItemInfo(Material.RED_MUSHROOM, 2048.0));
        buyItems.put(3, new ShopItemInfo(Material.MELON_SEEDS, 2048.0));
        buyItems.put(4, new ShopItemInfo(Material.PUMPKIN_SEEDS, 2048.0));
        buyItems.put(5, new ShopItemInfo(Material.CACTUS, 1536.0));
        buyItems.put(6, new ShopItemInfo(Material.BAMBOO, 1280.0));
        buyItems.put(7, new ShopItemInfo(Material.SUGAR_CANE, 1024.0));
        buyItems.put(8, new ShopItemInfo(Material.BEETROOT_SEEDS, 1024.0));
        buyItems.put(9, new ShopItemInfo(Material.CARROT, 1024.0));
        buyItems.put(10, new ShopItemInfo(Material.POTATO, 1024.0));
        buyItems.put(11, new ShopItemInfo(Material.WHEAT_SEEDS, 1024.0));

        for(Map.Entry<Integer, ShopItemInfo> entry : buyItems.entrySet()){
            ItemStack item = new ItemStack(entry.getValue().material);
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.lore(List.of(
                    Component.text(InventoryUtil.toCurrency(entry.getValue().price) + " Moedas", NamedTextColor.GOLD, TextDecoration.BOLD)
            ));
            item.setItemMeta(itemMeta);

            buyInv.setItem(entry.getKey(), item);
        }

    }
    public static void setInv(){
        inv = Bukkit.createInventory(null, 45,
                Component.text("Fazendeiro").color(NamedTextColor.GOLD).decorate(TextDecoration.BOLD));

        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closeMeta = close.getItemMeta();

        closeMeta.displayName(Component.text("Fechar")
                .color(NamedTextColor.RED)
                .decorate(TextDecoration.BOLD));

        close.setItemMeta(closeMeta);

        inv.setItem(0, close);

        ItemStack frame = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);

        ItemMeta frameMeta = frame.getItemMeta();

        frameMeta.displayName(Component.text(""));
        frame.setItemMeta(frameMeta);

        for(int i : new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44}){
            inv.setItem(i, frame);
        }

        ItemStack buy = new ItemStack(Material.WHEAT_SEEDS);
        ItemMeta buyMeta = buy.getItemMeta();
        buyMeta.displayName(Component.text("Comprar").color(NamedTextColor.GOLD).decorate(TextDecoration.BOLD));

        buy.setItemMeta(buyMeta);

        ItemStack sell = new ItemStack(Material.WHEAT);
        ItemMeta sellMeta = sell.getItemMeta();
        sellMeta.displayName(Component.text("Vender").color(NamedTextColor.AQUA).decorate(TextDecoration.BOLD));

        sell.setItemMeta(sellMeta);

        inv.setItem(21, buy);
        inv.setItem(23, sell);
    }
}
