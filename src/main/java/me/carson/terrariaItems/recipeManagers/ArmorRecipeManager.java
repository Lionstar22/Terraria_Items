package me.carson.terrariaItems.recipeManagers;

import me.carson.terrariaItems.armourFolder.armors.cactusArmor.CactusBoots;
import me.carson.terrariaItems.armourFolder.armors.cactusArmor.CactusChestplate;
import me.carson.terrariaItems.armourFolder.armors.cactusArmor.CactusHelmet;
import me.carson.terrariaItems.armourFolder.armors.cactusArmor.CactusLeggings;
import me.carson.terrariaItems.armourFolder.armors.hallowedArmor.*;
import me.carson.terrariaItems.armourFolder.armors.moltenArmor.*;
import me.carson.terrariaItems.armourFolder.armors.shadowArmor.*;
import me.carson.terrariaItems.materialsFolder.materials.DemoniteBar;
import me.carson.terrariaItems.materialsFolder.materials.HallowedBar;
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
        registerShadowElytraRecipe();
        registerMoltenHelmRecipe();
        registerMoltenChestplateRecipe();
        registerMoltenLeggingsRecipe();
        registerMoltenBootsRecipe();
        registerMoltenElytraRecipe();
        registerHallowedHelmetRecipe();
        registerHallowedChestplateRecipe();
        registerHallowedLeggingsRecipe();
        registerHallowedBootsRecipe();
        registerHallowedElytraRecipe();
        registerCactusHelmetRecipe();
        registerCactusChestplateRecipe();
        registerCactusLeggingsRecipe();
        registerCactusBootsRecipe();
    }

    private void registerShadowHelmetRecipe(){
        ItemStack helmet=ShadowHelmet.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "ShadowHelmet");
        ShapedRecipe recipe = new ShapedRecipe(key, helmet);
        recipe.shape("DDD","D D","   ");
        recipe.setIngredient('D', DemoniteBar.getItem(plugin));
        Bukkit.addRecipe(recipe);
    }

    private void registerShadowScalemailRecipe(){
        ItemStack scalemail=ShadowScalemail.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "ShadowScalemail");
        ShapedRecipe recipe = new ShapedRecipe(key, scalemail);
        recipe.shape("D D","DDD","DDD");
        recipe.setIngredient('D', DemoniteBar.getItem(plugin));
        Bukkit.addRecipe(recipe);
    }

    private void registerShadowLeggingsRecipe(){
        ItemStack leggings=ShadowLeggings.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "ShadowLeggings");
        ShapedRecipe recipe = new ShapedRecipe(key, leggings);
        recipe.shape("DDD","D D","D D");
        recipe.setIngredient('D', DemoniteBar.getItem(plugin));
        Bukkit.addRecipe(recipe);
    }

    private void registerShadowGreavesRecipe(){
        ItemStack greaves=ShadowGreaves.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "ShadowGreaves");
        ShapedRecipe recipe = new ShapedRecipe(key, greaves);
        recipe.shape("   ","D D","D D");
        recipe.setIngredient('D', DemoniteBar.getItem(plugin));
        Bukkit.addRecipe(recipe);
    }

    private void registerShadowElytraRecipe(){
        ItemStack elytra=ShadowElytra.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "ShadowElytra");
        ShapedRecipe recipe = new ShapedRecipe(key, elytra);
        recipe.shape(" D ","DED"," D ");
        recipe.setIngredient('D', DemoniteBar.getItem(plugin));
        recipe.setIngredient('E',Material.ELYTRA);
        Bukkit.addRecipe(recipe);
    }

    private void registerMoltenHelmRecipe(){
        ItemStack helm= MoltenHelmet.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "MoltenHelmet");
        ShapelessRecipe recipe = new ShapelessRecipe(key,helm);
        recipe.addIngredient(Material.DIAMOND_HELMET);
        recipe.addIngredient(HellstoneBar.getItem(plugin));
        Bukkit.addRecipe(recipe);
    }

    private void registerMoltenChestplateRecipe(){
        ItemStack chestplate= MoltenChestplate.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "MoltenChestplate");
        ShapelessRecipe recipe = new ShapelessRecipe(key,chestplate);
        recipe.addIngredient(Material.DIAMOND_CHESTPLATE);
        recipe.addIngredient(HellstoneBar.getItem(plugin));
        Bukkit.addRecipe(recipe);
    }

    private void registerMoltenLeggingsRecipe(){
        ItemStack leggings= MoltenLeggings.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "MoltenLeggings");
        ShapelessRecipe recipe = new ShapelessRecipe(key,leggings);
        recipe.addIngredient(Material.DIAMOND_LEGGINGS);
        recipe.addIngredient(HellstoneBar.getItem(plugin));
        Bukkit.addRecipe(recipe);
    }

    private void registerMoltenBootsRecipe(){
        ItemStack boots= MoltenBoots.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "MoltenBoots");
        ShapelessRecipe recipe = new ShapelessRecipe(key,boots);
        recipe.addIngredient(Material.DIAMOND_BOOTS);
        recipe.addIngredient(HellstoneBar.getItem(plugin));
        Bukkit.addRecipe(recipe);
    }

    private void registerMoltenElytraRecipe(){
        ItemStack elytra= MoltenElytra.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "MoltenElytra");
        ShapelessRecipe recipe = new ShapelessRecipe(key,elytra);
        recipe.addIngredient(Material.ELYTRA);
        recipe.addIngredient(HellstoneBar.getItem(plugin));
        Bukkit.addRecipe(recipe);
    }

    private void registerHallowedHelmetRecipe(){
        ItemStack item= HallowedHelmet.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "HallowedHelmet");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("HHH","H H","   ");
        recipe.setIngredient('H', HallowedBar.getItem(plugin));
        Bukkit.addRecipe(recipe);
    }

    private void registerHallowedChestplateRecipe(){
        ItemStack item= HallowedChestplate.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "HallowedChestplate");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("H H","HHH","HHH");
        recipe.setIngredient('H', HallowedBar.getItem(plugin));
        Bukkit.addRecipe(recipe);
    }

    private void registerHallowedLeggingsRecipe(){
        ItemStack item= HallowedLeggings.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "HallowedLeggings");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("HHH","H H","H H");
        recipe.setIngredient('H', HallowedBar.getItem(plugin));
        Bukkit.addRecipe(recipe);
    }

    private void registerHallowedBootsRecipe(){
        ItemStack item= HallowedBoots.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "HallowedBoots");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("   ","H H","H H");
        recipe.setIngredient('H', HallowedBar.getItem(plugin));
        Bukkit.addRecipe(recipe);
    }

    private void registerHallowedElytraRecipe(){
        ItemStack item= HallowedElytra.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "HallowedElytra");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" H ","HEH"," H ");
        recipe.setIngredient('H', HallowedBar.getItem(plugin));
        recipe.setIngredient('E', Material.ELYTRA);
        Bukkit.addRecipe(recipe);
    }

    private void registerCactusHelmetRecipe(){
        ItemStack item= CactusHelmet.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "CactusHelmet");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("CCC","C C","   ");
        recipe.setIngredient('C', Material.CACTUS);
        Bukkit.addRecipe(recipe);
    }

    private void registerCactusChestplateRecipe(){
        ItemStack item= CactusChestplate.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "CactusChestplate");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("C C","CCC","CCC");
        recipe.setIngredient('C', Material.CACTUS);
        Bukkit.addRecipe(recipe);
    }

    private void registerCactusLeggingsRecipe(){
        ItemStack item= CactusLeggings.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "CactusLeggings");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("CCC","C C","C C");
        recipe.setIngredient('C', Material.CACTUS);
        Bukkit.addRecipe(recipe);
    }

    private void registerCactusBootsRecipe(){
        ItemStack item= CactusBoots.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "CactusBoots");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("   ","C C","C C");
        recipe.setIngredient('C', Material.CACTUS);
        Bukkit.addRecipe(recipe);
    }
}
