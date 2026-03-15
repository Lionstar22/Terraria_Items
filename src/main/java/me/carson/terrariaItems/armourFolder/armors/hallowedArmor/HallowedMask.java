package me.carson.terrariaItems.armourFolder.armors.hallowedArmor;

import me.carson.terrariaItems.armourFolder.Armor;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class HallowedMask extends Armor implements Listener {

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

    public HallowedMask(Plugin plugin){
        super(plugin,"Hallowed Mask","#FF96FF", Material.NETHERITE_HELMET,"hallowed_mask","hallowed_armor", EquipmentSlot.HEAD,"HallowedMask",new ArrayList<>(List.of(ChatColor.GRAY+"10% increased melee damage and attack speed",ChatColor.GRAY+"10% increased critical strike chance",ChatColor.GRAY+"Set Bonus: Dodge every 30 seconds")));
    }

    @Override
    public void activateArmorEffect(Player player) {
        playerInstance.addBonusMelee(player.getUniqueId(),0.1);
        playerInstance.addCritChance(player.getUniqueId(),0.1);
        player.getAttribute(Attribute.ATTACK_SPEED).setBaseValue(player.getAttribute(Attribute.ATTACK_SPEED).getValue()+0.4);
    }

    @Override
    public void deactivateArmorEffect(Player player) {
        playerInstance.subtractBonusMelee(player.getUniqueId(),0.1);
        playerInstance.subtractCritChance(player.getUniqueId(),0.1);
        player.getAttribute(Attribute.ATTACK_SPEED).setBaseValue(Math.max(player.getAttribute(Attribute.ATTACK_SPEED).getValue()-0.4,4));
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack helm=new HallowedMask(plugin).createItem();
        ItemMeta meta=helm.getItemMeta();
        meta.addAttributeModifier(Attribute.ARMOR,new AttributeModifier(new NamespacedKey(plugin,"hallowed_mask_armor"),20.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD));
        meta.addAttributeModifier(Attribute.ARMOR_TOUGHNESS,new AttributeModifier(new NamespacedKey(plugin,"hallowed_mask_armor_toughness"),5.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD));
        meta.addAttributeModifier(Attribute.KNOCKBACK_RESISTANCE,new AttributeModifier(new NamespacedKey(plugin,"hallowed_mask_k_resistance"),1.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD));
        meta.addEnchant(Enchantment.PROTECTION,4,true);
        meta.setEnchantmentGlintOverride(false);
        helm.setItemMeta(meta);
        return helm;
    }

}
