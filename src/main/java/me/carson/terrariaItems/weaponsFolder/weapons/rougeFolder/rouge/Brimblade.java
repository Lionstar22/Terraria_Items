package me.carson.terrariaItems.weaponsFolder.weapons.rougeFolder.rouge;

import me.carson.terrariaItems.projectilesFolder.rougeProjectiles.BlazingStarProjectile;
import me.carson.terrariaItems.projectilesFolder.rougeProjectiles.BrimbladeProjectile;
import me.carson.terrariaItems.projectilesFolder.rougeProjectiles.BrimstoneDart;
import me.carson.terrariaItems.weaponsFolder.weapons.rougeFolder.Rouge;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class Brimblade extends Rouge {

    public Brimblade(Plugin plugin) {
        super(plugin,"brimblade.name","#FF96FF", Material.SCRAPE_POTTERY_SHERD,"brimblade","Brimblade",10,0.9f,10,0,20,"brimblade.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        return new Brimblade(plugin).createItem();
    }

    @Override
    public void leftActivate(Player player) {
        boolean stealth=stealthManager.isStealthStrike(player);
        player.getWorld().playSound(player.getLocation(), "terraria:sword_use", 1.0F, 1.0F);
        new BrimbladeProjectile(plugin).createBrimbladeProjectile(player,speed,damage,spread,duration,15,stealthManager.getStealth(player.getUniqueId()),stealth);
        if(stealth){
            stealthAttack(player);
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
        new BrimstoneDart(plugin).shootDarts(player,stealthManager.getStealth(player.getUniqueId()));
    }
}
