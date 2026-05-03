package me.carson.terrariaItems.weaponsFolder.weapons.throwableFolder.throwablesFolder;

import me.carson.terrariaItems.throwablesFolder.throwables.GrenadeProjectile;
import me.carson.terrariaItems.throwablesFolder.throwables.SpikyBallProjectile;
import me.carson.terrariaItems.weaponsFolder.weapons.throwableFolder.ThrowableWeapon;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class SpikyBall extends ThrowableWeapon {

    public SpikyBall(Plugin plugin) {
        super(plugin,"spiky_ball.name","#FFFFFF", Material.SLIME_BALL,"spiky_ball","SpikyBall",10,1,3,0,800,"spiky_ball.lore");
    }

    @Override
    public void leftActivate(Player player) {

    }

    @Override
    public void rightActivate(Player player) {
        player.getInventory().removeItem(getItem(plugin));
        player.getWorld().playSound(player.getLocation(),"terraria:sword_use", 0.5F, 1.0F);
        new SpikyBallProjectile(plugin).createThrowableObj(player,speed,damage,spread,duration,0,0.06f);
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item =new SpikyBall(plugin).createItem();
        ItemMeta meta= item.getItemMeta();
        meta.setMaxStackSize(99);
        item.setItemMeta(meta);
        return item;
    }

}
