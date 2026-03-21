package me.carson.terrariaItems.enemyProjectilesFolder.mobProjectiles;

import me.carson.terrariaItems.enemyProjectilesFolder.EnemyProjectile;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;

public class RuneWizardBolt extends EnemyProjectile {

    public RuneWizardBolt(Plugin plugin) {
        super(plugin, 10,1f, "rune_wizard_bolt", "RuneWizardBolt",0,0, DamageType.LIGHTNING_BOLT);
    }


    @Override
    public void hitEntityEffect(LivingEntity entity) {
        entity.setFreezeTicks(300);
        entity.setFireTicks(60);
    }

    @Override
    public void hitBlockEffect(Block block) {
        block.getWorld().playSound(block.getLocation(), "terraria:impact_1", 2.0F, 1.0F);
    }

}
