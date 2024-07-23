package com.dione.minion;

import com.dione.database.DatabaseManager;
import com.dione.logger.Logger;
import com.dione.main.Main;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MinionManager {
    private static Map<UUID, Minion> minions = new HashMap<>();

    public static Minion getMinion(UUID minionId){
        return minions.getOrDefault(minionId, null);
    }
    public static void addMinion(UUID minionId, Minion minion){
        minions.put(minionId, minion);
    }
    public static void removeMinion(UUID minionId){
        if(minions.containsKey(minionId)){
            minions.remove(minionId);
        }
    }
    public static void loadMinions(Chunk chunk){
        DatabaseManager db = Main.getDatabase();
        MongoCollection<Document> minionCollection = db.getCollection(Minion.collection);
        for(Entity entity : chunk.getEntities()){
            if (entity instanceof ArmorStand stand) {
                NamespacedKey key = new NamespacedKey(Main.getInstance(), "minionId");
                if (stand.getPersistentDataContainer().has(key)) {
                    String minionId = stand.getPersistentDataContainer().get(key, PersistentDataType.STRING);
                    if(MinionManager.getMinion(UUID.fromString(minionId)) != null){
                        continue;
                    }
                    Document minionDocument = minionCollection.find(Filters.eq("_id", minionId)).first();
                    Logger.Log("Minion " + minionId + " carregado!");
                    if(minionDocument != null){
                        //load inventory
                        Inventory minionInventory = Bukkit.createInventory(null, 45);
                        for(Document itemInfo : minionDocument.getList("items", Document.class)){
                            Material item = Material.valueOf(itemInfo.getString("material"));
                            int amount = itemInfo.getInteger("amount");
                            ItemStack itemStack = new ItemStack(item, amount);
                            minionInventory.addItem(itemStack);
                        }
                        Minion minion =  new HarvestMinion(stand, UUID.fromString(minionId), minionInventory, Material.POTATOES, Material.POTATO);
                        addMinion(UUID.fromString(minionId), minion);
                        minion.startTask();
                    }
                }
            }
        }
    }
    public static void saveMinions(){
        DatabaseManager db = Main.getDatabase();
        MongoCollection<Document> minionsCollection = db.getCollection(Minion.collection);
        minionsCollection.drop();
        for(Map.Entry<UUID, Minion> minion : minions.entrySet()){
            minion.getValue().saveMinion();
        }
    }
}
