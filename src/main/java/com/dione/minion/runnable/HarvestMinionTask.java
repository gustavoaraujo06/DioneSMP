package com.dione.minion.runnable;

import com.dione.minion.Minion;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class HarvestMinionTask extends BukkitRunnable {
    int radius = 0;
    int x = -radius;
    int z = -radius;
    Minion minion;
    Material harvestType;
    public HarvestMinionTask(int radius, Minion minion, Material harvestType){
        this.radius = radius;
        this.minion = minion;
        this.harvestType = harvestType;
    }
    @Override
    public void run() {
        outerLoop:
        for(int x = -radius; x <= radius; x++){
            for(int z = -radius; z <= radius; z++){
                if(z != 0 || x != 0){
                    Location location = minion.getStand().getLocation();
                    Block block = location.getWorld().getBlockAt(location.getBlockX() + x, location.getBlockY(), location.blockZ() + z);
                    Block aboveBlock = block.getRelative(0, 1, 0);
                    if(block.getType() != Material.FARMLAND){
                        block.setType(Material.FARMLAND);
                        minion.turnToBlock(block);
                        break outerLoop;
                    }
                    if(aboveBlock.getType() == harvestType){
                        Ageable ageable = (Ageable) aboveBlock.getBlockData();
                        if(ageable.getAge() == ageable.getMaximumAge()){
                            minion.turnToBlock(aboveBlock);

                            aboveBlock.setType(Material.AIR);
                            ItemStack item;
                            switch (minion.getType()){
                                case POTATO:
                                    item = new ItemStack(Material.POTATO);
                                    item.setAmount(3);
                                    break;
                                default:
                                    item = new ItemStack(Material.DIRT);
                            }
                            minion.getInventory().addItem(item);
                        }
                    }
                    if(aboveBlock.getType() == Material.AIR && block.getType() == Material.FARMLAND){
                        minion.turnToBlock(aboveBlock);
                        aboveBlock.setType(harvestType);
                        break outerLoop;
                    }
                }
            }
        }
    }
}
