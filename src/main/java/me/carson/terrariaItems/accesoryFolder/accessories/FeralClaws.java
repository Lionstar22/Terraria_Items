package me.carson.terrariaItems.accesoryFolder.accessories;


import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.protocol.player.Equipment;
import com.github.retrooper.packetevents.protocol.player.EquipmentSlot;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerEntityEquipment;
import io.github.retrooper.packetevents.util.SpigotConversionUtil;
import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;


public class FeralClaws extends Accessory  {

    public FeralClaws(Plugin plugin){
        super(plugin,"Feral Claws","#FFC896",Material.LIME_DYE,"feral_claws","FeralClaws",new ArrayList<>(List.of(ChatColor.GRAY+"+0.25 increased attack speed",ChatColor.GRAY+"Must be in accessory inventory")));
    }

    @Override
    public void activateEffect(Player player){
        player.getAttribute(Attribute.ATTACK_SPEED).removeModifier(new AttributeModifier(new NamespacedKey(plugin,"feral_claws_attack_speed"),0.25, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
        player.getAttribute(Attribute.ATTACK_SPEED).addModifier(new AttributeModifier(new NamespacedKey(plugin,"feral_claws_attack_speed"),0.25, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
    }

    @Override
    public void deactivateEffect(Player player) {
        player.getAttribute(Attribute.ATTACK_SPEED).removeModifier(new AttributeModifier(new NamespacedKey(plugin,"feral_claws_attack_speed"),0.25, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new FeralClaws(plugin).createItem();
    }

}
