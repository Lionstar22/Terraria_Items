package me.carson.terrariaItems.weaponsFolder.weapons;

import me.carson.terrariaItems.projectilesFolder.projectiles.IceBolt;
import me.carson.terrariaItems.projectilesFolder.projectiles.Leaf;
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

public class IceBlade extends Weapon {

    private final HashMap<UUID, Long> lastClickTime = new HashMap<>();

    public IceBlade(Plugin plugin) {
        super(plugin,"Ice Blade","#9696FF", Material.IRON_SWORD,"ice_blade","IceBlade",0, new ArrayList<>(List.of(ChatColor.GRAY+"Shoots an icy bolt")));
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new IceBlade(plugin).createItem();
        ItemMeta meta= item.getItemMeta();
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE,new AttributeModifier(new NamespacedKey(plugin,"attack"),7.0, AttributeModifier.Operation.ADD_NUMBER));
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

        player.getWorld().playSound(player.getLocation(), "terraria:frost_bolt", 1.0F, 1.0F);
        new IceBolt(plugin).createProjectile(player);
    }

    @Override
    public void rightActivate(Player player) {

    }

}
