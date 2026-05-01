package me.carson.terrariaItems.throwablesFolder.throwables;

import me.carson.terrariaItems.throwablesFolder.Throwable;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class GrenadeObj extends Throwable {

    public GrenadeObj(Plugin plugin) {
        super(plugin, 0,  "grenade", "GrenadeObj",0,0,0.5f, DamageType.ARROW);
    }

    @Override
    public void hitEntityEffect(ItemDisplay proj,Player player) {
        proj.getWorld().createExplosion(proj.getLocation(), 2.0F, false, false);
    }

    @Override
    public void hitBlockEffect(Block block) {

    }

}
