package me.carson.terrariaItems.recipieManagers;

import me.carson.terrariaItems.armourFolder.armors.shadowArmor.ShadowHelmet;
import me.carson.terrariaItems.blocksFolder.blocks.Hellforge;
import me.carson.terrariaItems.materialsFolder.materials.Hellstone;
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
        registerHellforgeRecipe();
    }

    private void registerHellforgeRecipe(){
        ItemStack forge=Hellforge.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "Hellforge");
        ShapedRecipe recipe = new ShapedRecipe(key, forge);
        recipe.shape(" M ","MFM"," M ");
        recipe.setIngredient('M', new Hellstone(plugin).createItem());
        recipe.setIngredient('F', Material.FURNACE);
        Bukkit.addRecipe(recipe);
    }
}
