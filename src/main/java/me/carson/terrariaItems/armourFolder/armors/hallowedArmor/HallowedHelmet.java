package me.carson.terrariaItems.armourFolder.armors.hallowedArmor;

import me.carson.terrariaItems.armourFolder.Armor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
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

public class HallowedHelmet extends Armor implements Listener {

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

    public HallowedHelmet(Plugin plugin){
        super(plugin,"Hallowed Helmet","#FF96FF", Material.NETHERITE_HELMET,"hallowed_helmet","hallowed_armor", EquipmentSlot.HEAD,"HallowedHelmet",new ArrayList<>(List.of(ChatColor.GRAY+"15% increased ranged damage",ChatColor.GRAY+"8% increased critical strike chance",ChatColor.GRAY+"Set Bonus: Dodge every 30 seconds",ChatColor.GRAY+"+20 Armor")));
    }

    @Override
    public void activateArmorEffect(Player player) {
        playerInstance.addBonusRanged(player.getUniqueId(),0.15);
        playerInstance.addCritChance(player.getUniqueId(),0.08);
    }

    @Override
    public void deactivateArmorEffect(Player player) {
        playerInstance.subtractBonusRanged(player.getUniqueId(),0.15);
        playerInstance.subtractCritChance(player.getUniqueId(),0.08);
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack helm=new HallowedHelmet(plugin).createItem();
        ItemMeta meta=helm.getItemMeta();
        meta.addAttributeModifier(Attribute.ARMOR,new AttributeModifier(new NamespacedKey(plugin,"hallowed_helmet_armor"),20.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD));
        meta.addAttributeModifier(Attribute.ARMOR_TOUGHNESS,new AttributeModifier(new NamespacedKey(plugin,"hallowed_helmet_armor_toughness"),5.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD));
        meta.addAttributeModifier(Attribute.KNOCKBACK_RESISTANCE,new AttributeModifier(new NamespacedKey(plugin,"hallowed_helmet_k_resistance"),1.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD));
        meta.addEnchant(Enchantment.PROTECTION,2,true);
        meta.setEnchantmentGlintOverride(false);
        helm.setItemMeta(meta);
        return helm;
    }

}
