package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Particle;
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

public class TsunamiInABottle extends Accessory{

    public TsunamiInABottle(Plugin plugin){
        super(plugin,"tsunami_in_a_bottle.name","#9696FF", Material.FIREWORK_STAR,"tsunami_in_a_bottle","TsunamiInABottle","tsunami_in_a_bottle.lore");
    }

    @Override
    public void activateEffect(Player player) {
    }

    @Override
    public void deactivateEffect(Player player) {
    }

    public static ItemStack getItem(Plugin plugin) {
        return new TsunamiInABottle(plugin).createItem();
    }

}
