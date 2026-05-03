package me.carson.terrariaItems.throwablesFolder.throwables;

import me.carson.terrariaItems.throwablesFolder.Throwable;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class SpikyBallProjectile extends Throwable {

    public SpikyBallProjectile(Plugin plugin) {
        super(plugin, 0,  "spiky_ball", "SpikyBallProjectile",7,0,0.5f,0.999f, DamageType.ARROW);
    }

    @Override
    public void hitEntityEffect(ItemDisplay proj,Player player) {

    }

    @Override
    public void hitBlockEffect(Block block) {

    }

    @Override
    public void timerEndEffect(ItemDisplay proj, Player player) {

    }

}
