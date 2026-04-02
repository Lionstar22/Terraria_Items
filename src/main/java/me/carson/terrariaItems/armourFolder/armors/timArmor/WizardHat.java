package me.carson.terrariaItems.armourFolder.armors.timArmor;

import me.carson.terrariaItems.armourFolder.Armor;
import me.carson.terrariaItems.armourFolder.armors.necroArmor.NecroHelmet;
import me.carson.terrariaItems.miscFolder.Basic;
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
        super(plugin,"Wizard Hat","#96FF96", Material.LEATHER_HELMET,"wizard_hat","tim_armor",EquipmentSlot.HEAD,"WizardHat",new ArrayList<>(List.of(ChatColor.GRAY+"5% increased magic damage",ChatColor.GRAY+"+1 Armor")));
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
