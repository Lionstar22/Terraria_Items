package me.carson.terrariaItems.weaponsFolder.weapons.gunFolder.guns;

import me.carson.terrariaItems.projectilesFolder.projectiles.BoneThrowingKnifeProjectile;
import me.carson.terrariaItems.projectilesFolder.projectiles.ThrowingKnifeProjectile;
import me.carson.terrariaItems.weaponsFolder.weapons.gunFolder.Gun;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class BoneThrowingKnife extends Gun {

    public BoneThrowingKnife(Plugin plugin) {
        super(plugin,"bone_throwing_knife.name","#9696FF",Material.GOLD_NUGGET,"bone_throwing_knife","BoneThrowingKnife",6,1.25f,5f,0,150,"bone_throwing_knife.lore");
    }

    @Override
    public void leftActivate(Player player) {
        rightActivate(player);
    }

    @Override
    public void rightActivate(Player player) {
        player.getInventory().removeItem(getItem(plugin));
        player.getWorld().playSound(player.getLocation(),"terraria:sword_use", 0.75F, 1.0F);
        new BoneThrowingKnifeProjectile(plugin).createGravProjectile(player,speed,damage,spread,duration,15,0.1f);
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item =new BoneThrowingKnife(plugin).createItem();
        ItemMeta meta= item.getItemMeta();
        meta.setMaxStackSize(99);
        item.setItemMeta(meta);
        return item;
    }

}
