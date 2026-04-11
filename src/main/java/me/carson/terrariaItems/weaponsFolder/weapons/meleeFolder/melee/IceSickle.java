package me.carson.terrariaItems.weaponsFolder.weapons.meleeFolder.melee;

import me.carson.terrariaItems.projectilesFolder.projectiles.CausticBeam;
import me.carson.terrariaItems.projectilesFolder.projectiles.IceSickleProjectile;
import me.carson.terrariaItems.weaponsFolder.weapons.meleeFolder.Sword;
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

public class IceSickle extends Sword {

    private final HashMap<UUID, Long> lastClickTime = new HashMap<>();

    public IceSickle(Plugin plugin) {
        super(plugin,"ice_sickle.name","#FF96FF", Material.NETHERITE_SWORD,"ice_sickle","IceSickle",0,0.75f,10.5f,0,25,"ice_sickle.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new IceSickle(plugin).createItem();
        ItemMeta meta= item.getItemMeta();
        meta.addAttributeModifier(Attribute.ATTACK_SPEED,new AttributeModifier(new NamespacedKey(plugin,"speed"),-1.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE,new AttributeModifier(new NamespacedKey(plugin,"attack"),16, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
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

        new IceSickleProjectile(plugin).createProjectile(player,speed,damage,spread,duration);
    }

    @Override
    public void rightActivate(Player player) {

    }

}
