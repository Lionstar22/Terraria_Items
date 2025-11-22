package me.carson.terrariaItems.armourFolder.armors.moltenArmor;

import me.carson.terrariaItems.armourFolder.Armor;
import me.carson.terrariaItems.armourFolder.ArmorManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MoltenHelmet extends Armor implements Listener {

    private static final Set<EntityDamageEvent.DamageCause> COUNTERABLE_CAUSES = Set.of(
            EntityDamageEvent.DamageCause.LAVA,
            EntityDamageEvent.DamageCause.FIRE_TICK,
            EntityDamageEvent.DamageCause.CAMPFIRE,
            EntityDamageEvent.DamageCause.FIRE
    );

    public MoltenHelmet(Plugin plugin){
        super(plugin,"Molten Helmet","#FFC896", Material.NETHERITE_HELMET,"molten_helmet","molten_armor", EquipmentSlot.HEAD,"MoltenHelmet",new ArrayList<>(List.of(ChatColor.GRAY+"Set Bonus: Fire Resistance, 25% Increased Damage, Sets Enemies on Fire")));
    }

    @Override
    public void activateArmorEffect(Player player) {
    }

    @Override
    public void deactivateArmorEffect(Player player) {
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player player)) return;
        PlayerInventory inv = player.getInventory();
        ItemStack helm =inv.getHelmet();
        if(hasSetBonus(player)&&isThisItem(helm)){
            double baseDamage = event.getDamage();
            double boostedDamage = baseDamage * 1.25;
            event.getEntity().setFireTicks(100);
            event.setDamage(boostedDamage);
        }
    }
    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        PlayerInventory inv = player.getInventory();
        ItemStack helm =inv.getHelmet();
        if(COUNTERABLE_CAUSES.contains(event.getCause())){
            if (hasSetBonus(player)&&isThisItem(helm)){
                event.setCancelled(true);
            }
        }
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack helm=new MoltenHelmet(plugin).createItem();
        ItemMeta meta=helm.getItemMeta();
        meta.addEnchant(Enchantment.PROTECTION,1,false);
        meta.setEnchantmentGlintOverride(false);
        helm.setItemMeta(meta);
        return helm;
    }
}
