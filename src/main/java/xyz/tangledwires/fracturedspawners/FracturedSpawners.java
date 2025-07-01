package xyz.tangledwires.fracturedspawners;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.bstats.bukkit.Metrics;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import xyz.tangledwires.fracturedspawners.events.DropSpawners;
import xyz.tangledwires.fracturedspawners.events.NoPlaceFracturedSpawner;
import xyz.tangledwires.fracturedspawners.events.PlaceRepairedSpawner;
import xyz.tangledwires.fracturedspawners.events.RepairedSpawnerRecipe;
import xyz.tangledwires.fracturedspawners.events.UpdateNotifier;

public class FracturedSpawners extends JavaPlugin {
    public String version = getDescription().getVersion();
	public String latestVersion;
	public boolean isOutdated = false;
    @Override
    public void onEnable() {
        int pluginId = 21486;
        @SuppressWarnings("unused")
        Metrics metrics = new Metrics(this, pluginId);
        
        FileConfiguration config = this.getConfig();
        ArrayList<String> defaultAllowedTools = new ArrayList<String>();
        defaultAllowedTools.add("*");
        config.addDefault("silkTouchRequired", false);
        config.addDefault("spawnerDropsInCreative", false);
        config.addDefault("allowedTools", defaultAllowedTools);
        config.addDefault("repairedSpawnersStayRepaired", true);
        config.options().copyDefaults(true);
        this.saveConfig();

        setupRecipes();

        getServer().getPluginManager().registerEvents(new DropSpawners(), this);
        getServer().getPluginManager().registerEvents(new RepairedSpawnerRecipe(), this);
        getServer().getPluginManager().registerEvents(new NoPlaceFracturedSpawner(), this);
        getServer().getPluginManager().registerEvents(new UpdateNotifier(), this);
        getServer().getPluginManager().registerEvents(new PlaceRepairedSpawner(), this);
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
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("fracturedspawners")) {
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("reload")) {
                    this.reloadConfig();
                    sender.sendMessage(ChatColor.GREEN + "Config reloaded!");
                    return true;
                }
            }
        }
        return false;
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
    public List<Material> getAllowedTools() {
        FileConfiguration config = this.getConfig();
        List<String> allowedToolsStrings = config.getStringList("allowedTools");
        ArrayList<Material> allowedToolsMaterials = new ArrayList<Material>();
        if (allowedToolsStrings.contains("*")) {
            for (Material m : Material.values()) {
                allowedToolsMaterials.add(m);
            }
        }
        else {
            for (String allowed : allowedToolsStrings) {
                allowedToolsMaterials.add(Material.matchMaterial(allowed));
            }
        }
        return allowedToolsMaterials;
    }
}