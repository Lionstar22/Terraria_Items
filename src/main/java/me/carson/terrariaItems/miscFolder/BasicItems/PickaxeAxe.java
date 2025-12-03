package me.carson.terrariaItems.miscFolder.BasicItems;

import me.carson.terrariaItems.miscFolder.Basic;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.ToolComponent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class PickaxeAxe extends Basic implements Listener {

    public PickaxeAxe(Plugin plugin) {
        super(plugin,"Pickaxe Axe","#FF9696", Material.NETHERITE_PICKAXE,"pickaxe_axe","PickaxeAxe", new ArrayList<>(List.of(ChatColor.GRAY+"Not to be confused with a hamdrill",ChatColor.GRAY+"+2 Range")));
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item =new PickaxeAxe(plugin).createItem();
        ItemMeta meta=item.getItemMeta();
        ToolComponent tool= meta.getTool();
        tool.addRule(Tag.MINEABLE_AXE, 100.0f, true);
        tool.addRule(Tag.MINEABLE_PICKAXE, 100.0f, true);
        tool.addRule(Tag.MINEABLE_SHOVEL, 100.0f, true);
        tool.addRule(Tag.MINEABLE_HOE, 100.0f, true);
        meta.setTool(tool);
        meta.addEnchant(Enchantment.FORTUNE,2,true);
        meta.setEnchantmentGlintOverride(false);
        meta.addAttributeModifier(Attribute.BLOCK_INTERACTION_RANGE, new AttributeModifier(new NamespacedKey(plugin,"pickaxe_axe_range"),2.0, AttributeModifier.Operation.ADD_NUMBER));
        item.setItemMeta(meta);
        return item;
    }


}
