package com.dione.main;

import com.dione.commands.CommandShopFarmer;
import com.dione.commands.CommandShopMiner;
import com.dione.commands.CommandShopSmith;
import com.dione.listeners.ListenerShopFarmer;
import com.dione.listeners.ListenerShopMiner;
import com.dione.listeners.ListenerShopSmith;
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
        Bukkit.getPluginManager().registerEvents(new ListenerShopMiner(), this);
        Bukkit.getPluginManager().registerEvents(new ListenerShopFarmer(), this);
        Bukkit.getPluginManager().registerEvents(new ListenerShopSmith(), this);
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
