package ru.korshun.emenus.menu;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import ru.korshun.emenus.api.menu.Menu;
import ru.korshun.emenus.api.menu.MenuItemStack;

import java.util.HashMap;

public class EMenu implements Menu {
    private String name;
    private int size;
    private String title;
    private String openCommand;
    private HashMap<MenuItemStack, Integer> items = new HashMap<>();

    public EMenu(String name) {
        this.name = name;
    }

    public EMenu(String name, int size, String title) {
        this.name = name;
        this.size = size;
        this.title = title;
    }

    public EMenu(String name, int size, String title, String openCommand) {
        this.name = name;
        this.size = size;
        this.title = title;
        this.openCommand = openCommand;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getOpenCommand() {
        return openCommand;
    }

    @Override
    public void setOpenCommand(String command) {
        this.openCommand = command;
    }

    @Override
    public void addItem(MenuItemStack menuItemStack) {
        int i = 0;
        while(items.get(menuItemStack) != null) {
            i++;
        }
        items.put(menuItemStack, i);
        this.getInventory().addItem(new ItemStack(menuItemStack.getMaterial()));
    }

    @Override
    public void setItem(int slot, MenuItemStack menuItemStack) {
        items.put(menuItemStack, slot);
        this.getInventory().setItem(slot, new ItemStack(menuItemStack.getMaterial()));
    }

    @Override
    public Inventory getInventory() {
        return Bukkit.createInventory(null, size, title);
    }
}
