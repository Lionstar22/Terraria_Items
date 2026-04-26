package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import me.carson.terrariaItems.projectilesFolder.projectiles.StarCannonStar;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class ManaCloak extends Accessory {

    public ManaCloak(Plugin plugin){
        super(plugin,"mana_cloak.name","#FF96FF", Material.NETHER_BRICK,"mana_cloak","ManaCloak","mana_cloak.lore");
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
        new StarCannonStar(plugin).createFallingProjectile(player,1.5f,7,5f,50,30,event.getEntity().getLocation().add(Math.random()-0.5,0,Math.random()-0.5));
        new StarCannonStar(plugin).createFallingProjectile(player,1.5f,7,5f,50,30,event.getEntity().getLocation().add(Math.random()-0.5,0,Math.random()-0.5));
        new StarCannonStar(plugin).createFallingProjectile(player,1.5f,7,5f,50,30,event.getEntity().getLocation().add(Math.random()-0.5,0,Math.random()-0.5));
        player.getWorld().playSound(event.getEntity().getLocation(), "terraria:falling_star", 0.75F, 1.0F);
    }
    @Override
    public void onPlayerEffect(Player player, EntityPotionEffectEvent event) {

    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new ManaCloak(plugin).createItem();
        ItemMeta meta= item.getItemMeta();
        NamespacedKey key = new NamespacedKey(plugin, "mana_flower_accessory");
        meta.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte) 1);
        item.setItemMeta(meta);
        return item;
    }

}
