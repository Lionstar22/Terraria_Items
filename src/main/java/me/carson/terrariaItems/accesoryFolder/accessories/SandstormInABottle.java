package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.*;
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

public class SandstormInABottle extends Accessory{

    public SandstormInABottle(Plugin plugin){
        super(plugin,"sandstorm_in_a_bottle.name","#96FF96", Material.FIREWORK_STAR,"sandstorm_in_a_bottle","SandstormInABottle","sandstorm_in_a_bottle.lore");
    }

    @Override
    public void activateEffect(Player player) {
    }

    @Override
    public void deactivateEffect(Player player) {
    }

    public static ItemStack getItem(Plugin plugin) {
        return new SandstormInABottle(plugin).createItem();
    }

}
