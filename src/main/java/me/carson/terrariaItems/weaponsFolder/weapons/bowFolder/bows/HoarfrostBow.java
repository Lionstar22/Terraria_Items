package me.carson.terrariaItems.weaponsFolder.weapons.bowFolder.bows;

import me.carson.terrariaItems.projectilesFolder.projectiles.MistArrow;
import me.carson.terrariaItems.weaponsFolder.weapons.bowFolder.Bow;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class HoarfrostBow extends Bow {

    public HoarfrostBow(Plugin plugin) {
        super(plugin,"Hoarfrost Bow","#FF96FF", Material.BOW,"hoarfrost_bow","HoarfrostBow",10,2.5f,6,0.075f, new ArrayList<>(List.of(ChatColor.GRAY+"Fires two arrows at once",ChatColor.GRAY+"Converts wooden arrows into mist arrows")));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new HoarfrostBow(plugin).createItem();
    }

    @Override
    public void leftActivate(Player player) {

    }

    @Override
    public void rightActivate(Player player) {
        if (player.getInventory().contains(Material.ARROW)) {
            player.getInventory().removeItem(new ItemStack(Material.ARROW, 1));
            Location loc = player.getEyeLocation();
            new MistArrow(plugin).createProjectile(player,speed,damage,spread,100f);
            new MistArrow(plugin).createProjectile(player,speed,damage,spread,100f);
            player.getWorld().playSound(loc, "terraria:repeater_use", 0.75F, 1.0F);
        }
    }

}
