package me.carson.terrariaItems.weaponsFolder.weapons.magicFolder.magicWeapons;

import me.carson.terrariaItems.projectilesFolder.projectiles.LaserRifleProjectile;
import me.carson.terrariaItems.weaponsFolder.weapons.magicFolder.Magic;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.UUID;

public class LaserRifle extends Magic {

    public LaserRifle(Plugin plugin) {
        super(plugin,"laser_rifle.name","#FF9696", Material.PRISMARINE_SHARD,"laser_rifle","LaserRifle",5,2.5f,8,0,75,8,"laser_rifle.lore");
    }

    @Override
    public void leftActivate(Player player) {

    }

    @Override
    public void rightActivate(Player player) {
        if(!isThisItem(player.getInventory().getItemInMainHand())){return;}
        UUID id=player.getUniqueId();
        if(manaManagerInstance.getMana(id)<cost){return;}
        new LaserRifleProjectile(plugin).createProjectile(player,speed,damage,spread,duration);
        player.getWorld().playSound(player.getLocation(), "terraria:laser_2", 1.0F, 1.0F);
        manaManagerInstance.removeMana(id, cost);
        manaManagerInstance.updateManaBar(player);
        manaManagerInstance.startManaRegenDelay(player,manaManagerInstance);
    }

    public static ItemStack getItem(Plugin plugin) {
        return new LaserRifle(plugin).createItem();
    }

}
