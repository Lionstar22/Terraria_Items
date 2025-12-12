package me.carson.terrariaItems.projectilesFolder.projectiles;

import me.carson.terrariaItems.materialsFolder.materials.FallenStar;
import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class FallingStar extends Projectile {

    public FallingStar(Plugin plugin) {
        super(plugin, 50,  "fallen_star", "FallingStar",5, DamageType.PLAYER_ATTACK);
    }

    @Override
    public void hitEntityEffect(LivingEntity entity) {

    }

    @Override
    public void hitBlockEffect(Block block) {
        block.getWorld().playSound(block.getLocation(), "terraria:falling_star_impact", 2.0F, 1.0F);
        block.getWorld().dropItemNaturally(block.getLocation().add(0,1,0), FallenStar.getItem(plugin));
    }

    public void starFall(Player player){
        float valueX = ThreadLocalRandom.current().nextFloat(-10, 10);
        float valueZ = ThreadLocalRandom.current().nextFloat(-10, 10);
        Location loc =player.getLocation().add(valueX,0,valueZ);
        new FallingStar(plugin).createFallingProjectile(player,1,0,5,50,30,loc);
        player.getWorld().playSound(loc.clone().add(0,5,0), "terraria:falling_star", 5.0F, 1.0F);
    }

}
