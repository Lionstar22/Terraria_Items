package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInputEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class BlizzardInABottle extends Accessory{



    public BlizzardInABottle(Plugin plugin){
        super(plugin,"Blizzard in a Bottle","#9696FF", Material.FIREWORK_STAR,"blizzard_in_a_bottle","BlizzardInABottle",new ArrayList<>(List.of(ChatColor.GRAY+"Allows the holder to double jump", ChatColor.GRAY+"Must be in accessory inventory")));
    }

    @Override
    public void activateEffect(Player player) {
    }

    @Override
    public void deactivateEffect(Player player) {
    }

    public static ItemStack getItem(Plugin plugin) {
        return new BlizzardInABottle(plugin).createItem();
    }

}
