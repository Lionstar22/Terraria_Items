package me.carson.terrariaItems.weaponsFolder.weapons;

import me.carson.terrariaItems.projectilesFolder.projectiles.ornaments.*;
import me.carson.terrariaItems.weaponsFolder.Weapon;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ChristmasTreeSword extends Weapon {

    private final HashMap<UUID, Long> lastClickTime = new HashMap<>();

    public ChristmasTreeSword(Plugin plugin) {
        super(plugin,"Christmas Tree Sword","#FFFF0A",15,0, Material.DIAMOND_SWORD,"christmas_tree_sword","ChristnasTreeSword",0, new ArrayList<>(List.of(ChatColor.GRAY+"Shoots Christmas ornaments")));
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new ChristmasTreeSword(plugin).createItem();
        ItemMeta meta= item.getItemMeta();
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE,new AttributeModifier(new NamespacedKey(plugin,"attack"),14, AttributeModifier.Operation.ADD_NUMBER));
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public void leftActivate(Player player) {
        player.getWorld().playSound(player.getLocation(), "terraria:sword_use", 1.0F, 1.0F);

        long currentTime = System.currentTimeMillis();
        long lastTime = lastClickTime.getOrDefault(player.getUniqueId(), 0L);

        if (currentTime - lastTime < 750) {
            return;
        }
        lastClickTime.put(player.getUniqueId(), currentTime);

        new StarOrnament(plugin).createProjectile(player,super.damage,super.spread);
        new RedOrnament(plugin).createProjectile(player,super.damage,0.2f);
        new BlueOrnament(plugin).createProjectile(player,super.damage,0.2f);
        new GreenOrnament(plugin).createProjectile(player,super.damage,0.2f);
        new YellowOrnament(plugin).createProjectile(player,super.damage,0.2f);
    }

    @Override
    public void rightActivate(Player player) {

    }

}
