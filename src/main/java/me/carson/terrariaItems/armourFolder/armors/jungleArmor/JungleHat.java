package me.carson.terrariaItems.armourFolder.armors.jungleArmor;

import me.carson.terrariaItems.armourFolder.Armor;
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
        super(plugin,"Jungle Hat","#FFC896", Material.IRON_HELMET,"jungle_hat","jungle_armor",EquipmentSlot.HEAD,"JungleHat",new ArrayList<>(List.of(ChatColor.GRAY+"Increases maximum mana by 40",ChatColor.GRAY+"6% increased critical strike chance")));
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
