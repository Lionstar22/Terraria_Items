package me.carson.terrariaItems.blocksFolder;

import me.carson.terrariaItems.blocksFolder.blocks.Hellforge;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class CustomBlockManager implements Listener {

    private final List<CustomBlock> blockItems = new ArrayList<>();

    public CustomBlockManager(Plugin plugin) {
        blockItems.add(new Hellforge(plugin));

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
}
