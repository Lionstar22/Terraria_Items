package me.carson.terrariaItems.weaponsFolder.weapons.swordFolder.swords;

import me.carson.terrariaItems.projectilesFolder.projectiles.IceBolt;
import me.carson.terrariaItems.projectilesFolder.projectiles.Icicle;
import me.carson.terrariaItems.projectilesFolder.projectiles.StarCannonStar;
import me.carson.terrariaItems.weaponsFolder.weapons.swordFolder.Sword;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.RayTraceResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Starfury extends Sword {

    private final HashMap<UUID, Long> lastClickTime = new HashMap<>();

    public Starfury(Plugin plugin) {
        super(plugin,"Starfury","#96FF96", Material.IRON_SWORD,"starfury","Starfury",0,2,8,5,20, new ArrayList<>(List.of(ChatColor.GRAY+"Causes stars to rain from the sky",ChatColor.GRAY+"Forged with the fury of heaven",ChatColor.GRAY+"8 Damage")));
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new Starfury(plugin).createItem();
        ItemMeta meta= item.getItemMeta();
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE,new AttributeModifier(new NamespacedKey(plugin,"attack"),7, AttributeModifier.Operation.ADD_NUMBER));
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

        RayTraceResult result= player.getWorld().rayTrace(player.getEyeLocation(),player.getEyeLocation().getDirection(),150, FluidCollisionMode.NEVER,true,0.1, e -> (e!=player));

        if (result == null) {return;}

        Location hit=result.getHitPosition().toLocation(player.getWorld());

        new StarCannonStar(plugin).createFallingProjectile(player,speed,damage,spread,duration,25,hit);
        player.getWorld().playSound(result.getHitPosition().toLocation(hit.getWorld()).add(0,25,0), "terraria:falling_star", 1.0F, 1.0F);
    }

    @Override
    public void rightActivate(Player player) {

    }

}
