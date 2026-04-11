package me.carson.terrariaItems.armorFolder.armors.timArmor;

import me.carson.terrariaItems.armorFolder.Armor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.EquippableComponent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class WizardHat extends Armor {

    public WizardHat(Plugin plugin){
        super(plugin,"wizard_hat.name","#96FF96", Material.LEATHER_HELMET,"wizard_hat","tim_armor",EquipmentSlot.HEAD,"WizardHat","wizard_hat.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item= new WizardHat(plugin).createItem();
        ItemMeta meta=item.getItemMeta();
        EquippableComponent equip= meta.getEquippable();
        equip.setModel(null);
        meta.setEquippable(equip);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public void activateArmorEffect(Player player) {
        playerInstance.addBonusMagic(player.getUniqueId(),0.05);
    }

    @Override
    public void deactivateArmorEffect(Player player) {
        playerInstance.subtractBonusMagic(player.getUniqueId(),0.05);
    }
}
