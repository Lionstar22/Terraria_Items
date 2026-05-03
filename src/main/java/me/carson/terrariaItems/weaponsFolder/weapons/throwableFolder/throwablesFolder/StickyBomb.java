package me.carson.terrariaItems.weaponsFolder.weapons.throwableFolder.throwablesFolder;

import me.carson.terrariaItems.throwablesFolder.throwables.StickyBombProjectile;
import me.carson.terrariaItems.weaponsFolder.weapons.throwableFolder.ThrowableWeapon;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class StickyBomb extends ThrowableWeapon {

    public StickyBomb(Plugin plugin) {
        super(plugin,"sticky_bomb.name","#FFFFFF", Material.GUNPOWDER,"sticky_bomb","StickyBomb",15,0.75f,0,0,60,"sticky_bomb.lore");
    }

    @Override
    public void leftActivate(Player player) {

    }

    @Override
    public void rightActivate(Player player) {
        player.getInventory().removeItem(getItem(plugin));
        player.getWorld().playSound(player.getLocation(),"terraria:sword_use", 0.5F, 1.0F);
        new StickyBombProjectile(plugin).createThrowableObj(player,speed,damage,spread,duration,0,0.05f);
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item =new StickyBomb(plugin).createItem();
        ItemMeta meta= item.getItemMeta();
        meta.setMaxStackSize(99);
        item.setItemMeta(meta);
        return item;
    }

}
