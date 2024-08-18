package ru.korshun.emenus.config;

import org.bukkit.configuration.file.FileConfiguration;

public class MenuConfigUtils {
    public static String getKeyByValue(FileConfiguration config, String value) {
        String key = null;
        for(String fKey : config.getKeys(true)) {
            Object fValue = config.get(fKey);
            if(value != null && value.toString().equals(fValue)) {
                key = fKey;
                break;
            }
        }
        return key;
    }
}
