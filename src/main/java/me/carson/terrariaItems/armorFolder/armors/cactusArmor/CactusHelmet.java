package me.carson.terrariaItems.armorFolder.armors.cactusArmor;

import me.carson.terrariaItems.armorFolder.Armor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.LivingEntity;
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

public class CactusHelmet extends Armor implements Listener {

    public CactusHelmet(Plugin plugin){
        super(plugin,"cactus_helmet.name","#FFFFFF", Material.LEATHER_HELMET,"cactus_helmet","cactus_armor", EquipmentSlot.HEAD,"CactusHelmet","cactus_helmet.lore");
    }

    @Override
    public void activateArmorEffect(Player player) {
    }

    @Override
    public void deactivateArmorEffect(Player player) {
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new CactusHelmet(plugin).createItem();
        ItemMeta meta=item.getItemMeta();
        meta.addAttributeModifier(Attribute.ARMOR,new AttributeModifier(new NamespacedKey(plugin,"cactus_helmet_armor"),1.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD));
        item.setItemMeta(meta);
        return item;
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        if(!(event.getDamager() instanceof LivingEntity entity)){return;}
        if (!(event.getEntity() instanceof Player player)) return;
        if(event.getCause() != EntityDamageEvent.DamageCause.ENTITY_ATTACK){return;}
        PlayerInventory inv = player.getInventory();
        ItemStack helm =inv.getHelmet();
        if(hasSetBonus(player)&&isThisItem(helm)){
            entity.damage(5,player);
        }
    }

}
