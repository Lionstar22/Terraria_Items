package me.carson.terrariaItems.armourFolder.armors.shadowArmor;

import me.carson.terrariaItems.armourFolder.Armor;
import me.carson.terrariaItems.armourFolder.armors.forbiddenArmor.ForbiddenMask;
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

public class ShadowHelmet extends Armor {

    public ShadowHelmet(Plugin plugin){
        super(plugin,"Shadow Helmet","#9696FF", Material.DIAMOND_HELMET,"shadow_helmet","shadow_armor", EquipmentSlot.HEAD,"ShadowHelmet",new ArrayList<>(List.of(ChatColor.GRAY+"5% increased critical strike chance",ChatColor.GRAY+"5% Increased movement speed",ChatColor.GRAY+"+3 Armor")));
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item= new ShadowHelmet(plugin).createItem();
        ItemMeta meta=item.getItemMeta();
        EquippableComponent equip= meta.getEquippable();
        equip.setModel(null);
        meta.setEquippable(equip);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public void activateArmorEffect(Player player) {
        playerInstance.addCritChance(player.getUniqueId(),0.05);
        player.setWalkSpeed(player.getWalkSpeed()+0.01f);
    }

    @Override
    public void deactivateArmorEffect(Player player) {
        playerInstance.subtractCritChance(player.getUniqueId(),0.05);
        player.setWalkSpeed(Math.max(player.getWalkSpeed() - 0.01f, 0.2f));
    }
}
