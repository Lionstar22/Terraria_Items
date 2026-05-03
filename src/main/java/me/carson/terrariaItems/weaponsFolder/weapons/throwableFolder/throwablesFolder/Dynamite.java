package me.carson.terrariaItems.weaponsFolder.weapons.throwableFolder.throwablesFolder;

import me.carson.terrariaItems.throwablesFolder.throwables.BombProjectile;
import me.carson.terrariaItems.throwablesFolder.throwables.DynamiteProjectile;
import me.carson.terrariaItems.weaponsFolder.weapons.throwableFolder.ThrowableWeapon;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class Dynamite extends ThrowableWeapon {

    public Dynamite(Plugin plugin) {
        super(plugin,"dynamite.name","#9696FF", Material.FIREWORK_STAR,"dynamite","Dynamite",20,0.75f,0,0,100,"dynamite.lore");
    }

    @Override
    public void leftActivate(Player player) {

    }

    @Override
    public void rightActivate(Player player) {
        player.getInventory().removeItem(getItem(plugin));
        player.getWorld().playSound(player.getLocation(),"terraria:sword_use", 0.5F, 1.0F);
        new DynamiteProjectile(plugin).createThrowableObj(player,speed,damage,spread,duration,0,0.05f);
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item =new Dynamite(plugin).createItem();
        ItemMeta meta= item.getItemMeta();
        meta.setMaxStackSize(99);
        item.setItemMeta(meta);
        return item;
    }

}
