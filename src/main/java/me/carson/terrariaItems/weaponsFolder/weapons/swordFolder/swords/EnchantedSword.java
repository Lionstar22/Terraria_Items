package me.carson.terrariaItems.weaponsFolder.weapons.swordFolder.swords;

import me.carson.terrariaItems.projectilesFolder.projectiles.EnchantedSwordBeam;
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

public class EnchantedSword extends Sword {

    private final HashMap<UUID, Long> lastClickTime = new HashMap<>();

    public EnchantedSword(Plugin plugin) {
        super(plugin,"Enchanted Sword","#96FF96", Material.DIAMOND_SWORD,"enchanted_sword","EnchantedSword",0,1.5f,8,0,20, new ArrayList<>(List.of(ChatColor.GRAY+"Shoots an enchanted sword beam",ChatColor.GRAY+"8 Damage")));
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new EnchantedSword(plugin).createItem();
        ItemMeta meta= item.getItemMeta();
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE,new AttributeModifier(new NamespacedKey(plugin,"attack"),7, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlotGroup.MAINHAND));
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

        player.getWorld().playSound(player.getLocation(), "terraria:rod_of_discord_use", 1.0F, 1.0F);
        new EnchantedSwordBeam(plugin).createProjectile(player,speed,damage,spread,duration);
    }

    @Override
    public void rightActivate(Player player) {

    }

}
