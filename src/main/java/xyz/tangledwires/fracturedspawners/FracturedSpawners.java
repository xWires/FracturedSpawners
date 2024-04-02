package xyz.tangledwires.fracturedspawners;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

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
import xyz.tangledwires.fracturedspawners.events.UpdateNotifier;

public class FracturedSpawners extends JavaPlugin {
    public String version = getDescription().getVersion();
	public String latestVersion;
	public boolean isOutdated = false;
    @Override
    public void onEnable() {
        int pluginId = 21486;
        Metrics metrics = new Metrics(this, pluginId);
        
        setupRecipes();
        getServer().getPluginManager().registerEvents(new DropFracturedSpawner(), this);
        getServer().getPluginManager().registerEvents(new RepairedSpawnerRecipe(), this);
        getServer().getPluginManager().registerEvents(new NoPlaceFracturedSpawner(), this);
        getServer().getPluginManager().registerEvents(new UpdateNotifier(), this);
        /*
		 * This checks whether the plugin is up to date.
		 * The URL below returns the latest build number from Jenkins.
		 * 
		 * It gets the latest build number and compares it with the version string of this instance of FracturedSpawners.
		 */
		HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://ci.tangledwires.xyz/job/FracturedSpawners/lastSuccessfulBuild/buildNumber"))
                .GET()
                .build();
		try {
			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			int newestVersion = Integer.parseInt(response.body());
			latestVersion = Integer.toString(newestVersion);
			if (newestVersion > Integer.parseInt(getDescription().getVersion())) {
				isOutdated = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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