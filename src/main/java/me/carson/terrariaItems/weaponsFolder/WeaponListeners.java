package me.carson.terrariaItems.weaponsFolder;

import me.carson.terrariaItems.weaponsFolder.weapons.bowFolder.bows.PulseBow;
import me.carson.terrariaItems.weaponsFolder.weapons.gunFolder.guns.Shotgun;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class WeaponListeners implements Listener {

    private final Plugin plugin;
    private final NamespacedKey customItemKey;
    private final Map<UUID, List<MerchantRecipe>> originalRecipes = new HashMap<>();

    public WeaponListeners(Plugin plugin){
        this.plugin=plugin;
        customItemKey=new NamespacedKey(plugin, "custom_item_id");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public Boolean hasItemInHand(ItemStack item, String id){
        if(item!=null&&item.hasItemMeta()){
            return Objects.equals(Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(customItemKey, PersistentDataType.STRING), id);
        }
        return false;
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
    public void onVolcanoHit(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player player)) return;
        ItemStack item=player.getInventory().getItemInMainHand();
        if(hasItemInHand(item,"Volcano")){
            player.getWorld().playSound(event.getEntity(), "terraria:volcano", 2.5F, 1.0F);
        }
    }

    @EventHandler
    public void onSnowballHit(ProjectileHitEvent event) {
        if (!(event.getEntity() instanceof Snowball snowball)) return;
        NamespacedKey key = new NamespacedKey(plugin, "custom_snowball");
        PersistentDataContainer pdc = snowball.getPersistentDataContainer();
        if (!pdc.has(key, PersistentDataType.INTEGER)) return;
        if (!(event.getHitEntity() instanceof LivingEntity target)) return;

        World world= event.getEntity().getWorld();
        world.playSound(event.getHitEntity().getLocation(), "terraria:snowball_impact", 1.0F, 1.0F);
        target.damage(8);
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player player)) return;
        ItemStack item=player.getInventory().getItemInMainHand();
        if(hasItemInHand(item,"BladeOfGrass")){
            if(event.getEntity() instanceof LivingEntity livingEntity){
                if(Math.random()<0.25){
                    livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.POISON,140,2,false,true,true));
                }
            }
        }
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        if (!event.isSneaking()) {
            player.removePotionEffect(PotionEffectType.SLOWNESS);
        }
        if(hasItemInHand(player.getInventory().getItemInMainHand(),"SniperRifle")){
            if (event.isSneaking()) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 999999, 20, false, false, false));
            }
            else {
                player.removePotionEffect(PotionEffectType.SLOWNESS);
            }
        }
    }

    @EventHandler
    public void onVillagerInventoryOpen(InventoryOpenEvent event) {
        if (!(event.getInventory().getHolder() instanceof Villager villager)) return;
        if (!(event.getPlayer() instanceof Player player)) return;
        if (villager.getProfession() != Villager.Profession.WEAPONSMITH) return;
        if (!hasAdvancement(player, "nether/root")) return;

        List<MerchantRecipe> recipes = new ArrayList<>(villager.getRecipes());
        originalRecipes.put(villager.getUniqueId(), new ArrayList<>(villager.getRecipes()));

        MerchantRecipe recipe = new MerchantRecipe(
                Shotgun.getItem(plugin),
                0, 999, true, 1, 0.05f
        );
        recipe.addIngredient(new ItemStack(Material.EMERALD, 32));
        recipe.addIngredient(new ItemStack(Material.IRON_INGOT, 16));
        recipes.add(recipe);

        villager.setRecipes(recipes);
    }

    @EventHandler
    public void onVillagerInventoryClose(InventoryCloseEvent event) {
        if (!(event.getInventory().getHolder() instanceof Villager villager)) return;

        List<MerchantRecipe> original = originalRecipes.get(villager.getUniqueId());
        if (original != null) {
            villager.setRecipes(original);
            originalRecipes.remove(villager.getUniqueId());
        }
    }

    @EventHandler
    public void onMerchantInventoryOpen(InventoryOpenEvent event) {
        if (!(event.getInventory().getHolder() instanceof WanderingTrader villager)) return;
        if (!(event.getPlayer() instanceof Player player)) return;
        if (!hasAdvancement(player, "end/root")) return;

        List<MerchantRecipe> recipes = new ArrayList<>(villager.getRecipes());
        originalRecipes.put(villager.getUniqueId(), new ArrayList<>(villager.getRecipes()));

        MerchantRecipe recipe = new MerchantRecipe(
                PulseBow.getItem(plugin),
                0, 999, true, 1, 0.05f
        );
        recipe.addIngredient(new ItemStack(Material.EMERALD, 48));
        recipes.add(recipe);

        villager.setRecipes(recipes);
    }

    @EventHandler
    public void onMerchantInventoryClose(InventoryCloseEvent event) {
        if (!(event.getInventory().getHolder() instanceof WanderingTrader villager)) return;

        List<MerchantRecipe> original = originalRecipes.get(villager.getUniqueId());
        if (original != null) {
            villager.setRecipes(original);
            originalRecipes.remove(villager.getUniqueId());
        }
    }

    @EventHandler
    public void onExplosionDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Item && (event.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION)) {
            event.setCancelled(true);
        }
    }
}
