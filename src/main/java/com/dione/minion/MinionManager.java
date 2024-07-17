package com.dione.minion;

import java.util.HashMap;
import java.util.UUID;

public class MinionManager {
    private static HashMap<UUID, Minion> minions = new HashMap<>();

    public static Minion getMinion(UUID minionId){
        if(!minions.containsKey(minionId)){
            return null;
        }
        return minions.get(minionId);
    }
    public static void addMinion(UUID minionId, Minion minion){
        minions.put(minionId, minion);
    }
    public static boolean removeMinion(UUID minionId){
        if(!minions.containsKey(minionId)){
            return false;
        }
        minions.remove(minionId);
        return true;
    }
}
