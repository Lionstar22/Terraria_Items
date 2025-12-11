package me.carson.terrariaItems.weaponsFolder.weapons.magicFolder.magicWeapons;

import me.carson.terrariaItems.ManaManager;
import me.carson.terrariaItems.projectilesFolder.projectiles.AmethystBolt;
import me.carson.terrariaItems.projectilesFolder.projectiles.RubyBolt;
import me.carson.terrariaItems.weaponsFolder.weapons.magicFolder.Magic;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MeteorStaff extends Magic {

    public MeteorStaff(Plugin plugin) {
        super(plugin,"Meteor Staff","#FF96FF", Material.NETHER_BRICK,"meteor_staff","MeteorStaff",10,1,4,0,50,9, new ArrayList<>(List.of(ChatColor.GRAY+"Showers meteors",ChatColor.GRAY+"Costs 9 Mana")));
    }

    @Override
    public void leftActivate(Player player) {

    }

    @Override
    public void rightActivate(Player player) {
        if(!isThisItem(player.getInventory().getItemInMainHand())){return;}
        UUID id=player.getUniqueId();
        ManaManager instance = ManaManager.getInstance();
        if(instance.getMana(id)<cost){return;}
        instance.removeMana(id, cost);
        instance.updateManaBar(player);
        instance.startManaRegenDelay(player,instance);

        World world=player.getWorld();
        RayTraceResult result = world.rayTraceBlocks(
                player.getEyeLocation(),
                player.getEyeLocation().getDirection(),
                150,
                FluidCollisionMode.NEVER,
                true
        );

        if (result == null || result.getHitBlock() == null) {
            return;
        }

        Location hit=result.getHitBlock().getLocation();

        new RubyBolt(plugin).createFallingProjectile(player,speed,damage,spread,duration,hit);
        player.getWorld().playSound(player.getLocation(), "terraria:magic_use", 1.0F, 1.0F);
    }

    public static ItemStack getItem(Plugin plugin) {
        return new MeteorStaff(plugin).createItem();
    }

}
