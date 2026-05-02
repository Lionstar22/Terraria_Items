package me.carson.terrariaItems.weaponsFolder.weapons.throwableFolder.throwablesFolder;

import me.carson.terrariaItems.throwablesFolder.throwables.GrenadeObj;
import me.carson.terrariaItems.throwablesFolder.throwables.StickyGrenadeObj;
import me.carson.terrariaItems.weaponsFolder.weapons.throwableFolder.ThrowableWeapon;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class StickyGrenade extends ThrowableWeapon {

    public StickyGrenade(Plugin plugin) {
        super(plugin,"sticky_grenade.name","#FFFFFF", Material.SLIME_BALL,"sticky_grenade","StickyGrenade",15,1,5,0,60,"sticky_grenade.lore");
    }

    @Override
    public void leftActivate(Player player) {

    }

    @Override
    public void rightActivate(Player player) {
        player.getInventory().removeItem(getItem(plugin));
        player.getWorld().playSound(player.getLocation(),"terraria:sword_use", 0.5F, 1.0F);
        new StickyGrenadeObj(plugin).createThrowableObj(player,speed,damage,spread,duration,0,0.05f);
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item =new StickyGrenade(plugin).createItem();
        ItemMeta meta= item.getItemMeta();
        meta.setMaxStackSize(99);
        item.setItemMeta(meta);
        return item;
    }

}
