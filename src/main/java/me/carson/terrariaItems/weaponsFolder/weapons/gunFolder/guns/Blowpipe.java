package me.carson.terrariaItems.weaponsFolder.weapons.gunFolder.guns;

import me.carson.terrariaItems.projectilesFolder.projectiles.Seed;
import me.carson.terrariaItems.weaponsFolder.Weapon;
import me.carson.terrariaItems.weaponsFolder.weapons.gunFolder.Gun;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class Blowpipe extends Gun {

    public Blowpipe(Plugin plugin) {
        super(plugin,"Blowpipe","#FFFFFF", Material.STICK,"blowpipe","Blowpipe",20,3,1,0,100, new ArrayList<>(List.of(ChatColor.GRAY+"Uses seeds for Ammo",ChatColor.GRAY+"1 damage")));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new Blowpipe(plugin).createItem();
    }

    @Override
    public void leftActivate(Player player) {

    }

    @Override
    public void rightActivate(Player player) {
        if (player.getInventory().contains(Material.WHEAT_SEEDS)) {
            player.getInventory().removeItem(new ItemStack(Material.WHEAT_SEEDS, 1));
            new Seed(plugin).createProjectile(player,speed,damage,spread,duration);
            player.getWorld().playSound(player.getLocation(), "terraria:blowpipe_use", 1.0F, 1.0F);
        }else if (player.getInventory().contains(Material.MELON_SEEDS)){
            player.getInventory().removeItem(new ItemStack(Material.MELON_SEEDS, 1));
            new Seed(plugin).createProjectile(player,speed,damage,spread,duration);
            player.getWorld().playSound(player.getLocation(), "terraria:blowpipe_use", 1.0F, 1.0F);
        }else if (player.getInventory().contains(Material.PUMPKIN_SEEDS)){
            player.getInventory().removeItem(new ItemStack(Material.PUMPKIN_SEEDS, 1));
            new Seed(plugin).createProjectile(player,speed,damage,spread,duration);
            player.getWorld().playSound(player.getLocation(), "terraria:blowpipe_use", 1.0F, 1.0F);
        }
        else if (player.getInventory().contains(Material.BEETROOT_SEEDS)){
            player.getInventory().removeItem(new ItemStack(Material.BEETROOT_SEEDS, 1));
            new Seed(plugin).createProjectile(player,speed,damage,spread,duration);
            player.getWorld().playSound(player.getLocation(), "terraria:blowpipe_use", 1.0F, 1.0F);
        }
        else if (player.getInventory().contains(Material.TORCHFLOWER_SEEDS)){
            player.getInventory().removeItem(new ItemStack(Material.TORCHFLOWER_SEEDS, 1));
            new Seed(plugin).createProjectile(player,speed,damage,spread,duration);
            player.getWorld().playSound(player.getLocation(), "terraria:blowpipe_use", 1.0F, 1.0F);
        }

    }

}
