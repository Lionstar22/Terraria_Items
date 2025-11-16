package me.carson.terrariaItems.armourFolder.armors.moltenArmor;

import me.carson.terrariaItems.armourFolder.Armor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MoltenHelmet extends Armor {

    public Boolean active;

    private static final Set<EntityDamageEvent.DamageCause> COUNTERABLE_CAUSES = Set.of(
            EntityDamageEvent.DamageCause.LAVA,
            EntityDamageEvent.DamageCause.FIRE_TICK,
            EntityDamageEvent.DamageCause.CAMPFIRE,
            EntityDamageEvent.DamageCause.FIRE
    );

    public MoltenHelmet(Plugin plugin){
        super(plugin,"Molten Helmet","#FFC896", Material.NETHERITE_HELMET,"molten_helmet","molten_armor", EquipmentSlot.HEAD,"MoltenHelmet",new ArrayList<>(List.of(ChatColor.GRAY+"Set Bonus: Fire Resistance, 25% Increased Melee Damage, Fire Ascpect")));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new MoltenHelmet(plugin).createItem();
    }

    @Override
    public void activateArmorEffect(Player player) {
        active=true;
    }

    @Override
    public void deactivateArmorEffect(Player player) {
        active=false;
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player player)) return;
        if(active){
            double baseDamage = event.getDamage();
            double boostedDamage = baseDamage * 1.25;
            event.getEntity().setFireTicks(100);
            event.setDamage(boostedDamage);
        }
    }
    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if(active){
            if (COUNTERABLE_CAUSES.contains(event.getCause())){
                event.setCancelled(true);
            }
        }
    }
}
