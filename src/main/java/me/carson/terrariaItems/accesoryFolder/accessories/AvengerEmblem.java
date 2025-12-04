package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class AvengerEmblem extends Accessory implements Listener {

    public AvengerEmblem(Plugin plugin){
        super(plugin,"Avenger Emblem","#FF96FF", Material.GOLD_INGOT,"avenger_emblem","AvengerEmblem",new ArrayList<>(List.of(ChatColor.GRAY+"15% increased damage",ChatColor.GRAY+"Shift Right Click to Activate")));
    }

    @Override
    public void activateEffect(Player player) {

    }

    @Override
    public void deactivateEffect(Player player) {

    }

    public static ItemStack getItem(Plugin plugin) {
        return new AvengerEmblem(plugin).createItem();
    }

    @EventHandler
    public void onMeleeDamage(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player player)) return;

        for (ItemStack itemInv : player.getInventory().getContents()) {
            if (AvengerEmblem.this.isThisItem(itemInv)) {
                if (AvengerEmblem.this.isActivated(itemInv)){
                    double boostedDamage = event.getDamage() * 1.15;
                    event.setDamage(boostedDamage);
                    break;
                }
            }
        }
    }

}
