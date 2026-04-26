package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class ManaFlower extends Accessory {

    public ManaFlower(Plugin plugin){
        super(plugin,"mana_flower.name","#FF9696", Material.NETHER_BRICK,"mana_flower","ManaFlower","mana_flower.lore");
    }

    @Override
    public void activateEffect(Player player) {
        playerInstance.addManaReduction(player.getUniqueId(),0.08);
    }

    @Override
    public void deactivateEffect(Player player) {
        playerInstance.subtractManaReduction(player.getUniqueId(),0.08);
    }

    @Override
    public void onPlayerHit(Player player, EntityDamageEvent event) {

    }
    @Override
    public void onPlayerEffect(Player player, EntityPotionEffectEvent event) {

    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new ManaFlower(plugin).createItem();
        ItemMeta meta= item.getItemMeta();
        NamespacedKey key = new NamespacedKey(plugin, "mana_flower_accessory");
        meta.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte) 1);
        item.setItemMeta(meta);
        return item;
    }

}
