package me.carson.terrariaItems.projectilesFolder;

import me.carson.terrariaItems.projectilesFolder.projectiles.Leaf;
import me.carson.terrariaItems.weaponsFolder.weapons.*;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;


public class ProjectileManager implements Listener {

    public ProjectileManager(Plugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

}
