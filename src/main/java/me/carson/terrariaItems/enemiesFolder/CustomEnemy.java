package me.carson.terrariaItems.enemiesFolder;

import org.bukkit.plugin.Plugin;

public abstract class CustomEnemy {

    protected final Plugin plugin;

    public CustomEnemy(Plugin plugin){
        this.plugin = plugin;
    }

}
