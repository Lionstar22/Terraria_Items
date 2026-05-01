package me.carson.terrariaItems.weaponsFolder.weapons.throwableFolder.throwablesFolder;

import me.carson.terrariaItems.throwablesFolder.throwables.GrenadeObj;
import me.carson.terrariaItems.weaponsFolder.weapons.throwableFolder.ThrowableWeapon;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class Grenade extends ThrowableWeapon {

    public Grenade(Plugin plugin) {
        super(plugin,"grenade.name","#FFFFFF", Material.SLIME_BALL,"grenade","Grenade",10,1,10,0,60,"grenade.lore");
    }

    @Override
    public void leftActivate(Player player) {

    }

    @Override
    public void rightActivate(Player player) {
        new GrenadeObj(plugin).createThrowableObj(player,speed,damage,spread,duration,0,0.05f);
    }

    public static ItemStack getItem(Plugin plugin) {
        return new Grenade(plugin).createItem();
    }

}
