package me.carson.terrariaItems.miscFolder.BasicItems;

import me.carson.terrariaItems.miscFolder.Basic;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.ToolComponent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class PickaxeAxe extends Basic implements Listener {

    public PickaxeAxe(Plugin plugin) {
        super(plugin,"Pickaxe Axe","#FF9696", Material.NETHERITE_PICKAXE,"pickaxe_axe","PickaxeAxe", new ArrayList<>(List.of(ChatColor.GRAY+"Not to be confused with a hamdrill")));
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item =new PickaxeAxe(plugin).createItem();
        ItemMeta meta=item.getItemMeta();
        ToolComponent tool= meta.getTool();
        tool.addRule(Tag.MINEABLE_AXE, 6.0f, true);
        tool.addRule(Tag.MINEABLE_PICKAXE, 6.0f, true);
        tool.addRule(Tag.MINEABLE_SHOVEL, 6.0f, true);
        tool.addRule(Tag.MINEABLE_HOE, 6.0f, true);
        Bukkit.getLogger().info(""+tool.getRules());
        meta.setTool(tool);
        item.setItemMeta(meta);
        return item;
    }


}
