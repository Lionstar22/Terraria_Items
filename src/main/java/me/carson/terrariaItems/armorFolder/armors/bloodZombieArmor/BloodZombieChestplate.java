package me.carson.terrariaItems.armorFolder.armors.bloodZombieArmor;

import me.carson.terrariaItems.armorFolder.Armor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class BloodZombieChestplate extends Armor {

    public BloodZombieChestplate(Plugin plugin){
        super(plugin,"unobtainable.name","#FFFFFF", Material.LEATHER_CHESTPLATE,"cactus_chestplate","blood_zombie_armor", EquipmentSlot.CHEST,"BloodZombieChestplate","unobtainable.lore");
    }

    @Override
    public void activateArmorEffect(Player player) {
    }

    @Override
    public void deactivateArmorEffect(Player player) {
    }

    public static ItemStack getItem(Plugin plugin) {
        return new BloodZombieChestplate(plugin).createItem();
    }

}
