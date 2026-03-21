package me.carson.terrariaItems.listenersHandler;

import me.carson.terrariaItems.materialsFolder.materials.IllegalGunParts;
import me.carson.terrariaItems.weaponsFolder.weapons.bowFolder.bows.PulseBow;
import me.carson.terrariaItems.weaponsFolder.weapons.gunFolder.guns.Minishark;
import me.carson.terrariaItems.weaponsFolder.weapons.gunFolder.guns.Shotgun;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
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

public class VillagerTradingListeners implements Listener {

    private final Plugin plugin;
    private final WorldDataHandler worldInstance=WorldDataHandler.getInstance();
    private final Map<UUID, List<MerchantRecipe>> originalRecipes = new HashMap<>();

    public VillagerTradingListeners(Plugin plugin){
        this.plugin=plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onVillagerInventoryOpen(InventoryOpenEvent event) {
        if (!(event.getInventory().getHolder() instanceof Villager villager)) return;
        if (!(event.getPlayer() instanceof Player)) return;
        if (villager.getProfession() != Villager.Profession.WEAPONSMITH) return;
        List<MerchantRecipe> recipes = new ArrayList<>(villager.getRecipes());
        originalRecipes.put(villager.getUniqueId(), new ArrayList<>(villager.getRecipes()));

        recipes.add(addMinishark());
        if(isNight(villager.getWorld())){
            recipes.add(addIllegalGunParts());
        }
        if(worldInstance.getHardmode()){
            recipes.add(addShotgun());
        }

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
        if (!(event.getPlayer() instanceof Player)) return;
        if(!worldInstance.getHardmode()){return;}

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

    public boolean isNight(World world) {
        long time = world.getTime();
        return time >= 13000 && time < 23000;
    }

    public MerchantRecipe addShotgun(){
        MerchantRecipe recipe = new MerchantRecipe(
                Shotgun.getItem(plugin),
                0, 999, true, 1, 0.05f
        );
        recipe.addIngredient(new ItemStack(Material.EMERALD, 25));
        recipe.addIngredient(new ItemStack(Material.IRON_INGOT, 16));
        return recipe;
    }

    public MerchantRecipe addMinishark(){
        MerchantRecipe recipe = new MerchantRecipe(
                Minishark.getItem(plugin),
                0, 999, true, 1, 0.05f
        );
        recipe.addIngredient(new ItemStack(Material.EMERALD, 35));
        recipe.addIngredient(new ItemStack(Material.IRON_INGOT, 8));
        return recipe;
    }

    public MerchantRecipe addIllegalGunParts(){
        MerchantRecipe recipe = new MerchantRecipe(
                IllegalGunParts.getItem(plugin),
                0, 999, true, 1, 0.05f
        );
        recipe.addIngredient(new ItemStack(Material.EMERALD, 20));
        return recipe;
    }
}
