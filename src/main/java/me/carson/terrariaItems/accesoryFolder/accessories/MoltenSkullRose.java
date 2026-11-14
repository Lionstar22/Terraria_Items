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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MoltenSkullRose extends Accessory implements Listener {

    public MoltenSkullRose(Plugin plugin){
        super(plugin,"molten_skull_rose.name","#D2A0FF", Material.ORANGE_DYE,"molten_skull_rose","MoltenSkullRose","molten_skull_rose.lore");
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
            if(!player.hasCooldown(Material.ORANGE_DYE)){
                player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 140, 0, false, false,false));
                player.setCooldown(Material.ORANGE_DYE,1200);
                event.setCancelled(true);
            }else {
                event.setDamage(event.getDamage()/2);
            }
        }
    }

    @Override
    public void onPlayerEffect(Player player, EntityPotionEffectEvent event) {

    }

    public static ItemStack getItem(Plugin plugin) {
        return new MoltenSkullRose(plugin).createItem();
    }

}
