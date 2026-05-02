package me.carson.terrariaItems.throwablesFolder.throwables;

import me.carson.terrariaItems.throwablesFolder.Throwable;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class BouncyGrenadeObj extends Throwable {

    public BouncyGrenadeObj(Plugin plugin) {
        super(plugin, 0,  "bouncy_grenade", "BouncyGrenadeObj",0,0,1f, DamageType.ARROW);
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
