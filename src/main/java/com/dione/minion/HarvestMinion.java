package com.dione.minion;

import com.dione.main.Main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class HarvestMinion extends Minion{
    protected int actionTime = 2;  //seconds
    protected Material seed;
    protected Material drop;
    protected  int radius = 1;
    public HarvestMinion(Location location, Material seed, Material drop) {
        super(location, MinionType.HARVEST);
        this.seed = seed;
        this.drop = drop;
        setupTask();
    }
    public HarvestMinion(ArmorStand stand, UUID minionId, Inventory inventory, Material seed, Material drop){
        super(stand, MinionType.HARVEST, minionId, inventory);
        this.seed = seed;
        this.drop = drop;
        setupTask();
    }
    public void setupTask(){
        this.task = new BukkitRunnable() {
            @Override
            public void run() {
                //harvest minion logic
                outer:
                for(int x = -radius; x <= radius; x++){
                    for(int z = -radius; z <= radius; z++){
                        if(z == 0 && x == 0){
                            continue;
                        }
                        Location location = stand.getLocation();
                        location.add(x, -1, z);
                        Block block = stand.getWorld().getBlockAt(location);
                        Block aboveBlock = stand.getWorld().getBlockAt(location.add(0, 1, 0));
                        if(block.getType() != Material.FARMLAND){
                            block.setType(Material.FARMLAND);
                            break outer;
                        }
                        if(aboveBlock.getType() == seed){
                            Ageable ageable = (Ageable)aboveBlock.getBlockData();
                            if(ageable.getAge() == ageable.getMaximumAge()){
                                aboveBlock.setType(Material.AIR);
                                stand.getWorld().playSound(aboveBlock.getLocation().add(0.5, 0, 0.5), Sound.BLOCK_CROP_BREAK, 1.f, 1.f);
                                stand.getWorld().spawnParticle(Particle.COMPOSTER, aboveBlock.getLocation().add(0.5, 0, 0.5), 30);
                                inventory.addItem(new ItemStack(drop, 4));
                            }
                        }
                        if(aboveBlock.getType() != seed){
                            aboveBlock.setType(seed);
                            break outer;
                        }
                    }
                }
            }
        };
    }

    @Override
    public void startTask() {
        this.task.runTaskTimer(Main.getInstance(), 20L * 3, this.actionTime * 20L);
    }

    public static ItemStack newMinionItemStack() {
        ItemStack minionItem = new ItemStack(Material.POTATO);

        ItemMeta itemMeta = minionItem.getItemMeta();

        itemMeta.displayName(Component.text("Minion de Batata", NamedTextColor.DARK_PURPLE, TextDecoration.BOLD));

        NamespacedKey key = new NamespacedKey(Main.getInstance(), "minionType");
        itemMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, MinionType.HARVEST.name());

        minionItem.setItemMeta(itemMeta);
        return minionItem;
    }
}
