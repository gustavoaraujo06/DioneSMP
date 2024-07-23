package com.dione.minion;

import com.dione.database.DatabaseManager;
import com.dione.logger.Logger;
import com.dione.main.Main;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Minion {
    public final static String collection = "minions";
    protected UUID minionId;
    protected ArmorStand stand;
    protected MinionType type;
    protected BukkitRunnable task;
    protected Inventory inventory;

    public Minion(Location location, MinionType type){
        //esse metodo é usado quando criando um minion do 0
        this.minionId = UUID.randomUUID();
        this.type = type;
        this.inventory = Bukkit.createInventory(null, 45);
        placeMinionInWorld(location);
        saveMinion();
        Logger.Log("1 Minion Adicionado ao Mundo e a Database!");
    }
    public Minion(ArmorStand stand, MinionType type, UUID minionId, Inventory inventory){
        this.stand = stand;
        this.type = type;
        this.minionId = minionId;
        this.inventory = inventory;
    }
    public void placeMinionInWorld(Location location){
        stand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        NamespacedKey key = new NamespacedKey(Main.getInstance(), "minionId");
        stand.getPersistentDataContainer().set(key, PersistentDataType.STRING, minionId.toString());
        stand.setSmall(true);

    }
    public void startTask(){

    }
    public void stopTask(){
        this.task.cancel();
    }
    public Inventory getInventory(){
        return this.inventory;
    }
    public void dropInventory(){
        for(ItemStack stack : inventory.getContents()){
            if(stack != null){
                stand.getWorld().dropItemNaturally(stand.getLocation(), stack);
            }
        }
        inventory = null;
    }
    public void saveMinion(){
        DatabaseManager db = Main.getDatabase();
        MongoCollection<Document> minionCollection = db.getCollection(collection);

        Document minionDocument = new Document("_id", this.minionId.toString());
        minionDocument.append("type", this.type.toString());
        minionDocument.append("items", getInventoryDocument());

        if(minionCollection.find(Filters.eq("_id", minionId.toString())).first() != null){
            minionCollection.findOneAndReplace(Filters.eq("_id", minionId.toString()), minionDocument);
        }else{
            minionCollection.insertOne(minionDocument);
        }

    }
    public List<Document> getInventoryDocument(){
        List<Document> items = new ArrayList<>();
        for(ItemStack itemStack : inventory.getContents()){
            if(itemStack == null){
                continue;
            }
            Document itemDocument = new Document("material", itemStack.getType().name());
            itemDocument.append("amount", itemStack.getAmount());
            items.add(itemDocument);
        }
        return items;
    }
    public UUID getMinionId(){
        return this.minionId;
    }
}
