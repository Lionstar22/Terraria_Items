package me.carson.terrariaItems.armourFolder.armors.forbiddenArmor;

import me.carson.terrariaItems.armourFolder.Armor;
import me.carson.terrariaItems.armourFolder.armors.jungleArmor.JungleHat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.EquippableComponent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class ForbiddenMask extends Armor {

    public ForbiddenMask(Plugin plugin){
        super(plugin,"Forbidden Mask","#FF96FF", Material.NETHERITE_HELMET,"forbidden_mask","forbidden_armor", EquipmentSlot.HEAD,"ForbiddenMask",new ArrayList<>(List.of(ChatColor.GRAY+"15% increased magic damage",ChatColor.GRAY+"+3 Armor")));
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item= new ForbiddenMask(plugin).createItem();
        ItemMeta meta=item.getItemMeta();
        meta.addEnchant(Enchantment.PROTECTION,2,false);
        meta.setEnchantmentGlintOverride(false);
        EquippableComponent equip= meta.getEquippable();
        equip.setModel(null);
        meta.setEquippable(equip);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public void activateArmorEffect(Player player) {
        playerInstance.addBonusMagic(player.getUniqueId(),0.15);
    }

    @Override
    public void deactivateArmorEffect(Player player) {
        playerInstance.subtractBonusMagic(player.getUniqueId(),0.15);
    }

}
