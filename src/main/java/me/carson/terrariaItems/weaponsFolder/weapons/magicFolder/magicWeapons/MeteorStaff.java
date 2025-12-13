package me.carson.terrariaItems.weaponsFolder.weapons.magicFolder.magicWeapons;

import me.carson.terrariaItems.ManaManager;
import me.carson.terrariaItems.projectilesFolder.projectiles.Meteor;
import me.carson.terrariaItems.weaponsFolder.weapons.magicFolder.Magic;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.RayTraceResult;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MeteorStaff extends Magic {

    public MeteorStaff(Plugin plugin) {
        super(plugin,"Meteor Staff","#FF96FF", Material.NETHER_BRICK,"meteor_staff","MeteorStaff",0,1.5f,10,3,50,9, new ArrayList<>(List.of(ChatColor.GRAY+"Showers meteors",ChatColor.GRAY+"Costs 9 Mana",ChatColor.GRAY+"10 Damage")));
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

        RayTraceResult result= player.getWorld().rayTrace(player.getEyeLocation(),player.getEyeLocation().getDirection(),150,FluidCollisionMode.NEVER,true,0.1,e -> (e!=player));

        if (result == null) {return;}

        Location hit=result.getHitPosition().toLocation(player.getWorld());

        new Meteor(plugin).createFallingProjectile(player,speed,damage,spread,duration,25,hit);
        player.getWorld().playSound(player.getLocation(), "terraria:meteor_staff_use", 0.75F, 1.0F);
    }

    public static ItemStack getItem(Plugin plugin) {
        return new MeteorStaff(plugin).createItem();
    }

}
