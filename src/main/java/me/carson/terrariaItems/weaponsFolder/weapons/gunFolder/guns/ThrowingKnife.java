package me.carson.terrariaItems.weaponsFolder.weapons.gunFolder.guns;

import me.carson.terrariaItems.projectilesFolder.projectiles.ThrowingKnifeProjectile;
import me.carson.terrariaItems.weaponsFolder.weapons.gunFolder.Gun;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class ThrowingKnife extends Gun {

    public ThrowingKnife(Plugin plugin) {
        super(plugin,"throwing_knife.name","#FFFFFF",Material.IRON_NUGGET,"throwing_knife","ThrowingKnife",8,1,4f,0,100,"throwing_knife.lore");
    }

    @Override
    public void leftActivate(Player player) {
        rightActivate(player);
    }

    @Override
    public void rightActivate(Player player) {
        player.getInventory().removeItem(getItem(plugin));
        player.getWorld().playSound(player.getLocation(),"terraria:sword_use", 0.75F, 1.0F);
        new ThrowingKnifeProjectile(plugin).createGravProjectile(player,speed,damage,spread,duration,15,0.1f);
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item =new ThrowingKnife(plugin).createItem();
        ItemMeta meta= item.getItemMeta();
        meta.setMaxStackSize(99);
        item.setItemMeta(meta);
        return item;
    }

}
