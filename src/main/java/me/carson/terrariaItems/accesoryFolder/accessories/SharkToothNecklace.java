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


public class SharkToothNecklace extends Accessory  {

    public SharkToothNecklace(Plugin plugin){
        super(plugin,"shark_tooth_necklace.name","#9696FF",Material.NETHER_BRICK,"shark_tooth_necklace","SharkToothNecklace","shark_tooth_necklace.lore");
    }

    @Override
    public void activateEffect(Player player){
        player.getAttribute(Attribute.ATTACK_DAMAGE).removeModifier(new AttributeModifier(new NamespacedKey(plugin,"shark_tooth_necklace_attack_damage"),1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
        player.getAttribute(Attribute.ATTACK_DAMAGE).addModifier(new AttributeModifier(new NamespacedKey(plugin,"shark_tooth_necklace_attack_damage"),1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
    }

    @Override
    public void deactivateEffect(Player player) {
        player.getAttribute(Attribute.ATTACK_DAMAGE).removeModifier(new AttributeModifier(new NamespacedKey(plugin,"shark_tooth_necklace_attack_damage"),1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
    }

    @Override
    public void onPlayerHit(Player player, EntityDamageEvent event) {

    }

    @Override
    public void onPlayerEffect(Player player, EntityPotionEffectEvent event) {

    }

    public static ItemStack getItem(Plugin plugin) {
        return new SharkToothNecklace(plugin).createItem();
    }

}
