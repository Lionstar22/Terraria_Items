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

public class Vitamins extends Accessory implements Listener {

    public Vitamins(Plugin plugin) {
        super(plugin, "Vitamins", "#FF9696", Material.SHORT_GRASS, "vitamins", "Vitamins", new ArrayList<>(List.of(ChatColor.GRAY + "Immunity to Weakness", ChatColor.GRAY + "Must be in accessory inventory")));
    }

    @Override
    public void activateEffect(Player player) {

    }

    @Override
    public void deactivateEffect(Player player) {

    }

    @EventHandler
    private void onSlow(EntityPotionEffectEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        PotionEffect newEffect = event.getNewEffect();
        if (newEffect == null) return;

        if (newEffect.getType() == PotionEffectType.WEAKNESS) {
            if (hasItem(player)) {
                event.setCancelled(true);
            }
        }
    }

    public static ItemStack getItem(Plugin plugin) {
        return new Vitamins(plugin).createItem();
    }
}
