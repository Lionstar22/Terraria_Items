package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class HandWarmer extends Accessory  {

    public HandWarmer(Plugin plugin){
        super(plugin,"hand_warmer.name","#96FF96",Material.NETHER_BRICK,"hand_warmer","HandWarmer","hand_warmer.lore");
    }

    @Override
    public void activateEffect(Player player){

    }

    @Override
    public void deactivateEffect(Player player) {

    }

    @Override
    public void onPlayerHit(Player player, EntityDamageEvent event) {
        if(event.getCause()== EntityDamageEvent.DamageCause.FREEZE){
            player.setFreezeTicks(0);
            event.setCancelled(true);
        }
    }

    @Override
    public void onPlayerEffect(Player player, EntityPotionEffectEvent event) {

    }


    public static ItemStack getItem(Plugin plugin) {
        return new HandWarmer(plugin).createItem();
    }

}
