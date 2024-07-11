package com.dione.main;

import com.dione.commands.CommandShop;
import com.dione.listeners.ListenerShop;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

public class Main extends JavaPlugin {
    public static Economy econ;
    @Override
    public void onEnable() {
        getCommand("loja").setExecutor(new CommandShop());
        Bukkit.getPluginManager().registerEvents(new ListenerShop(), this);
        if(!setupEconomy()){
            getLogger().severe("Não foi possivel inicializar o plugin: Erro ao inicializar Economy");
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    public boolean setupEconomy(){
        if(getServer().getPluginManager().getPlugin("Vault") == null){
            return false;
        }
        econ = getServer().getServicesManager().getRegistration(Economy.class).getProvider();
        return true;
    }
}
