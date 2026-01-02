package me.carson.terrariaItems.weaponsFolder.weapons.swordFolder.swords;

import me.carson.terrariaItems.projectilesFolder.projectiles.ornaments.*;
import me.carson.terrariaItems.weaponsFolder.weapons.swordFolder.Sword;
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

public class TerraBlade extends Sword {

    private final HashMap<UUID, Long> lastClickTime = new HashMap<>();

    public TerraBlade(Plugin plugin) {
        super(plugin,"Terra Blade","#FFFF0A", Material.NETHERITE_SWORD,"terra_blade","TerraBlade",0,1.5f,1000,0,75, new ArrayList<>(List.of(ChatColor.GRAY+"Forged from the finest blades of light and darkness",ChatColor.GRAY+"1000 Damage",ChatColor.GRAY+"+3 Range")));
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new TerraBlade(plugin).createItem();
        ItemMeta meta= item.getItemMeta();
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE,new AttributeModifier(new NamespacedKey(plugin,"attack"),999, AttributeModifier.Operation.ADD_NUMBER));
        meta.addAttributeModifier(Attribute.ENTITY_INTERACTION_RANGE, new AttributeModifier(new NamespacedKey(plugin,"range"),3.0, AttributeModifier.Operation.ADD_NUMBER));
        meta.addAttributeModifier(Attribute.ATTACK_SPEED,new AttributeModifier(new NamespacedKey(plugin,"speed"),-1.5, AttributeModifier.Operation.ADD_NUMBER));
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
        player.getWorld().playSound(player.getLocation(), "terraria:cosmolight_use", 1.0F, 1.0F);

    }

    @Override
    public void rightActivate(Player player) {

    }

}
