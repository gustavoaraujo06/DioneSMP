package com.dione.shop.gui;

import com.dione.util.InventoryUtil;
import it.unimi.dsi.fastutil.Hash;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class GUIShop implements InventoryHolder {
    public Inventory inv;
    public Inventory buyInv;
    public Inventory sellInv;
    public HashMap<Material, Double> buyItems;
    public HashMap<Material, Double> sellItems;
    public GUIShop(HashMap<Material, Double> buyItems, HashMap<Material, Double> sellItems, Component title, Material frame, Material buyIcon, Material sellIcon){
        this.buyItems = buyItems;
        this.sellItems = sellItems;
        setupSellInventory();
        setupBuyInventory();
        setupInventory(title, frame, buyIcon, sellIcon);
    }
    public GUIShop(HashMap<Material, Double> buyItems, double sellPercent, Component title, Material frame, Material buyIcon, Material sellIcon){
        this.buyItems = buyItems;
        this.sellItems = new HashMap<>();
        for(Map.Entry<Material, Double> entry : buyItems.entrySet()){
            this.sellItems.put(entry.getKey(), entry.getValue() * sellPercent);
        }
        setupSellInventory();
        setupBuyInventory();
        setupInventory(title, frame, buyIcon, sellIcon);
    }
    public void setupInventory(Component title, Material frame, Material buyIcon, Material sellIcon){
        inv = InventoryUtil.createMenu(this, title, 45, Component.text("Fechar", NamedTextColor.RED));
        InventoryUtil.createInventoryFrame(inv, frame);

        ItemStack buy = new ItemStack(buyIcon);
        InventoryUtil.setItemName(buy, Component.text("Comprar", NamedTextColor.GOLD, TextDecoration.BOLD));

        ItemStack sell = new ItemStack(sellIcon);
        InventoryUtil.setItemName(sell, Component.text("Vender", NamedTextColor.AQUA, TextDecoration.BOLD));

        inv.setItem(21, buy);
        inv.setItem(23, sell);
    }

    public void setupSellInventory(){
        int size = Math.ceilDiv((sellItems.size() + 1), 9) * 9;
        sellInv = InventoryUtil.createMenu(
                this,
                Component.text("Vender", NamedTextColor.AQUA, TextDecoration.BOLD),
                size,
                Component.text("Fechar", NamedTextColor.RED)
        );
        int slotCounter = 1;
        for(Map.Entry<Material, Double> entry : sellItems.entrySet()){
            ItemStack item = new ItemStack(entry.getKey());
            InventoryUtil.setItemLore(item,
                    List.of(
                            Component.text(InventoryUtil.toCurrency(entry.getValue()) + " Moedas", NamedTextColor.GOLD)
                    ));
            sellInv.setItem(slotCounter, item);
            slotCounter++;
        }
    }
    public void setupBuyInventory(){
        int size = Math.ceilDiv((sellItems.size() + 1), 9) * 9;
        buyInv = InventoryUtil.createMenu(
                this,
                Component.text("Comprar", NamedTextColor.GOLD, TextDecoration.BOLD),
                size,
                Component.text("Fechar", NamedTextColor.RED)
        );
        int slotCounter = 1;
        for(Map.Entry<Material, Double> entry : buyItems.entrySet()){
            ItemStack item = new ItemStack(entry.getKey());
            InventoryUtil.setItemLore(item,
                    List.of(
                            Component.text(InventoryUtil.toCurrency(entry.getValue()) + " Moedas", NamedTextColor.GOLD)
                    ));
            buyInv.setItem(slotCounter, item);
            slotCounter++;
        }
    }
    @Override
    public @NotNull Inventory getInventory() {
        return null;
    }
}
