package me.carson.terrariaItems.armorFolder.armors.jungleArmor;

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

public class JungleHat extends Armor {

    public JungleHat(Plugin plugin){
        super(plugin,"jungle_hat.name","#FFC896", Material.IRON_HELMET,"jungle_hat","jungle_armor",EquipmentSlot.HEAD,"JungleHat","jungle_hat.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item= new JungleHat(plugin).createItem();
        ItemMeta meta=item.getItemMeta();
        EquippableComponent equip= meta.getEquippable();
        equip.setModel(null);
        meta.setEquippable(equip);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public void activateArmorEffect(Player player) {
        playerInstance.addExtraMana(player.getUniqueId(),40);
        playerInstance.addCritChance(player.getUniqueId(),0.06);
    }

    @Override
    public void deactivateArmorEffect(Player player) {
        playerInstance.subtractExtraMana(player.getUniqueId(),40);
        playerInstance.subtractCritChance(player.getUniqueId(),0.06);
    }
}
