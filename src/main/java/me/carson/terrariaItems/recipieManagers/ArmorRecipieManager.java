package me.carson.terrariaItems.recipieManagers;

import me.carson.terrariaItems.armourFolder.armors.shadowArmor.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;

public class ArmorRecipieManager {

    private final Plugin plugin;

    public ArmorRecipieManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void registerRecipes() {
        registerShadowHelmetRecipe();
        registerShadowScalemailRecipe();
        registerShadowLeggingsRecipe();
        registerShadowGreavesRecipe();
    }

    private void registerShadowHelmetRecipe(){
        ItemStack helmet=new ShadowHelmet(plugin).createItem();
        NamespacedKey key = new NamespacedKey(plugin, "ShadowHelmet");
        ShapedRecipe recipe = new ShapedRecipe(key, helmet);
        recipe.shape("DDD","D D","   ");
        recipe.setIngredient('D', Material.IRON_BLOCK);
        Bukkit.addRecipe(recipe);
    }

    private void registerShadowScalemailRecipe(){
        ItemStack scalemail=new ShadowScalemail(plugin).createItem();
        NamespacedKey key = new NamespacedKey(plugin, "ShadowScalemail");
        ShapedRecipe recipe = new ShapedRecipe(key, scalemail);
        recipe.shape("D D","DDD","DDD");
        recipe.setIngredient('D', Material.IRON_BLOCK);
        Bukkit.addRecipe(recipe);
    }

    private void registerShadowLeggingsRecipe(){
        ItemStack leggings=new ShadowLeggings(plugin).createItem();
        NamespacedKey key = new NamespacedKey(plugin, "ShadowLeggings");
        ShapedRecipe recipe = new ShapedRecipe(key, leggings);
        recipe.shape("DDD","D D","D D");
        recipe.setIngredient('D', Material.IRON_BLOCK);
        Bukkit.addRecipe(recipe);
    }

    private void registerShadowGreavesRecipe(){
        ItemStack greaves=new ShadowGreaves(plugin).createItem();
        NamespacedKey key = new NamespacedKey(plugin, "ShadowGreaves");
        ShapedRecipe recipe = new ShapedRecipe(key, greaves);
        recipe.shape("   ","D D","D D");
        recipe.setIngredient('D', Material.IRON_BLOCK);
        Bukkit.addRecipe(recipe);
    }
}
