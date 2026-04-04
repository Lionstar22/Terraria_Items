package me.carson.terrariaItems.recipeManagers;

import me.carson.terrariaItems.listenersHandler.WorldDataHandler;
import me.carson.terrariaItems.materialsFolder.materials.HallowedBar;
import me.carson.terrariaItems.miscFolder.BasicItems.*;
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

public class MiscRecipeManager implements Listener {

    private final Plugin plugin;
    private final List<NamespacedKey> preHardmodeRecipes;
    private final List<NamespacedKey> HardmodeRecipes;
    private final WorldDataHandler worldInstance=WorldDataHandler.getInstance();

    public MiscRecipeManager(Plugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);

        preHardmodeRecipes = List.of(
        );

        HardmodeRecipes = List.of(
                new NamespacedKey(plugin, "PickaxeAxe")
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
        registerPickaxeAxeRecipe();
    }

    private void registerPickaxeAxeRecipe(){
        ItemStack axe= PickaxeAxe.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "PickaxeAxe");
        ShapedRecipe recipe = new ShapedRecipe(key, axe);
        recipe.shape("HHH","HS "," S ");
        recipe.setIngredient('S', Material.STICK);
        recipe.setIngredient('H', new RecipeChoice.ExactChoice( HallowedBar.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(recipe);
    }
}
