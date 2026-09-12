package me.carson.terrariaItems.toolFolder.tools.potions;

import me.carson.terrariaItems.toolFolder.Tool;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class FruitSalad extends Tool {

    public FruitSalad(Plugin plugin){
        super(plugin,"fruit_salad.name","#96FF96", Material.BREWER_POTTERY_SHERD,"fruit_salad","FruitSalad",20,"fruit_salad.lore");
    }

    @Override
    public void rightActivate(Player player) {
        customPotionInstance.addCustomFoodEffect(player,30000,"Well_Fed");
        player.getInventory().removeItem(getItem(plugin));
        player.setFoodLevel(player.getFoodLevel()+6);
        player.getWorld().playSound(player.getLocation(), "terraria:food_eat", 0.75f, 1f);
    }

    @Override
    public void cooldownEffect(Player player) {

    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item =new FruitSalad(plugin).createItem();
        ItemMeta meta= item.getItemMeta();
        meta.setMaxStackSize(64);
        item.setItemMeta(meta);
        return item;
    }

}
