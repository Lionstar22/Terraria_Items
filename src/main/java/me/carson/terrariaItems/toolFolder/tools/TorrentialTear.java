package me.carson.terrariaItems.toolFolder.tools;

import me.carson.terrariaItems.toolFolder.Tool;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class TorrentialTear extends Tool {

    public TorrentialTear(Plugin plugin){
        super(plugin,"Torrential Tear","#FF96FF", Material.NAUTILUS_SHELL,"torrential_tear","TorrentialTear",100,new ArrayList<>(List.of(ChatColor.GRAY+"Toggles Rain")));
    }

    @Override
    public void rightActivate(Player player) {
        World world =player.getWorld();
        boolean raining = world.hasStorm();
        world.setStorm(!raining);
        player.getWorld().playSound(player.getLocation(), "terraria:wet_sound", 0.75f, 1f);
    }

    @Override
    public void cooldownEffect(Player player) {

    }

    public static ItemStack getItem(Plugin plugin) {
        return new TorrentialTear(plugin).createItem();
    }
}
