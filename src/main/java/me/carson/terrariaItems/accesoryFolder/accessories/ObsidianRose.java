package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;


public class ObsidianRose extends Accessory  {

    public ObsidianRose(Plugin plugin){
        super(plugin,"obsidian_rose.name","#FFC896",Material.NETHER_BRICK,"obsidian_rose","ObsidianRose","obsidian_rose.lore");
    }

    @Override
    public void activateEffect(Player player){

    }

    @Override
    public void deactivateEffect(Player player) {

    }

    @Override
    public void onPlayerHit(Player player, EntityDamageEvent event) {
        if(event.getCause()== EntityDamageEvent.DamageCause.LAVA){
            event.setDamage(event.getDamage()/2);
        }
    }

    @Override
    public void onPlayerEffect(Player player, EntityPotionEffectEvent event) {

    }

    public static ItemStack getItem(Plugin plugin) {
        return new ObsidianRose(plugin).createItem();
    }

}
