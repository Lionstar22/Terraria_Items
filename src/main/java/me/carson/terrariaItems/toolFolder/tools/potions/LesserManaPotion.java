package me.carson.terrariaItems.toolFolder.tools.potions;

import me.carson.terrariaItems.accesoryFolder.accessories.ManaFlower;
import me.carson.terrariaItems.toolFolder.Tool;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LesserManaPotion extends Tool {

    public LesserManaPotion(Plugin plugin){
        super(plugin,"lesser_mana_potion.name","#FFFFFF", Material.PAPER,"lesser_mana_potion","LesserManaPotion",20,"lesser_mana_potion.lore");
    }

    @Override
    public void rightActivate(Player player) {
        UUID id=player.getUniqueId();
        manaManagerInstance.addMana(id, 50);
        manaManagerInstance.updateManaBar(player);
        player.getInventory().removeItem(getItem(plugin));
        player.getWorld().playSound(player.getLocation(), "terraria:potion_drink", 0.75f, 1f);
    }

    @Override
    public void cooldownEffect(Player player) {

    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item =new LesserManaPotion(plugin).createItem();
        ItemMeta meta= item.getItemMeta();
        NamespacedKey key = new NamespacedKey(plugin, "mana_potion");
        meta.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte) 1);
        meta.setMaxStackSize(99);
        item.setItemMeta(meta);
        return item;
    }

}
