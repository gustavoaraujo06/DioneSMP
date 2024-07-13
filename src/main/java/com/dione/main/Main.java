package com.dione.main;

import com.dione.scoreboard.listeners.ListenerScoreboard;
import com.dione.shopFarmer.commands.CommandShopFarmer;
import com.dione.shopMiner.commands.CommandShopMiner;
import com.dione.shopSmith.commands.CommandShopSmith;
import com.dione.shopFarmer.listeners.ListenerShopFarmer;
import com.dione.shopMiner.listeners.ListenerShopMiner;
import com.dione.shopSmith.listeners.ListenerShopEnchant;
import com.dione.shopSmith.listeners.ListenerShopSmith;
import com.dione.shopSpawner.commands.CommandShopSpawner;
import com.dione.shopSpawner.listeners.ListenerShopSpawner;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin {
    public static Economy econ;
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
        Bukkit.getPluginManager().registerEvents(new ListenerShopMiner(), this);
        Bukkit.getPluginManager().registerEvents(new ListenerShopFarmer(), this);
        Bukkit.getPluginManager().registerEvents(new ListenerShopSmith(), this);
        Bukkit.getPluginManager().registerEvents(new ListenerShopEnchant(), this);
        Bukkit.getPluginManager().registerEvents(new ListenerScoreboard(), this);
        Bukkit.getPluginManager().registerEvents(new ListenerShopSpawner(), this);
        if(!setupEconomy()){
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
}
