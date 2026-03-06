package me.carson.terrariaItems.weaponsFolder.weapons.gunFolder.guns;


import me.carson.terrariaItems.materialsFolder.MaterialManager;
import me.carson.terrariaItems.projectilesFolder.Projectile;
import me.carson.terrariaItems.projectilesFolder.ProjectileManager;
import me.carson.terrariaItems.weaponsFolder.weapons.gunFolder.Gun;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.*;

public class Shotgun extends Gun implements Listener {
    ProjectileManager projectileManagerInstance= ProjectileManager.getInstance();
    MaterialManager materialManagerInstance = MaterialManager.getInstance();
    NamespacedKey key = new NamespacedKey(plugin, "custom_item_id");
    private final Map<UUID, List<MerchantRecipe>> originalRecipes = new HashMap<>();

    public Shotgun(Plugin plugin) {
        super(plugin,"Shotgun","#FF9696", Material.ARMADILLO_SCUTE,"shotgun","Shotgun",30,3,3.5f,0.1f,100, new ArrayList<>(List.of(ChatColor.GRAY+"Fires a spread of bullets",ChatColor.GRAY+"3.5 Damage")));
    }

    @Override
    public void leftActivate(Player player) {

    }

    @Override
    public void rightActivate(Player player) {
        ItemStack bullet;
        for(ItemStack itemInv : player.getInventory().getStorageContents()){
            if(materialManagerInstance.getBulletItem(itemInv)!=null){
                bullet=materialManagerInstance.getBulletItem(itemInv);
                Projectile projectile = projectileManagerInstance.getBullet(bullet.getItemMeta().getPersistentDataContainer().get(key, PersistentDataType.STRING));
                for(int i =0;i<5;i++){
                    projectile.createProjectile(player,speed,damage,spread,duration);
                }
                player.getInventory().removeItem(bullet);
                player.getWorld().playSound(player.getLocation(),"terraria:shotgun_shoot", 1.0F, 1.0F);
                break;
            }
        }
    }

    public static ItemStack getItem(Plugin plugin) {return new Shotgun(plugin).createItem();}

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
        if (!(event.getInventory().getHolder() instanceof Villager villager)) return;
        if (!(event.getPlayer() instanceof Player player)) return;
        if (villager.getProfession() != Villager.Profession.WEAPONSMITH) return;
        if (!hasAdvancement(player, "nether/root")) return;

        List<MerchantRecipe> recipes = new ArrayList<>(villager.getRecipes());
        originalRecipes.put(villager.getUniqueId(), new ArrayList<>(villager.getRecipes()));

        MerchantRecipe recipe = new MerchantRecipe(
                getItem(plugin),
                0, 999, true, 1, 0.05f
        );
        recipe.addIngredient(new ItemStack(Material.EMERALD, 32));
        recipe.addIngredient(new ItemStack(Material.IRON_INGOT, 16));
        recipes.add(recipe);

        villager.setRecipes(recipes);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (!(event.getInventory().getHolder() instanceof Villager villager)) return;

        List<MerchantRecipe> original = originalRecipes.get(villager.getUniqueId());
        if (original != null) {
            villager.setRecipes(original);
            originalRecipes.remove(villager.getUniqueId());
        }
    }
}
