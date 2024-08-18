package ru.korshun.emenus.menu;

import org.bukkit.Material;
import ru.korshun.emenus.api.menu.MenuItemStack;

import java.util.ArrayList;
import java.util.List;

public class EItemStack implements MenuItemStack {
    private String name;
    private Material material;
    private int amount;
    private List<String> commands = new ArrayList<>();

    public EItemStack(String name) {
        this.name = name;
    }

    public EItemStack(Material material) {
        this.material = material;
    }

    public EItemStack(Material material, int amount) {
        this.material = material;
        this.amount = amount;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Material getMaterial() {
        return material;
    }

    @Override
    public void setMaterial(Material material) {
        this.material = material;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public List<String> getCommands() {
        return commands;
    }
}
