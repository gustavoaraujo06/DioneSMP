package com.dione.shop.listeners;

import com.dione.logger.Logger;
import com.dione.main.Main;
import com.dione.shop.gui.GUIShop;
import com.dione.util.InventoryUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ListenerShopBuy implements Listener {
    @EventHandler
    public void OnInventoryClick(InventoryClickEvent event){
        if(event.getInventory().getHolder() instanceof GUIShop menu){
            if(event.getInventory().equals(menu.buyInv)){
                event.setCancelled(true);
                Player player = (Player) event.getWhoClicked();
                if(event.getClickedInventory().equals(player.getInventory())){
                    return;
                }
                if(event.getRawSlot() == 0){
                    player.openInventory(menu.inv);
                    return;
                }
                if(event.getCurrentItem() != null){
                    Material itemMaterial = event.getCurrentItem().getType();
                    if(event.isShiftClick()){
                        double price = menu.buyItems.get(itemMaterial) * 64;
                        if(Main.econ.has(player, price)){
                            Main.econ.withdrawPlayer(player, price);

                            ItemStack item = new ItemStack(itemMaterial, 64);

                            player.getInventory().addItem(item);

                            player.sendMessage(Component.text(
                                    "Você comprou 64 ", NamedTextColor.GOLD
                            ).append(Component.translatable(itemMaterial.translationKey(), NamedTextColor.AQUA)
                                    .append(Component.text(" por " + InventoryUtil.toCurrency(price), NamedTextColor.GOLD))));

                            Logger.Log(player.getName() +
                                    " comprou 64 " +
                                    Logger.componentToString(Component.translatable(itemMaterial.translationKey())) +
                                    " por " + InventoryUtil.toCurrency(price));
                            return;
                        }
                    }
                    double price = menu.buyItems.get(itemMaterial);
                    if(Main.econ.has(player, price)){
                        Main.econ.withdrawPlayer(player, price);

                        ItemStack item = new ItemStack(itemMaterial);

                        player.getInventory().addItem(item);

                        player.sendMessage(Component.text(
                                "Você comprou 1 ", NamedTextColor.GOLD
                        ).append(Component.translatable(itemMaterial.translationKey(), NamedTextColor.AQUA)
                                .append(Component.text(" por " + InventoryUtil.toCurrency(price), NamedTextColor.GOLD))));

                        Logger.Log(player.getName() +
                                " comprou 1 " +
                                Logger.componentToString(Component.translatable(itemMaterial.translationKey())) +
                                " por " + InventoryUtil.toCurrency(price));
                    }
                }
            }
        }
    }
}
