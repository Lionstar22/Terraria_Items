package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class LuckyHorseshoe extends Accessory{

    public LuckyHorseshoe(Plugin plugin){
        super(plugin,"lucky_horseshoe.name","#9696FF",Material.GOLD_BLOCK,"lucky_horseshoe","LuckyHorseshoe","lucky_horseshoe.lore");
    }

    @Override
    public void activateEffect(Player player){

    }

    @Override
    public void deactivateEffect(Player player) {

    }

    @Override
    public void onPlayerHit(Player player, EntityDamageEvent event) {
        if(((event.getCause() == EntityDamageEvent.DamageCause.FALL)||(event.getCause() == EntityDamageEvent.DamageCause.FLY_INTO_WALL))){
            event.setCancelled(true);
        }
    }

    @Override
    public void onPlayerEffect(Player player, EntityPotionEffectEvent event) {

    }

    public static ItemStack getItem(Plugin plugin) {
        return new LuckyHorseshoe(plugin).createItem();
    }

}
