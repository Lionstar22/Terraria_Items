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


public class StarCloak extends Accessory  {

    public StarCloak(Plugin plugin){
        super(plugin,"star_cloak.name","#FF9696",Material.NETHER_BRICK,"star_cloak","StarCloak","star_cloak.lore");
    }

    @Override
    public void activateEffect(Player player){

    }

    @Override
    public void deactivateEffect(Player player) {

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
        return new StarCloak(plugin).createItem();
    }

}
