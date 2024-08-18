package ru.korshun.emenus.utils;

import org.bukkit.entity.Player;
import ru.korshun.emenus.api.menu.Menu;

public class MenuUtils {
    public static void openMenuFor(Menu menu, Player player) {
        player.openInventory(menu.getInventory());
    }
}
