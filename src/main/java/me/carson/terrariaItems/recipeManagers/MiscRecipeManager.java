package me.carson.terrariaItems.recipeManagers;

import me.carson.terrariaItems.materialsFolder.materials.HallowedBar;
import me.carson.terrariaItems.miscFolder.BasicItems.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;

public class MiscRecipeManager {

    private final Plugin plugin;

    public MiscRecipeManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void registerRecipes() {
        registerPickaxeAxeRecipe();
    }

    private void registerPickaxeAxeRecipe(){
        ItemStack axe= PickaxeAxe.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "PickaxeAxe");
        ShapedRecipe recipe = new ShapedRecipe(key, axe);
        recipe.shape("HHH","HS "," S ");
        recipe.setIngredient('S', Material.STICK);
        recipe.setIngredient('H',HallowedBar.getItem(plugin));
        Bukkit.addRecipe(recipe);
    }
}
