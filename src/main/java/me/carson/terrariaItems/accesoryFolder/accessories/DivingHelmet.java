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


public class DivingHelmet extends Accessory  {

    public DivingHelmet(Plugin plugin){
        super(plugin,"diving_helmet.name","#96FF96",Material.NETHER_BRICK,"diving_helmet","DivingHelmet","diving_helmet.lore");
    }

    @Override
    public void activateEffect(Player player){
        player.getAttribute(Attribute.OXYGEN_BONUS).removeModifier(new AttributeModifier(new NamespacedKey(plugin,"diving_helmet_oxygen_bonus"),1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
        player.getAttribute(Attribute.OXYGEN_BONUS).addModifier(new AttributeModifier(new NamespacedKey(plugin,"diving_helmet_oxygen_bonus"),1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
    }

    @Override
    public void deactivateEffect(Player player) {
        player.getAttribute(Attribute.OXYGEN_BONUS).removeModifier(new AttributeModifier(new NamespacedKey(plugin,"diving_helmet_oxygen_bonus"),1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
    }

    @Override
    public void onPlayerHit(Player player, EntityDamageEvent event) {

    }

    @Override
    public void onPlayerEffect(Player player, EntityPotionEffectEvent event) {

    }

    public static ItemStack getItem(Plugin plugin) {
        return new DivingHelmet(plugin).createItem();
    }

}
