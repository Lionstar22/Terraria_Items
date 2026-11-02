package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class MoltenCharm extends Accessory  {

    public MoltenCharm(Plugin plugin){
        super(plugin,"molten_charm.name","#FF96FF",Material.ORANGE_DYE,"molten_charm","MoltenCharm","molten_charm.lore");
    }

    @Override
    public void activateEffect(Player player){

    }

    @Override
    public void deactivateEffect(Player player) {

    }

    @Override
    public void onPlayerHit(Player player, EntityDamageEvent event) {
        if (OBSIDIAN_SKULL_DAMAGE.contains(event.getCause())){
            event.setCancelled(true);
            return;
        }
        if(event.getCause()== EntityDamageEvent.DamageCause.LAVA){
            if(!player.hasCooldown(Material.ORANGE_DYE)){
                player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 150, 0, false, false,false));
                player.setCooldown(Material.ORANGE_DYE,1200);
                event.setCancelled(true);
            }
        }
    }

    @Override
    public void onPlayerEffect(Player player, EntityPotionEffectEvent event) {

    }


    public static ItemStack getItem(Plugin plugin) {
        return new MoltenCharm(plugin).createItem();
    }

}
