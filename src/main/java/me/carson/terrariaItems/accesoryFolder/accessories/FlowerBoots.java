package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;


public class FlowerBoots extends Accessory  {

    public FlowerBoots(Plugin plugin){
        super(plugin,"flower_boots.name","#96FF0A",Material.EXPLORER_POTTERY_SHERD,"aglet","flower_boots","flower_boots.lore");
    }

    @Override
    public void activateEffect(Player player){

    }

    @Override
    public void deactivateEffect(Player player) {

    }

    @Override
    public void onPlayerHit(Player player, EntityDamageEvent event) {

    }

    @Override
    public void onPlayerEffect(Player player, EntityPotionEffectEvent event) {

    }


    public static ItemStack getItem(Plugin plugin) {
        return new FlowerBoots(plugin).createItem();
    }

}
