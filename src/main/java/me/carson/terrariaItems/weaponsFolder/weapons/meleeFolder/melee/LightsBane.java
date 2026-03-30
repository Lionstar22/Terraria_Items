package me.carson.terrariaItems.weaponsFolder.weapons.meleeFolder.melee;

import me.carson.terrariaItems.weaponsFolder.weapons.meleeFolder.Sword;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class LightsBane extends Sword {
    public LightsBane(Plugin plugin) {
        super(plugin,"Lights Bane","#9696FF",Material.IRON_SWORD,"lights_bane","LightsBane",10,0,0,0,0, new ArrayList<>(List.of(ChatColor.GRAY+"Creates a dark slash near the tip of the sword")));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new LightsBane(plugin).createItem();
    }

    @Override
    public void leftActivate(Player player) {
        Location loc = player.getEyeLocation();
        Vector dir = loc.getDirection().normalize();
        loc.add(dir.multiply(3));
        player.getWorld().spawnParticle(Particle.WITCH, loc, 3, 0, -1, 0);
        player.getWorld().spawnParticle(Particle.WITCH, loc, 3, 0, -1, 0);
        player.getWorld().playSound(player.getLocation(), "terraria:sword_use", 1.0F, 1.0F);

        RayTraceResult result = player.getWorld().rayTraceEntities(
                loc,
                player.getEyeLocation().getDirection(),
                1
        );
        if(result==null){return;}
        LivingEntity target= (LivingEntity) result.getHitEntity();
        assert target != null;
        target.damage(10);
    }

    @Override
    public void rightActivate(Player player) {

    }

}
