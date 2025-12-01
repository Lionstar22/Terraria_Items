package me.carson.terrariaItems.recipieManagers;

import me.carson.terrariaItems.materialsFolder.materials.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.Plugin;

public class MaterialRecipeManager implements Listener {

    private final Plugin plugin;

    public MaterialRecipeManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void registerRecipes() {
        registerDemoniteBarRecipe();
        registerHellstoneRecipe();
        registerRubyRecipe();
        registerHellstoneBarRecipe();
        registerHallowedBarRecipe();
    }

    private void registerDemoniteBarRecipe(){
        ItemStack demoniteBar=DemoniteBar.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "DemoniteBar");
        ShapedRecipe recipe = new ShapedRecipe(key, demoniteBar);
        recipe.shape(" S ","SIS"," S ");
        recipe.setIngredient('I', Material.IRON_INGOT);
        recipe.setIngredient('S', Material.SOUL_SAND);
        Bukkit.addRecipe(recipe);
    }

    private void registerHellstoneRecipe(){
        ItemStack hellstone=Hellstone.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "Hellstone");
        ShapelessRecipe recipe = new ShapelessRecipe(key, hellstone);
        recipe.addIngredient(Material.NETHERITE_SCRAP);
        recipe.addIngredient(Material.LAVA_BUCKET);
        recipe.addIngredient(Material.OBSIDIAN);
        Bukkit.addRecipe(recipe);
    }

    private void registerRubyRecipe(){
        ItemStack ruby=Ruby.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "Ruby");
        ShapedRecipe recipe = new ShapedRecipe(key,ruby);
        recipe.shape("RRR","RDR","RRR");
        recipe.setIngredient('R', Material.REDSTONE);
        recipe.setIngredient('D', Material.DIAMOND);
        Bukkit.addRecipe(recipe);
    }

    private void registerHellstoneBarRecipe(){
        ItemStack bar=HellstoneBar.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "HellstoneBar");
        FurnaceRecipe recipe = new FurnaceRecipe(
                key,
                bar, // Result
                Material.MAGMA_BLOCK,                   // Ingredient (simple)
                0.35f,                               // XP
                50                                  // Cook time (10s)
        );
        Bukkit.addRecipe(recipe);
    }

    private void registerHallowedBarRecipe(){
        ItemStack hallow=HallowedBar.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "HallowedBar");
        ShapelessRecipe recipe = new ShapelessRecipe(key, hallow);
        recipe.addIngredient(SoulOfFright.getItem(plugin));
        recipe.addIngredient(SoulOfSight.getItem(plugin));
        recipe.addIngredient(SoulOfMight.getItem(plugin));
        Bukkit.addRecipe(recipe);
    }

}
