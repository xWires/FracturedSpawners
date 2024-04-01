package xyz.tangledwires.fracturedspawners;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;
import xyz.tangledwires.fracturedspawners.util.PersistantDataContainerUtils;

public class SpawnerItems {
    public static ItemStack getFracturedSpawner() {
        ItemStack stack = new ItemStack(Material.SPAWNER);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Fractured Spawner");
        stack.setItemMeta(meta);
        PersistantDataContainerUtils.setIsFracturedSpawner(stack, true);
        return stack;
    }
    public static ItemStack getRepairedSpawner() {
        ItemStack stack = new ItemStack(Material.SPAWNER);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Repaired Spawner");
        stack.setItemMeta(meta);
        return stack;
    }
}
