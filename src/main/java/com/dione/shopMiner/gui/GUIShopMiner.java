package com.dione.shopMiner.gui;

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

import java.util.*;

public class GUIShopMiner {
    public static Inventory inv;
    public static Inventory buyInv;
    public static HashMap<Integer, ShopItemInfo> buyItems;
    public static Inventory sellInv;
    public static HashMap<Integer, ShopItemInfo> sellItems;

    static{
        setupInv();
        setupBuyInv();
        setupSellInv();
    }

    private static void setupSellInv(){
        //um dia eu faço config
        double sellValuePercent = 0.75;
        sellInv = Bukkit.createInventory(null, 9, Component.text("Vender Minerios")
                .color(NamedTextColor.AQUA).decorate(TextDecoration.BOLD));
        sellItems = new HashMap<>();
        for(Map.Entry<Integer, ShopItemInfo> entry : buyItems.entrySet()){
            ShopItemInfo itemInfo = entry.getValue();
            sellItems.put(entry.getKey(), new ShopItemInfo(itemInfo.material, itemInfo.price * sellValuePercent));
        }

        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closeMeta = close.getItemMeta();
        closeMeta.displayName(Component.text("Voltar").color(NamedTextColor.RED));
        close.setItemMeta(closeMeta);

        sellInv.setItem(0, close);

        for(Map.Entry<Integer, ShopItemInfo> entry : sellItems.entrySet()){
            ItemStack item = new ItemStack(entry.getValue().material);
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.lore(List.of(
                    Component.text(InventoryUtil.toCurrency(entry.getValue().price) + " Moedas").color(NamedTextColor.GOLD)
            ));
            item.setItemMeta(itemMeta);
            sellInv.setItem(entry.getKey(), item);
        }
    }
    private static void setupBuyInv(){
        buyInv = Bukkit.createInventory(null, 9, Component.text("Comprar Minerios")
                .color(NamedTextColor.GOLD).decorate(TextDecoration.BOLD));
        //items hardcoded pq sim <3
        //algum dia eu faço configuraçao com json
        buyItems = new HashMap<>();
        buyItems.put(1, new ShopItemInfo(Material.DIAMOND, 2048.0));
        buyItems.put(2, new ShopItemInfo(Material.EMERALD, 4096.0));
        buyItems.put(3, new ShopItemInfo(Material.IRON_INGOT, 1024.0));
        buyItems.put(4, new ShopItemInfo(Material.GOLD_INGOT, 1024.0));
        buyItems.put(5, new ShopItemInfo(Material.COAL, 128.0));
        buyItems.put(6, new ShopItemInfo(Material.REDSTONE, 512.0));
        buyItems.put(7, new ShopItemInfo(Material.LAPIS_LAZULI, 512.0));

        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closeMeta = close.getItemMeta();
        closeMeta.displayName(Component.text("Voltar").color(NamedTextColor.RED));
        close.setItemMeta(closeMeta);

        buyInv.setItem(0, close);

        for(Map.Entry<Integer, ShopItemInfo> entry : buyItems.entrySet()){
            ItemStack item = new ItemStack(entry.getValue().material);
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.lore(List.of(
                    Component.text(InventoryUtil.toCurrency(entry.getValue().price) + " Moedas").color(NamedTextColor.GOLD)
            ));
            item.setItemMeta(itemMeta);
            buyInv.setItem(entry.getKey(), item);
        }



    }
    private static void setupInv(){
        inv = Bukkit.createInventory(null, 45,
                Component.text("Mineiro").color(NamedTextColor.GOLD).decorate(TextDecoration.BOLD));

        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closeMeta = close.getItemMeta();

        closeMeta.displayName(Component.text("Fechar")
                .color(NamedTextColor.RED)
                .decorate(TextDecoration.BOLD));

        close.setItemMeta(closeMeta);

        inv.setItem(0, close);

        ItemStack frame = new ItemStack(Material.CYAN_STAINED_GLASS_PANE);

        ItemMeta frameMeta = frame.getItemMeta();

        frameMeta.displayName(Component.text(""));
        frame.setItemMeta(frameMeta);

        for(int i : new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44}){
            inv.setItem(i, frame);
        }

        ItemStack buy = new ItemStack(Material.DIAMOND);
        ItemMeta buyMeta = buy.getItemMeta();
        buyMeta.displayName(Component.text("Comprar").color(NamedTextColor.GOLD).decorate(TextDecoration.BOLD));

        buy.setItemMeta(buyMeta);

        ItemStack sell = new ItemStack(Material.NAME_TAG);
        ItemMeta sellMeta = sell.getItemMeta();
        sellMeta.displayName(Component.text("Vender").color(NamedTextColor.AQUA).decorate(TextDecoration.BOLD));

        sell.setItemMeta(sellMeta);

        inv.setItem(21, buy);
        inv.setItem(23, sell);
    }
}
