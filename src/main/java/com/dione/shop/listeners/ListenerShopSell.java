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

public class ListenerShopSell implements Listener {
    @EventHandler
    public void OnInventoryClick(InventoryClickEvent event){
        if(event.getInventory().getHolder() instanceof GUIShop menu){
            if(event.getInventory().equals(menu.sellInv)){
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
                    double price = menu.sellItems.get(itemMaterial);

                    if(event.isShiftClick()){
                        int amount = 0;
                        for(ItemStack stack : player.getInventory().getContents()){
                            if(stack != null){
                                if(stack.getType() == itemMaterial){
                                    amount += stack.getAmount();
                                }
                            }
                        }
                        ItemStack item = new ItemStack(itemMaterial, amount);
                        player.getInventory().removeItem(item);

                        Main.econ.depositPlayer(player, price * amount);

                        player.sendMessage(Component.text(
                                "Você vendeu " + amount + " ", NamedTextColor.GOLD
                        ).append(Component.translatable(itemMaterial.translationKey(), NamedTextColor.AQUA)
                                .append(Component.text(" por " + InventoryUtil.toCurrency(price * amount), NamedTextColor.GOLD))));

                        Logger.Log(player.getName() +
                                " vendeu " + amount + " " +
                                Logger.componentToString(Component.translatable(itemMaterial.translationKey())) +
                                " por " + InventoryUtil.toCurrency(price * amount));
                        return;
                    }
                    if(player.getInventory().contains(itemMaterial)){
                        player.getInventory().removeItem(new ItemStack(itemMaterial));

                        Main.econ.depositPlayer(player, price);

                        player.sendMessage(Component.text(
                                "Você vendeu 1 ", NamedTextColor.GOLD
                        ).append(Component.translatable(itemMaterial.translationKey(), NamedTextColor.AQUA)
                                .append(Component.text(" por " + InventoryUtil.toCurrency(price), NamedTextColor.GOLD))));

                        Logger.Log(player.getName() +
                                " vendeu 1 " +
                                Logger.componentToString(Component.translatable(itemMaterial.translationKey())) +
                                " por " + InventoryUtil.toCurrency(price));

                    }


                }
            }
        }
    }
}
