package me.carson.terrariaItems.weaponsFolder.weapons;

import me.carson.terrariaItems.materialsFolder.materials.MusketBall;
import me.carson.terrariaItems.projectilesFolder.projectiles.BulletProjectile;
import me.carson.terrariaItems.projectilesFolder.projectiles.NeedlerProjectile;
import me.carson.terrariaItems.weaponsFolder.Weapon;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class Needler extends Weapon {

    public Needler(Plugin plugin) {
        super(plugin,"Needler","#FF9696",6, 0f, Material.STICK,"needler","Needler",10, new ArrayList<>(List.of(ChatColor.GRAY+"Converts musket balls into poison needles")));
    }

    @Override
    public void leftActivate(Player player) {

    }

    @Override
    public void rightActivate(Player player) {
        for (ItemStack itemInv : player.getInventory().getContents()) {
            if (new MusketBall(plugin).isThisItem(itemInv)) {
                player.getInventory().removeItem(MusketBall.getItem(plugin));
                player.getWorld().playSound(player.getLocation(),"terraria:needler_use", 1.0F, 1.0F);
                new NeedlerProjectile(plugin).createProjectile(player,super.damage,super.spread);
                break;
            }
        }
    }

    public static ItemStack getItem(Plugin plugin) {
        return new Needler(plugin).createItem();
    }

}
