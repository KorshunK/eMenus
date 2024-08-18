package ru.korshun.emenus.api.menu;

import org.bukkit.inventory.Inventory;

public interface Menu {
    String getName();
    int getSize();
    void setSize(int size);
    String getTitle();
    void setTitle(String title);
    String getOpenCommand();
    void setOpenCommand(String command);
    void addItem(MenuItemStack menuItemStack);
    void setItem(int slot, MenuItemStack menuItemStack);
    Inventory getInventory();
}
