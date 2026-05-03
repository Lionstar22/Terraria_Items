package me.carson.terrariaItems.throwablesFolder.throwables;

import me.carson.terrariaItems.throwablesFolder.Throwable;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class BouncyGrenadeProjectile extends Throwable {

    public BouncyGrenadeProjectile(Plugin plugin) {
        super(plugin, 0,  "bouncy_grenade", "BouncyGrenadeProjectile",0,0,1f,1f, DamageType.ARROW);
    }

    @Override
    public void hitEntityEffect(ItemDisplay proj,Player player) {
        proj.getWorld().createExplosion(proj.getLocation(), 2F, false, false);
    }

    @Override
    public void hitBlockEffect(Block block) {

    }

    @Override
    public void timerEndEffect(ItemDisplay proj, Player player) {
        proj.getWorld().createExplosion(proj.getLocation(), 2F, false, false);
    }

}
