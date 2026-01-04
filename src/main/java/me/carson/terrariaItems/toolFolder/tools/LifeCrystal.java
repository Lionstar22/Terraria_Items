package me.carson.terrariaItems.toolFolder.tools;

import me.carson.terrariaItems.toolFolder.Tool;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class LifeCrystal extends Tool {

    public LifeCrystal(Plugin plugin){
        super(plugin,"Life Crystal","#96FF96", Material.PINK_DYE,"life_crystal","LifeCrystal",10,new ArrayList<>(List.of(ChatColor.GRAY+"Permanently increases maximum life")));
    }

    @Override
    public void rightActivate(Player player) {
        if(!(player.getMaxHealth()>=40)){
            double newHealth=player.getMaxHealth()+2;
            player.setMaxHealth(newHealth);
            player.heal(2);
            player.playSound(player.getLocation(), "terraria:life_crystal_use", 0.5F, 1.0F);
            player.getInventory().removeItem(LifeCrystal.getItem(plugin));
        }
    }

    @Override
    public void cooldownEffect(Player player) {

    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item =new LifeCrystal(plugin).createItem();
        ItemMeta meta= item.getItemMeta();
        meta.setMaxStackSize(64);
        item.setItemMeta(meta);
        return item;
    }
}
