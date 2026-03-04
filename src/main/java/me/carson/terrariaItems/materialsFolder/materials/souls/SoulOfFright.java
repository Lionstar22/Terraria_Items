package me.carson.terrariaItems.materialsFolder.materials.souls;

import me.carson.terrariaItems.materialsFolder.Material;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class SoulOfFright extends Material implements Listener {

    public SoulOfFright(Plugin plugin) {
        super(plugin,"Soul of Fright","#FF96FF", org.bukkit.Material.IRON_INGOT,"soul_of_fright","SoulOfFright", new ArrayList<>(List.of(ChatColor.GRAY+"The essence of the Wither")));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new SoulOfFright(plugin).createItem();
    }

    @EventHandler
    public void onWitherDeath(EntityDeathEvent event) {
        LivingEntity entity = event.getEntity();
        NamespacedKey key = new NamespacedKey(plugin, "BossWither");
        if(entity.getPersistentDataContainer().has(key, PersistentDataType.BYTE)) {
            int drops = 20 + (int) (Math.random() * 11);
            ItemStack custom = SoulOfFright.getItem(plugin);
            custom.setAmount(drops);
            event.getDrops().add(custom);
        }
    }

}
