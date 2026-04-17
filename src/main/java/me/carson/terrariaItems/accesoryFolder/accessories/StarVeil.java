package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import me.carson.terrariaItems.projectilesFolder.projectiles.StarCannonStar;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;


public class StarVeil extends Accessory  {

    public StarVeil(Plugin plugin){
        super(plugin,"star_veil.name","#D2A0FF",Material.NETHER_BRICK,"star_veil","StarVeil","star_veil.lore");
    }

    @Override
    public void activateEffect(Player player){
        player.setMaximumNoDamageTicks(60); //default is 20
    }

    @Override
    public void deactivateEffect(Player player) {
        player.setMaximumNoDamageTicks(20); //default is 20
    }

    @Override
    public void onPlayerHit(Player player, EntityDamageEvent event) {
        new StarCannonStar(plugin).createFallingProjectile(player,1.5f,7,5f,50,30,event.getEntity().getLocation());
        new StarCannonStar(plugin).createFallingProjectile(player,1.5f,7,5f,50,30,event.getEntity().getLocation());
        new StarCannonStar(plugin).createFallingProjectile(player,1.5f,7,5f,50,30,event.getEntity().getLocation());
        player.getWorld().playSound(event.getEntity().getLocation(), "terraria:falling_star", 0.75F, 1.0F);
    }

    @Override
    public void onPlayerEffect(Player player, EntityPotionEffectEvent event) {

    }

    public static ItemStack getItem(Plugin plugin) {
        return new StarVeil(plugin).createItem();
    }

}
