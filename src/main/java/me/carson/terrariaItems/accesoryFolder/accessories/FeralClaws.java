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
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;


public class FeralClaws extends Accessory  {

    public FeralClaws(Plugin plugin){
        super(plugin,"Feral Claws","#FFC896",Material.LIME_DYE,"feral_claws","FeralClaws",new ArrayList<>(List.of(ChatColor.GRAY+"12% increased melee speed",ChatColor.GRAY+"Must be in accessory inventory")));
    }

    @Override
    public void activateEffect(Player player){
        player.getAttribute(Attribute.ATTACK_SPEED).setBaseValue(player.getAttribute(Attribute.ATTACK_SPEED).getValue()+0.48);
    }

    @Override
    public void deactivateEffect(Player player) {
        player.getAttribute(Attribute.ATTACK_SPEED).setBaseValue(Math.max(player.getAttribute(Attribute.ATTACK_SPEED).getValue()-0.48,4));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new FeralClaws(plugin).createItem();
    }

}
