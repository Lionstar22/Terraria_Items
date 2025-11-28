package me.carson.terrariaItems.weaponsFolder.weapons;

import me.carson.terrariaItems.weaponsFolder.Weapon;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class MoltenFury extends Weapon {

    public MoltenFury(Plugin plugin) {
        super(plugin,"Molten Fury","#FFC896", Material.BOW,"molten_fury","MoltenFury",10, new ArrayList<>(List.of(ChatColor.GRAY+"Lights wooden arrows ablaze")));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new MoltenFury(plugin).createItem();
    }

    @Override
    public void leftActivate(Player player) {

    }

    @Override
    public void rightActivate(Player player) {
        if (player.getInventory().contains(Material.ARROW)) {
            player.getInventory().removeItem(new ItemStack(Material.ARROW, 1));
            Location loc = player.getEyeLocation();
            Vector dir = loc.getDirection().normalize();
            loc.add(dir.multiply(1));
            World world= player.getWorld();
            Arrow arrow=world.spawnArrow(loc,dir, 5, 0.5F);
            arrow.setDamage(5);
            arrow.setFireTicks(100);
            arrow.setShooter(player);
        }
    }
}
