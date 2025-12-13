package me.carson.terrariaItems.weaponsFolder.weapons.swordFolder.swords;

import me.carson.terrariaItems.projectilesFolder.projectiles.Leaf;
import me.carson.terrariaItems.weaponsFolder.Weapon;
import me.carson.terrariaItems.weaponsFolder.weapons.swordFolder.Sword;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class BladeOfGrass extends Sword implements Listener {

    private final HashMap<UUID, Long> lastClickTime = new HashMap<>();

    public BladeOfGrass(Plugin plugin) {
        super(plugin,"Blade of Grass","#FFC896",Material.IRON_SWORD,"blade_of_grass","BladeOfGrass",0,1,6,0,15, new ArrayList<>(List.of(ChatColor.GRAY+"Has a chance to poison enemies",ChatColor.GRAY+"6 Damage")));
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new BladeOfGrass(plugin).createItem();
        ItemMeta meta= item.getItemMeta();
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE,new AttributeModifier(new NamespacedKey(plugin,"attack"),5, AttributeModifier.Operation.ADD_NUMBER));
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public void leftActivate(Player player) {
        player.getWorld().playSound(player.getLocation(), "terraria:sword_use", 1.0F, 1.0F);

        long currentTime = System.currentTimeMillis();
        long lastTime = lastClickTime.getOrDefault(player.getUniqueId(), 0L);

        if (currentTime - lastTime < 750) {
            return;
        }
        lastClickTime.put(player.getUniqueId(), currentTime);

        new Leaf(plugin).createProjectile(player,speed,damage,spread,duration);
    }

    @Override
    public void rightActivate(Player player) {

    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player player)) return;
        ItemStack item=player.getInventory().getItemInMainHand();
        if(BladeOfGrass.this.isThisItem(item)){
            if(event.getEntity() instanceof LivingEntity livingEntity){
                if(Math.random()<0.25){
                    livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.POISON,140,2,false,true,true));
                }
            }
        }

    }
}
