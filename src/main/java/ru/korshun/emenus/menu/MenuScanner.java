package ru.korshun.emenus.menu;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import ru.korshun.emenus.EMenus;
import ru.korshun.emenus.api.menu.Menu;
import ru.korshun.emenus.api.menu.MenuItemStack;
import ru.korshun.emenus.utils.MenuUtils;

import java.io.*;

public class MenuScanner {
    public static void setupMenusFiles() {
        for(Menu menu : EMenus.getInstance().getAllMenus()) {
            File dir = new File(EMenus.getInstance().getDataFolder().getAbsolutePath() + "/menus");
            if(!dir.exists()) {
                dir.mkdir();
            }
            File file = new File(EMenus.getInstance().getDataFolder().getAbsolutePath() + "/menus/" + EMenus.getInstance().getMenuFileName(menu));
            if(!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            saveResourceFile(EMenus.getInstance().getMenuFileName(menu), file);
            EMenus.getInstance().saveResource(file.getAbsolutePath(), false);
            YamlConfiguration.loadConfiguration(file);
        }
    }
    public static void saveResourceFile(String resource, File file) {
        try {
            InputStream is = EMenus.getInstance().getResource(resource);
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            OutputStream os = new FileOutputStream(file);
            os.write(buffer);
        } catch (IOException ignored) {}
    }
    public static void setupMenus() {
        for(Menu menu : EMenus.getInstance().getAllMenus()) {
            YamlConfiguration file = EMenus.getInstance().getMenuFile(menu);
            menu.setSize(file.getInt("size"));
            menu.setTitle(file.getString("title"));
            EMenuOpenCommand.register(EMenus.getInstance(), new CommandExecutor() {
                @Override
                public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
                    if(file.getString("permission") != null) {
                        if(!sender.hasPermission(file.getString("permission"))) {
                            return false;
                        }
                    }
                    MenuUtils.openMenuFor(menu, (Player) sender);
                    return true;
                }
            }, new String[] {file.getString("openCommand")}, "", "");
            ConfigurationSection section = file.getConfigurationSection("items");
            for(String i : section.getKeys(false)) {
                ConfigurationSection itemSection = section.getConfigurationSection(i);
                MenuItemStack is = new EItemStack(i);
                is.setMaterial(Material.valueOf(itemSection.getString("material")));
                try {
                    is.setAmount(itemSection.getInt("amount"));
                } catch (NullPointerException e) {
                    is.setAmount(1);
                }
                menu.setItem(itemSection.getInt("slot"), is);
            }
        }
    }
}
