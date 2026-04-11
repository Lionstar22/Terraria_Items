package me.carson.terrariaItems.weaponsFolder.weapons.gunFolder;

import me.carson.terrariaItems.weaponsFolder.Weapon;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public abstract class Gun extends Weapon {


    protected final float speed;
    protected final float damage;
    protected final float spread;
    protected final float duration;

    public Gun(Plugin plugin, String name, String rarity, Material baseMaterial, String texture, String id, int cooldown, float speed, float damage, float spread, float duration,String lore) {
        super(plugin, name, rarity, baseMaterial, texture, id, cooldown, lore);
        this.speed = speed;
        this.damage = damage;
        this.spread = spread;
        this.duration = duration;
    }

}
