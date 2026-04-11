package me.carson.terrariaItems.armorFolder.armors.hallowedArmor;

import me.carson.terrariaItems.armorFolder.Armor;
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

public class HallowedChestplate extends Armor implements Listener {

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

    public HallowedChestplate(Plugin plugin){
        super(plugin,"hallowed_chestplate.name","#FF96FF", Material.NETHERITE_CHESTPLATE,"hallowed_chestplate","hallowed_armor", EquipmentSlot.CHEST,"HallowedChestplate","hallowed_chestplate.lore");
    }

    @Override
    public void activateArmorEffect(Player player) {
        playerInstance.addCritChance(player.getUniqueId(),0.07);
    }

    @Override
    public void deactivateArmorEffect(Player player) {
        playerInstance.subtractCritChance(player.getUniqueId(),0.07);
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new HallowedChestplate(plugin).createItem();
        ItemMeta meta=item.getItemMeta();
        meta.addAttributeModifier(Attribute.ARMOR,new AttributeModifier(new NamespacedKey(plugin,"hallowed_chestplate_armor"),25.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST));
        meta.addAttributeModifier(Attribute.ARMOR_TOUGHNESS,new AttributeModifier(new NamespacedKey(plugin,"hallowed_chestplate_armor_toughness"),5.0, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlotGroup.CHEST));
        meta.addAttributeModifier(Attribute.KNOCKBACK_RESISTANCE,new AttributeModifier(new NamespacedKey(plugin,"hallowed_chestplate_k_resistance"),1.0, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlotGroup.CHEST));
        meta.addEnchant(Enchantment.PROTECTION,5,true);
        meta.setEnchantmentGlintOverride(false);
        item.setItemMeta(meta);
        return item;
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if(event.getCause() != EntityDamageEvent.DamageCause.ENTITY_ATTACK){return;}
        PlayerInventory inv = player.getInventory();
        ItemStack chestplate =inv.getChestplate();
        if(chestplate==null){return;}
        if(hasSetBonus(player)&&isThisItem(chestplate)){
            if(!player.hasCooldown(chestplate)){
                if (COUNTERABLE_CAUSES.contains(event.getCause())){
                    player.setCooldown(chestplate,600);
                    event.setCancelled(true);
                }
            }
        }
    }

}
