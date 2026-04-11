package me.carson.terrariaItems.toolFolder.tools.potions;

import me.carson.terrariaItems.toolFolder.Tool;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SuperManaPotion extends Tool {

    public SuperManaPotion(Plugin plugin){
        super(plugin,"super_mana_potion.name","#FF9696", Material.PAPER,"super_mana_potion","SuperManaPotion",20,"super_mana_potion.lore");
    }

    @Override
    public void rightActivate(Player player) {
        UUID id=player.getUniqueId();
        manaManagerInstance.addMana(id, 300);
        manaManagerInstance.updateManaBar(player);
        player.getInventory().removeItem(getItem(plugin));
        player.getWorld().playSound(player.getLocation(), "terraria:potion_drink", 0.75f, 1f);
    }

    @Override
    public void cooldownEffect(Player player) {

    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item =new SuperManaPotion(plugin).createItem();
        ItemMeta meta= item.getItemMeta();
        meta.setMaxStackSize(99);
        item.setItemMeta(meta);
        return item;
    }

}
