package me.carson.terrariaItems.toolFolder.tools;

import me.carson.terrariaItems.handlers.ManaManager;
import me.carson.terrariaItems.handlers.PlayerDataHandler;
import me.carson.terrariaItems.toolFolder.Tool;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.UUID;

public class ManaCrystal extends Tool {

    public ManaCrystal(Plugin plugin) {
        super(plugin,"mana_crystal.name","#96FF96", Material.GOLD_NUGGET,"mana_crystal","ManaCrystal",10,"mana_crystal.lore");
    }

    @Override
    public void rightActivate(Player player) {
        PlayerDataHandler playerDataInstance = PlayerDataHandler.getInstance();
        ManaManager manaInstance = ManaManager.getInstance();
        UUID id =player.getUniqueId();
        if(playerDataInstance.getMaxMana(id)<200.00){
            playerDataInstance.setMaxMana(id,playerDataInstance.getMaxMana(id)+20);
            player.getInventory().removeItem(ManaCrystal.getItem(plugin));
            manaInstance.updateManaBar(player);
            playerDataInstance.save();
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
