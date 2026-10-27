package me.carson.terrariaItems.accesoryFolder.accessories;


import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class DivingGear extends Accessory  {

    public DivingGear(Plugin plugin){
        super(plugin,"diving_gear.name","#FF9696",Material.NETHER_BRICK,"diving_gear","DivingGear","diving_gear.lore");
    }

    @Override
    public void activateEffect(Player player){
        player.getAttribute(Attribute.OXYGEN_BONUS).removeModifier(new AttributeModifier(new NamespacedKey(plugin,"diving_helmet_oxygen_bonus"),1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
        player.getAttribute(Attribute.OXYGEN_BONUS).addModifier(new AttributeModifier(new NamespacedKey(plugin,"diving_helmet_oxygen_bonus"),1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
        player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, -1, 0, false, false,false));
    }

    @Override
    public void deactivateEffect(Player player) {
        player.getAttribute(Attribute.OXYGEN_BONUS).removeModifier(new AttributeModifier(new NamespacedKey(plugin,"diving_helmet_oxygen_bonus"),1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
        player.removePotionEffect(PotionEffectType.DOLPHINS_GRACE);
    }

    @Override
    public void onPlayerHit(Player player, EntityDamageEvent event) {

    }

    @Override
    public void onPlayerEffect(Player player, EntityPotionEffectEvent event) {

    }

    public static ItemStack getItem(Plugin plugin) {
        return new DivingGear(plugin).createItem();
    }

}
