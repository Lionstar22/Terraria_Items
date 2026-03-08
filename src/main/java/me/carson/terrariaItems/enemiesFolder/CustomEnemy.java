package me.carson.terrariaItems.enemiesFolder;

import org.bukkit.plugin.Plugin;

public abstract class CustomEnemy {

    protected final Plugin plugin;
    protected final String name;
    protected final String id;

    public CustomEnemy(Plugin plugin, String name, String id){
        this.plugin = plugin;
        this.name = name;
        this.id = id;
    }

}
