package ru.korshun.emenus.api.menu;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface MenuItemStack {
    String getName();
    Material getMaterial();
    void setMaterial(Material material);
    int getAmount();
    void setAmount(int amount);
    List<String> getCommands();
}
