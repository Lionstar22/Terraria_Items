package me.carson.terrariaItems.weaponsFolder.weapons.bowFolder;

import me.carson.terrariaItems.weaponsFolder.Weapon;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public abstract class Bow extends Weapon {

    protected final float speed;
    protected final float damage;
    protected final float spread;

    public Bow(Plugin plugin, String name, String rarity, Material baseMaterial, String texture, String id, int cooldown, float speed, float damage, float spread, String lore) {
        super(plugin, name, rarity, baseMaterial, texture, id, cooldown, lore);
        this.speed = speed;
        this.damage = damage;
        this.spread = spread;
    }

    public abstract void rightActivate(Player player);

}
