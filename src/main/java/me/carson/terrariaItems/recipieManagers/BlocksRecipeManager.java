package me.carson.terrariaItems.recipieManagers;

import me.carson.terrariaItems.armourFolder.armors.shadowArmor.ShadowHelmet;
import me.carson.terrariaItems.blocksFolder.blocks.Hellforge;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;

public class BlocksRecipeManager {

    private final Plugin plugin;

    public BlocksRecipeManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void registerRecipes() {

    }

    private void registerHellforgeRecipe(){
        ItemStack forge=new Hellforge(plugin).createItem();
        NamespacedKey key = new NamespacedKey(plugin, "Hellforge");
        ShapedRecipe recipe = new ShapedRecipe(key, forge);
        recipe.shape(" M ","MFM"," M ");
        recipe.setIngredient('M', Material.MAGMA_BLOCK);
        recipe.setIngredient('F', Material.FURNACE);
        Bukkit.addRecipe(recipe);
    }
}
