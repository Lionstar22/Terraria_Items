package me.carson.terrariaItems.recipieManagers;

import me.carson.terrariaItems.materialsFolder.materials.DemoniteBar;
import me.carson.terrariaItems.materialsFolder.materials.HellstoneBar;
import me.carson.terrariaItems.weaponsFolder.weapons.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.*;
import org.bukkit.plugin.Plugin;

public class WeaponRecipeManager {

    private final Plugin plugin;

    public WeaponRecipeManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void registerRecipes() {
        registerLightsBaneRecipe();
        registerVolcanoRecipe();
        registerMoltenFury();
    }

    private void registerLightsBaneRecipe(){
        ItemStack bane=LightsBane.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "LightsBane");
        ShapedRecipe recipe = new ShapedRecipe(key, bane);
        recipe.shape(" D "," D "," S ");
        recipe.setIngredient('D',DemoniteBar.getItem(plugin));
        recipe.setIngredient('S',Material.STICK);
        Bukkit.addRecipe(recipe);
    }

    private void registerVolcanoRecipe(){
        ItemStack volcano =Volcano.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "Volcano");
        ShapelessRecipe recipe = new ShapelessRecipe(key, volcano);
        recipe.addIngredient(Material.DIAMOND_SWORD);
        recipe.addIngredient(HellstoneBar.getItem(plugin));
        Bukkit.addRecipe(recipe);
    }

    private void registerMoltenFury(){
        ItemStack fury=MoltenFury.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "MoltenFury");
        ShapedRecipe recipe =new ShapedRecipe(key,fury);
        recipe.shape(" WS","WNS"," WS");
        recipe.setIngredient('W',Material.STICK);
        recipe.setIngredient('N',HellstoneBar.getItem(plugin));
        recipe.setIngredient('S',Material.STRING);
        Bukkit.addRecipe(recipe);
    }

}
