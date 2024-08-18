package ru.korshun.emenus.menu;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.plugin.Plugin;
import ru.korshun.emenus.EMenus;

import java.lang.reflect.Field;
import java.util.Arrays;

public class EMenuOpenCommand extends Command implements PluginIdentifiableCommand {
    protected Plugin ePlugin;
    protected final CommandExecutor owner;
    protected final Object registeredWith;
    public EMenuOpenCommand(String[] aliases, String description, String usage, CommandExecutor owner, Object reqistredWith, Plugin plugin) {
        super(aliases[0], description, usage, Arrays.asList(aliases));
        this.owner = owner;
        this.ePlugin = plugin;
        this.registeredWith = reqistredWith;
    }

    @Override
    public Plugin getPlugin() {
        return this.ePlugin;
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if(!this.testPermission(sender)) return true;
        if(this.owner.onCommand(sender, this, label, args)) return true;
        else {
            sender.sendMessage(this.usageMessage);
            return false;
        }
    }

    public static void register(Plugin plugin, CommandExecutor commandExecutor, String[] aliases, String description, String usage) {
        try {
            EMenuOpenCommand command = new EMenuOpenCommand(aliases, description, usage, commandExecutor, new Object(), plugin);
            Field field = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            field.setAccessible(true);
            CommandMap map = (CommandMap) field.get(Bukkit.getServer());
            map.register(plugin.getDescription().getName(), command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
