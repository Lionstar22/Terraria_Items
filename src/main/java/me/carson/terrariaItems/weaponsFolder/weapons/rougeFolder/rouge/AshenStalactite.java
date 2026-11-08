package me.carson.terrariaItems.weaponsFolder.weapons.rougeFolder.rouge;

import me.carson.terrariaItems.projectilesFolder.rougeProjectiles.AshenStalactiteProjectile;
import me.carson.terrariaItems.projectilesFolder.rougeProjectiles.BlazingStarProjectile;
import me.carson.terrariaItems.weaponsFolder.weapons.rougeFolder.Rouge;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class AshenStalactite extends Rouge {

    public AshenStalactite(Plugin plugin) {
        super(plugin,"ashen_stalactite.name","#FFC896", Material.BLAZE_ROD,"ashen_stalactite","AshenStalactite",12,0.7f,6,0,25,"ashen_stalactite.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        return new AshenStalactite(plugin).createItem();
    }

    @Override
    public void leftActivate(Player player) {
        player.getWorld().playSound(player.getLocation(), "terraria:sword_use", 1.0F, 1.0F);
        if(stealthManager.isStealthStrike(player)){
            new AshenStalactiteProjectile(plugin).createStealthStalactite(player,damage,stealthManager.getStealth(player.getUniqueId()));
        }else{
            new AshenStalactiteProjectile(plugin).createStalactite(player,speed,damage,duration,stealthManager.getStealth(player.getUniqueId()));
        }
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
