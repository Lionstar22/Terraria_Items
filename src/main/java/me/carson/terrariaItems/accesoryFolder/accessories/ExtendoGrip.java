package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
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


public class ExtendoGrip extends Accessory  {

    public ExtendoGrip(Plugin plugin){
        super(plugin,"extendo_grip.name","#FFC896",Material.ARMS_UP_POTTERY_SHERD,"extendo_grip","ExtendoGrip","extendo_grip.lore");
    }

    @Override
    public void activateEffect(Player player){
        player.getAttribute(Attribute.BLOCK_INTERACTION_RANGE).removeModifier(new AttributeModifier(new NamespacedKey(plugin,"extendo_grip_block_interaction_range"),2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
        player.getAttribute(Attribute.BLOCK_INTERACTION_RANGE).addModifier(new AttributeModifier(new NamespacedKey(plugin,"extendo_grip_block_interaction_range"),2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
    }

    @Override
    public void deactivateEffect(Player player) {
        player.getAttribute(Attribute.BLOCK_INTERACTION_RANGE).removeModifier(new AttributeModifier(new NamespacedKey(plugin,"extendo_grip_block_interaction_range"),2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
    }

    @Override
    public void onPlayerHit(Player player, EntityDamageEvent event) {

    }

    @Override
    public void onPlayerEffect(Player player, EntityPotionEffectEvent event) {

    }


    public static ItemStack getItem(Plugin plugin) {
        return new ExtendoGrip(plugin).createItem();
    }

}
