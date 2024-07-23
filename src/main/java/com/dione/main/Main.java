package com.dione.main;

import com.dione.database.DatabaseManager;
import com.dione.minion.MinionManager;
import com.dione.minion.commands.CommandGiveMinion;
import com.dione.minion.listeners.ListenerBreakMinion;
import com.dione.minion.listeners.ListenerInteractMinion;
import com.dione.minion.listeners.ListenerOnChunkLoadMinion;
import com.dione.minion.listeners.ListenerPlaceMinion;
import com.dione.scoreboard.listeners.ListenerScoreboard;
import com.dione.shop.commands.CommandShopBuilder;
import com.dione.shop.commands.CommandShopWitch;
import com.dione.shop.listeners.ListenerShop;
import com.dione.shop.listeners.ListenerShopBuy;
import com.dione.shop.listeners.ListenerShopSell;
import com.dione.shop.commands.CommandShopFarmer;
import com.dione.shop.commands.CommandShopMiner;
import com.dione.shopSmith.commands.CommandShopSmith;
import com.dione.enchant.listeners.ListenerEnchant;
import com.dione.shopSmith.listeners.ListenerShopSmith;
import com.dione.shopSpawner.commands.CommandShopSpawner;
import com.dione.shopSpawner.listeners.ListenerShopSpawner;
import com.dione.shopWeapon.commands.CommandShopWeapon;
import com.dione.shopWeapon.listeners.ListenerShopArmor;
import com.dione.shopWeapon.listeners.ListenerShopSword;
import com.dione.shopWeapon.listeners.ListenerShopWeapon;
import com.dione.tweaks.listeners.ListenerFortuneTweaks;
import com.dione.tweaks.listeners.ListenerTweaks;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin {
    public static Economy econ;
    private static DatabaseManager database;
    public static DatabaseManager getDatabase(){return database;}
    private static Main instance;
    public static Main getInstance(){
        return instance;
    }
    @Override
    public void onEnable() {
        getCommand("lojaminer").setExecutor(new CommandShopMiner());
        getCommand("lojafarmer").setExecutor(new CommandShopFarmer());
        getCommand("lojasmith").setExecutor(new CommandShopSmith());
        getCommand("lojaspawner").setExecutor(new CommandShopSpawner());
        getCommand("lojaweapon").setExecutor(new CommandShopWeapon());
        getCommand("lojawitch").setExecutor(new CommandShopWitch());
        getCommand("lojabuilder").setExecutor(new CommandShopBuilder());
        getCommand("giveminion").setExecutor(new CommandGiveMinion());
        Bukkit.getPluginManager().registerEvents(new ListenerShopSmith(), this);
        Bukkit.getPluginManager().registerEvents(new ListenerShop(), this);
        Bukkit.getPluginManager().registerEvents(new ListenerShopBuy(), this);
        Bukkit.getPluginManager().registerEvents(new ListenerShopSell(), this);
        Bukkit.getPluginManager().registerEvents(new ListenerEnchant(), this);
        Bukkit.getPluginManager().registerEvents(new ListenerScoreboard(), this);
        Bukkit.getPluginManager().registerEvents(new ListenerShopSpawner(), this);
        Bukkit.getPluginManager().registerEvents(new ListenerShopWeapon(), this);
        Bukkit.getPluginManager().registerEvents(new ListenerShopSword(), this);
        Bukkit.getPluginManager().registerEvents(new ListenerShopArmor(), this);
        Bukkit.getPluginManager().registerEvents(new ListenerTweaks(), this);
        Bukkit.getPluginManager().registerEvents(new ListenerFortuneTweaks(), this);
        Bukkit.getPluginManager().registerEvents(new ListenerPlaceMinion(), this);
        Bukkit.getPluginManager().registerEvents(new ListenerInteractMinion(), this);
        Bukkit.getPluginManager().registerEvents(new ListenerOnChunkLoadMinion(), this);
        Bukkit.getPluginManager().registerEvents(new ListenerBreakMinion(), this);
        if(!setupEconomy() || !setupDatabase()){
            getLogger().severe("Não foi possivel inicializar o plugin: Erro ao inicializar Economy");
            getServer().getPluginManager().disablePlugin(this);
        }
        instance = this;
    }

    public boolean setupEconomy(){
        if(getServer().getPluginManager().getPlugin("Vault") == null){
            return false;
        }
        econ = getServer().getServicesManager().getRegistration(Economy.class).getProvider();
        return true;
    }
    public boolean setupDatabase(){
        database = new DatabaseManager("mongodb://localhost:27017", "DioneSMP");
        return database.getClient() != null;
    }

    @Override
    public void onDisable() {
        MinionManager.saveMinions();
        super.onDisable();
    }
}
