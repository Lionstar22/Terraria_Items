package me.carson.terrariaItems.recipieManagers;

import me.carson.terrariaItems.weaponsFolder.weapons.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.SmithingRecipe;
import org.bukkit.plugin.Plugin;

public class WeaponRecipeManager {

    private final Plugin plugin;

    public WeaponRecipeManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void registerRecipes() {
        registerLightsBaneRecipe();
    }

    private void registerLightsBaneRecipe(){
        ItemStack bane=new LightsBane(plugin).createItem();
        NamespacedKey key = new NamespacedKey(plugin, "LightsBane");
        ShapedRecipe recipe = new ShapedRecipe(key, bane);
        recipe.shape(" D "," D "," S ");
        recipe.setIngredient('D',Material.IRON_BLOCK);
        recipe.setIngredient('S',Material.STICK);
        Bukkit.addRecipe(recipe);
    }

}
