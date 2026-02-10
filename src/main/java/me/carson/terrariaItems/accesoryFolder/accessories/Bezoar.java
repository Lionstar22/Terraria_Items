package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class Bezoar extends Accessory implements Listener {

    public Bezoar(Plugin plugin){
        super(plugin,"Bezoar","#FF9696", Material.MOSS_BLOCK,"bezoar","Bezoar",new ArrayList<>(List.of(ChatColor.GRAY+"Immunity to Poison",ChatColor.GRAY+"Must be in accessory inventory")));
    }

    @Override
    public void activateEffect(Player player) {

    }

    @Override
    public void deactivateEffect(Player player) {

    }

    public static ItemStack getItem(Plugin plugin) {
        return new Bezoar(plugin).createItem();
    }

    @EventHandler
    private void onPoison(EntityPotionEffectEvent event){
        if (!(event.getEntity() instanceof Player player)) return;

        PotionEffect newEffect = event.getNewEffect();
        if (newEffect == null) return;

        if (newEffect.getType() == PotionEffectType.POISON) {
            if(hasItem(player)){
                event.setCancelled(true);
            }
        }
    }
}
