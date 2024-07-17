package com.dione.minion;

import com.dione.main.Main;
import com.dione.minion.runnable.HarvestMinionTask;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class PotatoMinion extends Minion{
    BukkitRunnable task;
    private int workDelaySeconds = 2;
    public PotatoMinion(Location location) {
        super(location);
        stand.getEquipment().setItemInMainHand(new ItemStack(Material.DIAMOND_HOE));
        this.type = MinionType.POTATO;
        task = new HarvestMinionTask(1, this, Material.POTATOES);
        startWorking();
    }

    @Override
    ItemStack getMinionBody() {
        return new ItemStack(Material.DIAMOND_CHESTPLATE);
    }

    @Override
    public void startWorking(){
        task.runTaskTimer(
                Main.getInstance(),
                0L,
                20L * workDelaySeconds
        );
    }
    @Override
    public void stopWorking(){
        task.cancel();
    }
}
