package me.carson.terrariaItems.toolFolder.tools;

import me.carson.terrariaItems.ManaManager;
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

public class ManaCrystal extends Tool {

    public ManaCrystal(Plugin plugin) {
        super(plugin,"Mana Crystal","#96FF96", Material.GOLD_NUGGET,"mana_crystal","ManaCrystal",10, new ArrayList<>(List.of(ChatColor.GRAY+"Permanently increases maximum mana by 20",ChatColor.GRAY+"200 Mana Max")));
    }

    @Override
    public void rightActivate(Player player) {
        ManaManager instance = ManaManager.getInstance();
        UUID id =player.getUniqueId();
        if(instance.getMaxMana(id)<200.00){
            instance.setMaxMana(id,instance.getMaxMana(id)+20);
            player.getInventory().removeItem(ManaCrystal.getItem(plugin));
            instance.updateManaBar(player);
        }
        player.getWorld().playSound(player.getLocation(), "terraria:mana_crystal_use", 1.0F, 1.0F);
    }

    @Override
    public void cooldownEffect(Player player) {

    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item =new ManaCrystal(plugin).createItem();
        ItemMeta meta= item.getItemMeta();
        meta.setMaxStackSize(64);
        item.setItemMeta(meta);
        return item;
    }
}
