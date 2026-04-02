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
            EntityDamageEvent.DamageCause.FIRE_TICK,
            EntityDamageEvent.DamageCause.CAMPFIRE,
            EntityDamageEvent.DamageCause.FIRE
    );

    public MoltenHelmet(Plugin plugin){
        super(plugin,"Molten Helmet","#FFC896", Material.NETHERITE_HELMET,"molten_helmet","molten_armor", EquipmentSlot.HEAD,"MoltenHelmet",new ArrayList<>(List.of(ChatColor.GRAY+"7% increased critical strike chance",ChatColor.GRAY+"Set Bonus: Grants immunity to fire",ChatColor.GRAY+"+3 Armor")));
    }

    @Override
    public void activateArmorEffect(Player player) {
        playerInstance.addCritChance(player.getUniqueId(),0.07);
    }

    @Override
    public void deactivateArmorEffect(Player player) {
        playerInstance.subtractCritChance(player.getUniqueId(),0.07);
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
