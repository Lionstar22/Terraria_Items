package me.carson.terrariaItems.weaponsFolder.weapons;

import me.carson.terrariaItems.weaponsFolder.Weapon;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class Volcano extends Weapon implements Listener {

    public Volcano(Plugin plugin) {
        super(plugin,"Volcano","#FFC896", Material.NETHERITE_SWORD,"volcano","Volcano",0, new ArrayList<>(List.of(ChatColor.GRAY+"It's made out of fire!")));
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new Volcano(plugin).createItem();
        ItemMeta meta= item.getItemMeta();
        //meta.setAttributeModifiers(Attribute.ENTITY_INTERACTION_RANGE);
        return item;
    }

    @Override
    public void leftActivate(Player player) {
        /*Location loc = player.getEyeLocation();
        Vector dir = loc.getDirection().normalize();
        loc.add(dir.multiply(0.2));

        RayTraceResult result = player.getWorld().rayTraceEntities(
                loc,
                dir,
                6
        );
        if(result==null){return;}
        LivingEntity target= (LivingEntity) result.getHitEntity();

        if (target != null) {
            target.damage(20,player);
            target.setFireTicks(60);
        }*/
    }

    @Override
    public void rightActivate(Player player) {

    }
    /*
    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player player)) return;

        ItemStack held = player.getInventory().getItemInMainHand();
        if (!isThisItem(held)) return;

        event.setCancelled(true);
    }*/

}
