package me.carson.terrariaItems.armourFolder.armors.hallowedArmor;

import me.carson.terrariaItems.accesoryFolder.accessories.CounterScarf;
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
        super(plugin,"Hallowed Helmet","#FF96FF", Material.NETHERITE_HELMET,"hallowed_helmet","hallowed_armor", EquipmentSlot.HEAD,"HallowedHelmet",new ArrayList<>(List.of(ChatColor.GRAY+"Grants dodge every 10 seconds",ChatColor.GRAY+"50% Damage Increase",ChatColor.GRAY+"20% Movement Speed Increase")));
    }

    @Override
    public void activateArmorEffect(Player player) {
        playerInstance.addBonusDamage(player.getUniqueId(),0.1);
    }

    @Override
    public void deactivateArmorEffect(Player player) {
        playerInstance.subtractBonusDamage(player.getUniqueId(),0.1);
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack helm=new HallowedHelmet(plugin).createItem();
        ItemMeta meta=helm.getItemMeta();
        meta.addAttributeModifier(Attribute.ARMOR,new AttributeModifier(new NamespacedKey(plugin,"hallowed_helmet_armor"),20.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD));
        meta.addAttributeModifier(Attribute.ARMOR_TOUGHNESS,new AttributeModifier(new NamespacedKey(plugin,"hallowed_helmet_armor_toughness"),5.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD));
        meta.addAttributeModifier(Attribute.KNOCKBACK_RESISTANCE,new AttributeModifier(new NamespacedKey(plugin,"hallowed_helmet_k_resistance"),1.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD));
        meta.addEnchant(Enchantment.PROTECTION,4,true);
        meta.setEnchantmentGlintOverride(false);
        helm.setItemMeta(meta);
        return helm;
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player player)) return;
        PlayerInventory inv = player.getInventory();
        ItemStack helm =inv.getHelmet();
        if(hasSetBonus(player)&&isThisItem(helm)){
            double boostedDamage = event.getDamage() * 1.5;
            event.setDamage(boostedDamage);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        PlayerInventory inv = player.getInventory();
        ItemStack helm =inv.getHelmet();
        if(helm==null){return;}
        if(hasSetBonus(player)&&isThisItem(helm)&&!player.hasCooldown(helm)){
            if (((COUNTERABLE_CAUSES.contains(event.getCause())))){
                player.setCooldown(helm,200);
                event.setCancelled(true);
            }
        }
    }

}
