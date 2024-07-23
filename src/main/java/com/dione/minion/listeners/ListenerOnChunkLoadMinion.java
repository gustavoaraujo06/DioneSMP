package com.dione.minion.listeners;

import com.dione.logger.Logger;
import com.dione.minion.MinionManager;
import org.bukkit.Chunk;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

public class ListenerOnChunkLoadMinion implements Listener {
    @EventHandler
    public void OnChunkLoad(ChunkLoadEvent event){
        Chunk chunk = event.getChunk();
        MinionManager.loadMinions(chunk);
    }
}
