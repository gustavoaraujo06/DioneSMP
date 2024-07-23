package com.dione.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class DatabaseManager {
    private MongoClient client;
    private MongoDatabase database;

    public DatabaseManager(String url, String database){
        this.client = MongoClients.create(url);
        this.database = this.client.getDatabase(database);
    }

    public MongoClient getClient() {
        return this.client;
    }

    public MongoDatabase getDatabase() {
        return this.database;
    }

    public MongoCollection<Document> getCollection(String collectionName){
        return this.database.getCollection(collectionName);
    }

    public void close(){
        if(this.client != null){
            this.client.close();
        }
    }
}
