package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseLootEvent;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class AncientFossil extends Accessory implements Listener {

    public AncientFossil(Plugin plugin){
        super(plugin,"Ancient Fossil","#9696FF", Material.COPPER_INGOT,"ancient_fossil","AncientFossil",new ArrayList<>(List.of(ChatColor.GRAY+"Increases mining speed",ChatColor.GRAY+"Shift Right Click to Activate")));
    }

    @Override
    public void activateEffect(Player player){
        player.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, -1, 1, false, false,false));
    }

    @Override
    public void deactivateEffect(Player player) {
        player.removePotionEffect(PotionEffectType.HASTE);
    }

    @EventHandler
    public void onBrush(BlockDropItemEvent event){
        Block block = event.getBlock();
        if(!(block.getType()==Material.SUSPICIOUS_SAND||block.getType()==Material.SUSPICIOUS_GRAVEL)){return;}
        if(Math.random()<0.25){
            ItemStack item = AncientFossil.getItem(plugin);
            block.getWorld().dropItem(block.getLocation().add(0,1,0), item);
        }
    }

    public static ItemStack getItem(Plugin plugin) {
        return new AncientFossil(plugin).createItem();
    }



}
