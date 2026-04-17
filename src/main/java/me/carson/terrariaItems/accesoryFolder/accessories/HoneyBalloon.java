package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;


public class HoneyBalloon extends Accessory  {

    public HoneyBalloon(Plugin plugin){
        super(plugin,"honey_balloon.name","#96FF96",Material.NETHER_BRICK,"honey_balloon","HoneyBalloon","honey_balloon.lore");
    }

    @Override
    public void activateEffect(Player player){
        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, -1, 0, true, false));
    }

    @Override
    public void deactivateEffect(Player player) {
        player.removePotionEffect(PotionEffectType.JUMP_BOOST);
    }

    @Override
    public void onPlayerHit(Player player, EntityDamageEvent event) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,100,1,false,false,false));
    }

    @Override
    public void onPlayerEffect(Player player, EntityPotionEffectEvent event) {

    }

    public static ItemStack getItem(Plugin plugin) {
        return new HoneyBalloon(plugin).createItem();
    }

}
