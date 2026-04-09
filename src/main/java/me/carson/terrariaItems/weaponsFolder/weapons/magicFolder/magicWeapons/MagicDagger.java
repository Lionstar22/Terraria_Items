package me.carson.terrariaItems.weaponsFolder.weapons.magicFolder.magicWeapons;

import me.carson.terrariaItems.projectilesFolder.projectiles.AmethystBolt;
import me.carson.terrariaItems.projectilesFolder.projectiles.MagicDaggerProjectile;
import me.carson.terrariaItems.weaponsFolder.weapons.magicFolder.Magic;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MagicDagger extends Magic {

    public MagicDagger(Plugin plugin) {
        super(plugin,"Magic Dagger","#FF9696", Material.BLAZE_ROD,"magic_dagger","MagicDagger",4,1.75f,9,0.005f,25,6, new ArrayList<>(List.of(ChatColor.GRAY+"A magical returning dagger",ChatColor.GRAY+"Costs 6 Mana",ChatColor.GRAY+"9 Damage")));
    }

    @Override
    public void leftActivate(Player player) {

    }

    @Override
    public void rightActivate(Player player) {
        if(!isThisItem(player.getInventory().getItemInMainHand())){return;}
        UUID id=player.getUniqueId();
        if(manaManagerInstance.getMana(id)<cost){return;}
        new MagicDaggerProjectile(plugin).createProjectile(player,speed,damage,spread,duration);
        player.getWorld().playSound(player.getLocation(), "terraria:sword_use", 0.75F, 1.0F);
        manaManagerInstance.removeMana(id, cost);
        manaManagerInstance.updateManaBar(player);
        manaManagerInstance.startManaRegenDelay(player,manaManagerInstance);
    }

    public static ItemStack getItem(Plugin plugin) {
        return new MagicDagger(plugin).createItem();
    }

}
