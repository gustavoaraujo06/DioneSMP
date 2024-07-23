package com.dione.minion;

public enum MinionType {
    HARVEST,
    BLOCK,
    MOB;
    public static MinionType getType(String value){
        for(MinionType type : MinionType.values()){
            if(type.name().equalsIgnoreCase(value)){
                return type;
            }
        }
        return HARVEST;
    }
}
