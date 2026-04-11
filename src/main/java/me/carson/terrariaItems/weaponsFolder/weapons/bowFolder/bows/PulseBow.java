package me.carson.terrariaItems.weaponsFolder.weapons.bowFolder.bows;

import me.carson.terrariaItems.projectilesFolder.projectiles.MistArrow;
import me.carson.terrariaItems.projectilesFolder.projectiles.PulseBolt;
import me.carson.terrariaItems.weaponsFolder.weapons.bowFolder.Bow;
import org.bukkit.*;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.plugin.Plugin;

import java.util.*;

public class PulseBow extends Bow implements Listener {

    private final Map<UUID, List<MerchantRecipe>> originalRecipes = new HashMap<>();

    public PulseBow(Plugin plugin) {
        super(plugin,"pulse_bow.name","#FFFF0A", Material.NETHER_STAR,"pulse_bow","PulseBow",5,2.5f,10,0f, "pulse_bow.name");
    }

    public static ItemStack getItem(Plugin plugin) {
        return new PulseBow(plugin).createItem();
    }

    @Override
    public void leftActivate(Player player) {

    }

    @Override
    public void rightActivate(Player player) {
        if (player.getInventory().contains(Material.ARROW)) {
            player.getInventory().removeItem(new ItemStack(Material.ARROW, 1));
            Location loc = player.getEyeLocation();
            new PulseBolt(plugin).createProjectile(player,speed,damage,spread,100f);
            player.getWorld().playSound(loc, "terraria:pulse", 0.75F, 1.0F);
        }
    }

    private boolean hasAdvancement(Player player, String advancementKey) {
        Advancement advancement = Bukkit.getAdvancement(
                NamespacedKey.minecraft(advancementKey)
        );
        if (advancement == null) return false;
        AdvancementProgress progress = player.getAdvancementProgress(advancement);
        return progress.isDone();
    }

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        if (!(event.getInventory().getHolder() instanceof WanderingTrader villager)) return;
        if (!(event.getPlayer() instanceof Player player)) return;
        if (!hasAdvancement(player, "end/root")) return;

        List<MerchantRecipe> recipes = new ArrayList<>(villager.getRecipes());
        originalRecipes.put(villager.getUniqueId(), new ArrayList<>(villager.getRecipes()));

        MerchantRecipe recipe = new MerchantRecipe(
                getItem(plugin),
                0, 999, true, 1, 0.05f
        );
        recipe.addIngredient(new ItemStack(Material.EMERALD, 48));
        recipes.add(recipe);

        villager.setRecipes(recipes);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (!(event.getInventory().getHolder() instanceof WanderingTrader villager)) return;

        List<MerchantRecipe> original = originalRecipes.get(villager.getUniqueId());
        if (original != null) {
            villager.setRecipes(original);
            originalRecipes.remove(villager.getUniqueId());
        }
    }

}
