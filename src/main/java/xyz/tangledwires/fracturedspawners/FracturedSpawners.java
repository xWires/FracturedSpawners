package xyz.tangledwires.fracturedspawners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class FracturedSpawners extends JavaPlugin {
    @Override
    public void onEnable() {
        setupRecipes();
        getLogger().info("FracturedSpawners enabled!");
    }
    @Override
    public void onDisable() {
        getLogger().info("FracturedSpawners disabled!");
    }
    public void setupRecipes() {
        NamespacedKey key = new NamespacedKey(this, "repairedSpawner");
        ItemStack resultSpawner = new ItemStack(Material.SPAWNER);
        ItemMeta m = resultSpawner.getItemMeta();
        m.setDisplayName(ChatColor.GOLD + "Repaired Spawner");
        ShapedRecipe spawnerRecipe = new ShapedRecipe(key, resultSpawner);
        spawnerRecipe.shape("*#*", "#@#", "*#*");
        spawnerRecipe.setIngredient('*', Material.DIAMOND);
        spawnerRecipe.setIngredient('#', Material.NETHERITE_SCRAP);
        spawnerRecipe.setIngredient('@', Material.SPAWNER);
        getServer().addRecipe(spawnerRecipe);
    }
}