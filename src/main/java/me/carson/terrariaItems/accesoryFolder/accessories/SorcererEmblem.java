package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class SorcererEmblem extends Accessory implements Listener {

    public SorcererEmblem(Plugin plugin){
        super(plugin,"Sorcerer Emblem","#FF9696", Material.GOLD_INGOT,"sorcerer_emblem","SorcererEmblem",new ArrayList<>(List.of(ChatColor.GRAY+"20% increased magic damage",ChatColor.GRAY+"Shift Right Click to Activate")));
    }

    @Override
    public void activateEffect(Player player) {

    }

    @Override
    public void deactivateEffect(Player player) {

    }

    public static ItemStack getItem(Plugin plugin) {
        return new SorcererEmblem(plugin).createItem();
    }

    @EventHandler
    public void onMobDeath(EntityDeathEvent e) {
        LivingEntity entity = e.getEntity();
        if (entity.getType() != EntityType.EVOKER){return;}

        if(Math.random()<0.1){
            ItemStack custom = SorcererEmblem.getItem(plugin);
            e.getDrops().add(custom);
        }
    }

    @EventHandler
    public void onMagicDamage(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player player)) return;
        DamageType type = event.getDamageSource().getDamageType();
        if (type != DamageType.MAGIC) return;

        for (ItemStack itemInv : player.getInventory().getContents()) {
            if (SorcererEmblem.this.isThisItem(itemInv)) {
                if (SorcererEmblem.this.isActivated(itemInv)){
                    double boostedDamage = event.getDamage() * 1.2;
                    event.setDamage(boostedDamage);
                    break;
                }
            }
        }
    }

}
