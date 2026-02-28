package me.carson.terrariaItems.weaponsFolder.weapons.swordFolder.swords;

import me.carson.terrariaItems.projectilesFolder.projectiles.ornaments.*;
import me.carson.terrariaItems.weaponsFolder.Weapon;
import me.carson.terrariaItems.weaponsFolder.weapons.swordFolder.Sword;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ChristmasTreeSword extends Sword {

    private final HashMap<UUID, Long> lastClickTime = new HashMap<>();

    public ChristmasTreeSword(Plugin plugin) {
        super(plugin,"Christmas Tree Sword","#FFFF0A", Material.DIAMOND_SWORD,"christmas_tree_sword","ChristmasTreeSword",0,1.5f,6,0,75, new ArrayList<>(List.of(ChatColor.GRAY+"Shoots Christmas ornaments",ChatColor.GRAY+"6 Damage")));
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new ChristmasTreeSword(plugin).createItem();
        ItemMeta meta= item.getItemMeta();
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE,new AttributeModifier(new NamespacedKey(plugin,"attack"),5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
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

        new StarOrnament(plugin).createProjectile(player,speed,damage,spread,duration);
        new RedOrnament(plugin).createProjectile(player,0.75f,damage,0.2f,duration);
        new BlueOrnament(plugin).createProjectile(player,0.75f,damage,0.2f,duration);
        new GreenOrnament(plugin).createProjectile(player,0.75f,damage,0.2f,duration);
        new YellowOrnament(plugin).createProjectile(player,0.75f,damage,0.2f,duration);
    }

    @Override
    public void rightActivate(Player player) {

    }

}
