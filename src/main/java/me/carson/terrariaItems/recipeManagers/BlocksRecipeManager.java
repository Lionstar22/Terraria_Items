package me.carson.terrariaItems.recipeManagers;

import me.carson.terrariaItems.blocksFolder.blocks.Hellforge;
import me.carson.terrariaItems.listenersHandler.WorldDataHandler;
import me.carson.terrariaItems.materialsFolder.materials.Hellstone;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class BlocksRecipeManager implements Listener {

    private final Plugin plugin;
    private final List<NamespacedKey> preHardmodeRecipes;
    private final List<NamespacedKey> HardmodeRecipes;
    private final WorldDataHandler worldInstance=WorldDataHandler.getInstance();

    public BlocksRecipeManager(Plugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);

        preHardmodeRecipes = List.of(
                new NamespacedKey(plugin, "Hellforge")
        );

        HardmodeRecipes = List.of(
        );
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        event.getPlayer().discoverRecipes(preHardmodeRecipes);
        if(worldInstance.getHardmode()){
            event.getPlayer().discoverRecipes(HardmodeRecipes);
        }
    }

    public void registerRecipes() {
        registerHellforgeRecipe();
    }

    private void registerHellforgeRecipe(){
        ItemStack forge=Hellforge.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "Hellforge");
        ShapedRecipe recipe = new ShapedRecipe(key, forge);
        recipe.shape(" M ","MFM"," M ");
        recipe.setIngredient('M', new RecipeChoice.ExactChoice( Hellstone.getItem(plugin)));
        recipe.setIngredient('F', Material.FURNACE);
        recipe.setCategory(CraftingBookCategory.MISC);
        Bukkit.addRecipe(recipe);
    }
}
