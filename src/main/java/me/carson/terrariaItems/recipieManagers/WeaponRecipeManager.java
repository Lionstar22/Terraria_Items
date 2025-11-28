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
        ItemStack bane=new LightsBane(plugin).createItem();
        NamespacedKey key = new NamespacedKey(plugin, "LightsBane");
        ShapedRecipe recipe = new ShapedRecipe(key, bane);
        recipe.shape(" D "," D "," S ");
        recipe.setIngredient('D',new DemoniteBar(plugin).createItem());
        recipe.setIngredient('S',Material.STICK);
        Bukkit.addRecipe(recipe);
    }

    private void registerVolcanoRecipe(){
        ItemStack volcano = new Volcano(plugin).createItem();
        NamespacedKey key = new NamespacedKey(plugin, "Volcano");
        ShapelessRecipe recipe = new ShapelessRecipe(key, volcano);
        recipe.addIngredient(Material.DIAMOND_SWORD);
        recipe.addIngredient(new HellstoneBar(plugin).createItem());
        Bukkit.addRecipe(recipe);
    }

    private void registerMoltenFury(){
        ItemStack fury=new MoltenFury(plugin).createItem();
        NamespacedKey key = new NamespacedKey(plugin, "MoltenFury");
        ShapedRecipe recipe =new ShapedRecipe(key,fury);
        recipe.shape(" WS","WNS"," WS");
        recipe.setIngredient('W',Material.STICK);
        recipe.setIngredient('N',new HellstoneBar(plugin).createItem());
        recipe.setIngredient('S',Material.STRING);
        Bukkit.addRecipe(recipe);
    }

}
