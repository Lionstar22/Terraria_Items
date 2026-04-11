package me.carson.terrariaItems.armorFolder.armors.necroArmor;

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

public class NecroHelmet extends Armor {

    public NecroHelmet(Plugin plugin){
        super(plugin,"necro_helmet.name","#96FF96", Material.DIAMOND_HELMET,"necro_helmet","necro_armor",EquipmentSlot.HEAD,"NecroHelmet","necro_helmet.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item= new NecroHelmet(plugin).createItem();
        ItemMeta meta=item.getItemMeta();
        EquippableComponent equip= meta.getEquippable();
        equip.setModel(null);
        meta.setEquippable(equip);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public void activateArmorEffect(Player player) {
        playerInstance.addBonusRanged(player.getUniqueId(),0.05);
    }

    @Override
    public void deactivateArmorEffect(Player player) {
        playerInstance.subtractBonusRanged(player.getUniqueId(),0.05);
    }
}
