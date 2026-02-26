package me.carson.terrariaItems.weaponsFolder.weapons.magicFolder.magicWeapons;

import me.carson.terrariaItems.listenersHandler.ManaManager;
import me.carson.terrariaItems.projectilesFolder.projectiles.CrystalStormProjectile;
import me.carson.terrariaItems.weaponsFolder.weapons.magicFolder.Magic;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrystalStorm extends Magic {

    public CrystalStorm(Plugin plugin) {
        super(plugin,"Crystal Storm","#FF9696", Material.BOOK,"crystal_storm","CrystalStorm",0,1.5f,6,0.15f,50,5, new ArrayList<>(List.of(ChatColor.GRAY+"Summons rapid fire crystal shards",ChatColor.GRAY+"Costs 5 Mana",ChatColor.GRAY+"6 Damage")));
    }

    @Override
    public void rightActivate(Player player) {
        if(!isThisItem(player.getInventory().getItemInMainHand())){return;}
        UUID id=player.getUniqueId();
        ManaManager instance = ManaManager.getInstance();
        if(instance.getMana(id)<cost){return;}
        new CrystalStormProjectile(plugin).createProjectile(player,speed,damage,spread,duration);
        player.getWorld().playSound(player.getLocation(), "terraria:falling_star", 0.75F, 1.0F);
        instance.removeMana(id, cost);
        instance.updateManaBar(player);
        instance.startManaRegenDelay(player,instance);
    }

    public static ItemStack getItem(Plugin plugin) {
        return new CrystalStorm(plugin).createItem();
    }
}
