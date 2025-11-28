package me.carson.terrariaItems.recipieManagers;

import me.carson.terrariaItems.armourFolder.armors.moltenArmor.MoltenBoots;
import me.carson.terrariaItems.armourFolder.armors.moltenArmor.MoltenChestplate;
import me.carson.terrariaItems.armourFolder.armors.moltenArmor.MoltenHelmet;
import me.carson.terrariaItems.armourFolder.armors.moltenArmor.MoltenLeggings;
import me.carson.terrariaItems.armourFolder.armors.shadowArmor.*;
import me.carson.terrariaItems.materialsFolder.materials.DemoniteBar;
import me.carson.terrariaItems.materialsFolder.materials.HellstoneBar;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.Plugin;

public class ArmorRecipeManager {

    private final Plugin plugin;

    public ArmorRecipeManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void registerRecipes() {
        registerShadowHelmetRecipe();
        registerShadowScalemailRecipe();
        registerShadowLeggingsRecipe();
        registerShadowGreavesRecipe();
        registerMoltenHelmRecipe();
        registerMoltenChestplateRecipe();
        registerMoltenLeggingsRecipe();
        registerMoltenBootsRecipe();
    }

    private void registerShadowHelmetRecipe(){
        ItemStack helmet=new ShadowHelmet(plugin).createItem();
        NamespacedKey key = new NamespacedKey(plugin, "ShadowHelmet");
        ShapedRecipe recipe = new ShapedRecipe(key, helmet);
        recipe.shape("DDD","D D","   ");
        recipe.setIngredient('D', new DemoniteBar(plugin).createItem());
        Bukkit.addRecipe(recipe);
    }

    private void registerShadowScalemailRecipe(){
        ItemStack scalemail=new ShadowScalemail(plugin).createItem();
        NamespacedKey key = new NamespacedKey(plugin, "ShadowScalemail");
        ShapedRecipe recipe = new ShapedRecipe(key, scalemail);
        recipe.shape("D D","DDD","DDD");
        recipe.setIngredient('D', new DemoniteBar(plugin).createItem());
        Bukkit.addRecipe(recipe);
    }

    private void registerShadowLeggingsRecipe(){
        ItemStack leggings=new ShadowLeggings(plugin).createItem();
        NamespacedKey key = new NamespacedKey(plugin, "ShadowLeggings");
        ShapedRecipe recipe = new ShapedRecipe(key, leggings);
        recipe.shape("DDD","D D","D D");
        recipe.setIngredient('D', new DemoniteBar(plugin).createItem());
        Bukkit.addRecipe(recipe);
    }

    private void registerShadowGreavesRecipe(){
        ItemStack greaves=new ShadowGreaves(plugin).createItem();
        NamespacedKey key = new NamespacedKey(plugin, "ShadowGreaves");
        ShapedRecipe recipe = new ShapedRecipe(key, greaves);
        recipe.shape("   ","D D","D D");
        recipe.setIngredient('D', new DemoniteBar(plugin).createItem());
        Bukkit.addRecipe(recipe);
    }

    private void registerMoltenHelmRecipe(){
        ItemStack helm= new MoltenHelmet(plugin).createItem();
        NamespacedKey key = new NamespacedKey(plugin, "MoltenHelmet");
        ShapelessRecipe recipe = new ShapelessRecipe(key,helm);
        recipe.addIngredient(Material.DIAMOND_HELMET);
        recipe.addIngredient(new HellstoneBar(plugin).createItem());
        Bukkit.addRecipe(recipe);
    }

    private void registerMoltenChestplateRecipe(){
        ItemStack chestplate= new MoltenChestplate(plugin).createItem();
        NamespacedKey key = new NamespacedKey(plugin, "MoltenChestplate");
        ShapelessRecipe recipe = new ShapelessRecipe(key,chestplate);
        recipe.addIngredient(Material.DIAMOND_CHESTPLATE);
        recipe.addIngredient(new HellstoneBar(plugin).createItem());
        Bukkit.addRecipe(recipe);
    }

    private void registerMoltenLeggingsRecipe(){
        ItemStack leggings= new MoltenLeggings(plugin).createItem();
        NamespacedKey key = new NamespacedKey(plugin, "MoltenLeggings");
        ShapelessRecipe recipe = new ShapelessRecipe(key,leggings);
        recipe.addIngredient(Material.DIAMOND_LEGGINGS);
        recipe.addIngredient(new HellstoneBar(plugin).createItem());
        Bukkit.addRecipe(recipe);
    }

    private void registerMoltenBootsRecipe(){
        ItemStack boots= new MoltenBoots(plugin).createItem();
        NamespacedKey key = new NamespacedKey(plugin, "MoltenBoots");
        ShapelessRecipe recipe = new ShapelessRecipe(key,boots);
        recipe.addIngredient(Material.DIAMOND_BOOTS);
        recipe.addIngredient(new HellstoneBar(plugin).createItem());
        Bukkit.addRecipe(recipe);
    }
}
