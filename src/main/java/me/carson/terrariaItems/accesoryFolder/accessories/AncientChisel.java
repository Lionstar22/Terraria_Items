package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class AncientChisel extends Accessory {

    public AncientChisel(Plugin plugin){
        super(plugin,"Ancient Chisel","#9696FF", Material.COPPER_INGOT,"ancient_chisel","AncientChisel",new ArrayList<>(List.of(ChatColor.GRAY+"Increases mining speed",ChatColor.GRAY+"Ancient problems require ancient solutions",ChatColor.GRAY+"Shift Right Click to Activate")));
    }

    @Override
    public void activateEffect(Player player){
        player.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, -1, 1, false, false,false));
    }

    @Override
    public void deactivateEffect(Player player) {
        player.removePotionEffect(PotionEffectType.HASTE);
    }

    public static ItemStack getItem(Plugin plugin) {
        return new AncientChisel(plugin).createItem();
    }

}
