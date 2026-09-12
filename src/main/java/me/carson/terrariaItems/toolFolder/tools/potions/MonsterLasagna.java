package me.carson.terrariaItems.toolFolder.tools.potions;

import me.carson.terrariaItems.toolFolder.Tool;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class MonsterLasagna extends Tool {

    public MonsterLasagna(Plugin plugin){
        super(plugin,"monster_lasagna.name","#96FF96", Material.BREWER_POTTERY_SHERD,"monster_lasagna","MonsterLasagna",20,"monster_lasagna.lore");
    }

    @Override
    public void rightActivate(Player player) {
        customPotionInstance.addCustomFoodEffect(player,9600,"Plenty_Satisfied");
        player.getInventory().removeItem(getItem(plugin));
        player.setFoodLevel(player.getFoodLevel()+10);
        player.getWorld().playSound(player.getLocation(), "terraria:food_eat", 0.75f, 1f);
    }

    @Override
    public void cooldownEffect(Player player) {

    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item =new MonsterLasagna(plugin).createItem();
        ItemMeta meta= item.getItemMeta();
        meta.setMaxStackSize(64);
        item.setItemMeta(meta);
        return item;
    }

}
