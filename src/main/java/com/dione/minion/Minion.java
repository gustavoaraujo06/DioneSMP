package com.dione.minion;

import com.dione.main.Main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

import java.util.Locale;
import java.util.UUID;

public abstract class Minion {
    ArmorStand stand;
    MinionType type = MinionType.NONE;
    Inventory inv;

    public Minion(Location location){
        stand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        stand.getEquipment().setHelmet(getMinionHead());
        stand.getEquipment().setChestplate(getMinionBody());
        stand.getEquipment().setLeggings(getMinionLeg());
        stand.getEquipment().setBoots(getMinionFeet());
        stand.setSmall(true);
        stand.setBasePlate(false);
        UUID minionId = UUID.randomUUID();
        NamespacedKey key = new NamespacedKey(Main.getInstance(), "minionId");
        stand.getPersistentDataContainer().set(key, PersistentDataType.STRING, minionId.toString());
        MinionManager.addMinion(minionId, this);
        inv = Bukkit.createInventory(null, 45,
                Component.text("Inventario Minion", NamedTextColor.GOLD));
    }
    public MinionType getType(){
        return this.type;
    }
    public Inventory getInventory(){
        return this.inv;
    }
    ItemStack getMinionHead(){
        return new ItemStack(Material.PLAYER_HEAD);
    }
    ItemStack getMinionBody(){
        return new ItemStack(Material.LEATHER_CHESTPLATE);
    }
    ItemStack getMinionLeg(){
        return new ItemStack(Material.LEATHER_LEGGINGS);
    }
    ItemStack getMinionFeet(){
        return new ItemStack(Material.LEATHER_BOOTS);
    }

    public void startWorking(){
    }
    public void stopWorking(){
    }
    public void turnToBlock(Block block){
        Location blockLocation = block.getLocation();
        blockLocation.add(0.5, 0.5, 0.5);

        Vector direction = blockLocation.toVector().subtract(stand.getLocation().toVector()).normalize();

        double yaw = Math.atan2(-direction.getX(), direction.getZ());
        double pitch = Math.sin(-direction.getY());

        yaw = Math.toDegrees(yaw);
        pitch = Math.toDegrees(pitch);

        Location newLocation = stand.getLocation();

        newLocation.setYaw((float)yaw);
        newLocation.setPitch((float)pitch);

        stand.teleport(newLocation);
    }
    public static ItemStack getMinionItem(MinionType type){
        ItemStack minionItem = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta meta = minionItem.getItemMeta();
        NamespacedKey key = new NamespacedKey(Main.getInstance(), "minionType");
        switch (type){
            case POTATO:
                meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, 0);
                break;
        }
        minionItem.setItemMeta(meta);
        return minionItem;
    }

    public ArmorStand getStand() {
        return stand;
    }
}
