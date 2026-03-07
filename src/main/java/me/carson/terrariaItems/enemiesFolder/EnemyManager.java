package me.carson.terrariaItems.enemiesFolder;

import me.carson.terrariaItems.enemiesFolder.enemies.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class EnemyManager {

    public EnemyManager(Plugin plugin){
        Bukkit.getPluginManager().registerEvents(new PossessedArmorEnemy(plugin), plugin);
    }

}
