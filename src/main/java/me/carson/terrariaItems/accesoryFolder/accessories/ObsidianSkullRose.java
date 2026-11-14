package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class ObsidianSkullRose extends Accessory implements Listener {

    public ObsidianSkullRose(Plugin plugin){
        super(plugin,"obsidian_skull_rose.name","#FF96FF", Material.NETHER_BRICK,"obsidian_skull_rose","ObsidianSkullRose","obsidian_skull_rose.lore");
    }

    @Override
    public void activateEffect(Player player){

    }

    @Override
    public void deactivateEffect(Player player) {

    }

    @Override
    public void onPlayerHit(Player player, EntityDamageEvent event) {
        if (OBSIDIAN_SKULL_DAMAGE.contains(event.getCause())){
            event.setCancelled(true);
        }else if(event.getCause()== EntityDamageEvent.DamageCause.LAVA){
            event.setDamage(event.getDamage()/2);
        }
    }

    @Override
    public void onPlayerEffect(Player player, EntityPotionEffectEvent event) {

    }

    public static ItemStack getItem(Plugin plugin) {
        return new ObsidianSkullRose(plugin).createItem();
    }

}
