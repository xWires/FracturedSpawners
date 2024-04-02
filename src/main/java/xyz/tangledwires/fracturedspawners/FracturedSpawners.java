package xyz.tangledwires.fracturedspawners;

import org.bstats.bukkit.Metrics;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import xyz.tangledwires.fracturedspawners.events.DropFracturedSpawner;
import xyz.tangledwires.fracturedspawners.events.NoPlaceFracturedSpawner;
import xyz.tangledwires.fracturedspawners.events.RepairedSpawnerRecipe;

public class FracturedSpawners extends JavaPlugin {
    @Override
    public void onEnable() {
        int pluginId = 21486;
        Metrics metrics = new Metrics(this, pluginId);
        
        setupRecipes();
        getServer().getPluginManager().registerEvents(new DropFracturedSpawner(), this);
        getServer().getPluginManager().registerEvents(new RepairedSpawnerRecipe(), this);
        getServer().getPluginManager().registerEvents(new NoPlaceFracturedSpawner(), this);
        getLogger().info("FracturedSpawners enabled!");
    }
    @Override
    public void onDisable() {
        getLogger().info("FracturedSpawners disabled!");
    }
    public void setupRecipes() {
        NamespacedKey key = new NamespacedKey(this, "repaired_spawner");
        ItemStack resultSpawner = new ItemStack(Material.SPAWNER);
        ItemMeta m = resultSpawner.getItemMeta();
        m.setDisplayName(ChatColor.GOLD + "Repaired Spawner");
        ShapedRecipe spawnerRecipe = new ShapedRecipe(key, resultSpawner);
        spawnerRecipe.shape("*#*", "#@#", "*#*");
        spawnerRecipe.setIngredient('*', Material.DIAMOND);
        spawnerRecipe.setIngredient('#', Material.ECHO_SHARD);
        spawnerRecipe.setIngredient('@', Material.SPAWNER);
        getServer().addRecipe(spawnerRecipe);
    }
}