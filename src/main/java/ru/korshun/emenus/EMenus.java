package ru.korshun.emenus;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import ru.korshun.emenus.api.menu.Menu;
import ru.korshun.emenus.menu.EMenu;
import ru.korshun.emenus.menu.MenuScanner;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public final class EMenus extends JavaPlugin {
    private static EMenus instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        MenuScanner.setupMenusFiles();
        MenuScanner.setupMenus();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static EMenus getInstance() {
        return instance;
    }
    public List<Menu> getAllMenus() {
        List<Menu> list = new ArrayList<>();
        ConfigurationSection section = this.getConfig().getConfigurationSection("menus");
        for(String s : section.getKeys(true)) {
            list.add(new EMenu(s));
        }
        return list;
    }

    public void loadMenuFile(Menu menu) {
        saveResource("/menus/" + EMenus.getInstance().getConfig().getString("menus." + menu.getName()), false);
        File file = new File(getDataFolder().getAbsolutePath() + "/menus/" + this.getConfig().getString("menus." + menu.getName()));
        YamlConfiguration.loadConfiguration(file);
    }

    public String getMenuFileName(Menu menu) {
        return this.getConfig().getString("menus." + menu.getName());
    }

    public static YamlConfiguration getMenuFile(Menu menu) {
        File file = new File(EMenus.getInstance().getDataFolder().getAbsolutePath() + "/" + EMenus.getInstance().getConfig().getString("menus." + menu.getName()));
        return YamlConfiguration.loadConfiguration(file);
    }
}
