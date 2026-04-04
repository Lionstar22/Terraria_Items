package me.carson.terrariaItems.weaponsFolder.weapons.magicFolder.magicWeapons;

import me.carson.terrariaItems.listenersHandler.ManaManager;
import me.carson.terrariaItems.projectilesFolder.projectiles.WaterBoltProjectile;
import me.carson.terrariaItems.weaponsFolder.weapons.magicFolder.Magic;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WaterBolt extends Magic {

    public WaterBolt(Plugin plugin) {
        super(plugin,"Water Bolt","#96FF96", Material.BOOK,"water_bolt","WaterBolt",10,0.5f,5,0,50,10, new ArrayList<>(List.of(ChatColor.GRAY+"Casts a slow moving bolt of water",ChatColor.GRAY+"Costs 10 Mana",ChatColor.GRAY+"5 Damage")));
    }

    @Override
    public void leftActivate(Player player) {

    }

    @Override
    public void rightActivate(Player player) {
        if(!isThisItem(player.getInventory().getItemInMainHand())){return;}
        UUID id=player.getUniqueId();
        if(manaManagerInstance.getMana(id)<cost){return;}
        new WaterBoltProjectile(plugin).createProjectile(player,speed,damage,spread,duration);
        player.getWorld().playSound(player.getLocation(), "terraria:water_bolt_use", 1.0F, 1.0F);
        manaManagerInstance.removeMana(id, cost);
        manaManagerInstance.updateManaBar(player);
        manaManagerInstance.startManaRegenDelay(player,manaManagerInstance);
    }

    public static ItemStack getItem(Plugin plugin) {
        return new WaterBolt(plugin).createItem();
    }

}
