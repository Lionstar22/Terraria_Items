package me.carson.terrariaItems.weaponsFolder.weapons.throwableFolder.throwablesFolder;

import me.carson.terrariaItems.throwablesFolder.throwables.StickyDynamiteProjectile;
import me.carson.terrariaItems.weaponsFolder.weapons.throwableFolder.ThrowableWeapon;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class StickyDynamite extends ThrowableWeapon {

    public StickyDynamite(Plugin plugin) {
        super(plugin,"sticky_dynamite.name","#9696FF", Material.FIREWORK_STAR,"sticky_dynamite","StickyDynamite",20,0.75f,0,0,100,"sticky_dynamite.lore");
    }

    @Override
    public void leftActivate(Player player) {

    }

    @Override
    public void rightActivate(Player player) {
        player.getInventory().removeItem(getItem(plugin));
        player.getWorld().playSound(player.getLocation(),"terraria:sword_use", 0.5F, 1.0F);
        new StickyDynamiteProjectile(plugin).createThrowableObj(player,speed,damage,spread,duration,0,0.05f);
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item =new StickyDynamite(plugin).createItem();
        ItemMeta meta= item.getItemMeta();
        meta.setMaxStackSize(99);
        item.setItemMeta(meta);
        return item;
    }

}
