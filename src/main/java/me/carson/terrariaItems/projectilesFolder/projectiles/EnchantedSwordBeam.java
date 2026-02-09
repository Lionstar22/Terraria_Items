package me.carson.terrariaItems.projectilesFolder.projectiles;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;

public class EnchantedSwordBeam extends Projectile {

    public EnchantedSwordBeam(Plugin plugin) {
        super(plugin, 0,  "enchanted_sword_beam", "EnchantedSwordBeam",0, 0, DamageType.PLAYER_ATTACK);
    }

    @Override
    public void hitEntityEffect(LivingEntity entity) {

    }

    @Override
    public void hitBlockEffect(Block block) {

    }

}
