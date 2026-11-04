package me.carson.terrariaItems.weaponsFolder.weapons.rougeFolder.rouge;

import me.carson.terrariaItems.projectilesFolder.rougeProjectiles.BlazingStarProjectile;
import me.carson.terrariaItems.projectilesFolder.rougeProjectiles.InfernalKrisProjectile;
import me.carson.terrariaItems.weaponsFolder.weapons.rougeFolder.Rouge;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class InfernalKris extends Rouge {

    public InfernalKris(Plugin plugin) {
        super(plugin,"infernal_kris.name","#FFC896", Material.BLAZE_ROD,"infernal_kris","InfernalKris",10,0.9f,6,0,200,"infernal_kris.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        return new InfernalKris(plugin).createItem();
    }

    @Override
    public void leftActivate(Player player) {
        player.getWorld().playSound(player.getLocation(), "terraria:sword_use", 1.0F, 1.0F);
        new InfernalKrisProjectile(plugin).createKris(player,speed,damage,duration,20,stealthManager.getStealth(player.getUniqueId()),stealthManager.isStealthStrike(player));
        stealthManager.reduceStealth(player);
        stealthManager.startStealthRegenDelay(player);
    }

    @Override
    public void rightActivate(Player player) {
        leftActivate(player);
    }

    @Override
    public void stealthAttack(Player player) {

    }
}
