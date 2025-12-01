package me.carson.terrariaItems.materialsFolder;


import me.carson.terrariaItems.materialsFolder.materials.*;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class MaterialManager implements Listener {

    public MaterialManager(Plugin plugin) {
        Bukkit.getPluginManager().registerEvents(new SoulOfMight(plugin), plugin);
        Bukkit.getPluginManager().registerEvents(new SoulOfSight(plugin), plugin);
        Bukkit.getPluginManager().registerEvents(new SoulOfFright(plugin), plugin);
    }

}
