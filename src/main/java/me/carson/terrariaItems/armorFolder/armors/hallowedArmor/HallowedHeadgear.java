package me.carson.terrariaItems.armorFolder.armors.hallowedArmor;

import me.carson.terrariaItems.armorFolder.Armor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class HallowedHeadgear extends Armor implements Listener {

    private static final Set<EntityDamageEvent.DamageCause> COUNTERABLE_CAUSES = Set.of(
            EntityDamageEvent.DamageCause.ENTITY_ATTACK,
            EntityDamageEvent.DamageCause.PROJECTILE,
            EntityDamageEvent.DamageCause.ENTITY_EXPLOSION,
            EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK,
            EntityDamageEvent.DamageCause.LIGHTNING,
            EntityDamageEvent.DamageCause.MAGIC,
            EntityDamageEvent.DamageCause.SONIC_BOOM,
            EntityDamageEvent.DamageCause.DRAGON_BREATH
    );

    public HallowedHeadgear(Plugin plugin){
        super(plugin,"hallowed_headgear.name","#FF96FF", Material.NETHERITE_HELMET,"hallowed_headgear","hallowed_armor", EquipmentSlot.HEAD,"HallowedHeadgear","hallowed_headgear.lore");
    }

    @Override
    public void activateArmorEffect(Player player) {
        playerInstance.addBonusMagic(player.getUniqueId(),0.12);
        playerInstance.addExtraMana(player.getUniqueId(),100);
        playerInstance.addCritChance(player.getUniqueId(),0.12);
    }

    @Override
    public void deactivateArmorEffect(Player player) {
        playerInstance.subtractBonusMagic(player.getUniqueId(),0.12);
        playerInstance.subtractExtraMana(player.getUniqueId(),100);
        playerInstance.subtractCritChance(player.getUniqueId(),0.12);
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack helm=new HallowedHeadgear(plugin).createItem();
        ItemMeta meta=helm.getItemMeta();
        meta.addAttributeModifier(Attribute.ARMOR,new AttributeModifier(new NamespacedKey(plugin,"hallowed_headgear_armor"),20.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD));
        meta.addAttributeModifier(Attribute.ARMOR_TOUGHNESS,new AttributeModifier(new NamespacedKey(plugin,"hallowed_headgear_armor_toughness"),5.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD));
        meta.addAttributeModifier(Attribute.KNOCKBACK_RESISTANCE,new AttributeModifier(new NamespacedKey(plugin,"hallowed_headgear_resistance"),1.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD));
        meta.setEnchantmentGlintOverride(false);
        helm.setItemMeta(meta);
        return helm;
    }

}
