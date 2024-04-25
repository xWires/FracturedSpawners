package xyz.tangledwires.fracturedspawners;

import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;
import xyz.tangledwires.fracturedspawners.util.PersistantDataContainerUtils;

public class SpawnerItems {
    /**
     * Gets an ItemStack representing a blank fractured spawner.
     * 
     * @return An ItemStack representing a blank fractured spawner.
     */
    public static ItemStack getFracturedSpawner() {
        ItemStack stack = new ItemStack(Material.SPAWNER);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Fractured Spawner");
        stack.setItemMeta(meta);
        PersistantDataContainerUtils.setIsFracturedSpawner(stack, true);
        return stack;
    }
    /**
     * Gets an ItemStack describing a fractured spawner with the provided EntityType.
     * 
     * @param et The EntityType that should be contained in this spawner.
     * @return An ItemStack representing a fractured spawner with the specified EntityType.
     */
    public static ItemStack getFracturedSpawner(EntityType et) {
        ItemStack is = getFracturedSpawner();
        BlockStateMeta bsm = (BlockStateMeta) is.getItemMeta();
        CreatureSpawner cs = (CreatureSpawner) bsm.getBlockState();

        cs.setSpawnedType(et);
        bsm.setBlockState(cs);
        is.setItemMeta(bsm);

        return is;
    }
    /**
     * Gets an ItemStack representing a blank repaired spawner.
     * 
     * @return An ItemStack representing a blank repaired spawner.
     */
    public static ItemStack getRepairedSpawner() {
        ItemStack stack = new ItemStack(Material.SPAWNER);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Repaired Spawner");
        stack.setItemMeta(meta);
        PersistantDataContainerUtils.setIsRepairedSpawner(stack, true);
        return stack;
    }
    /**
     * Gets an ItemStack describing a repaired spawner with the provided EntityType.
     * 
     * @param et The EntityType that should be contained in this spawner.
     * @return An ItemStack representing a repaired spawner with the specified EntityType.
     */
    public static ItemStack getRepairedSpawner(EntityType et) {
        ItemStack is = getRepairedSpawner();
        BlockStateMeta bsm = (BlockStateMeta) is.getItemMeta();
        CreatureSpawner cs = (CreatureSpawner) bsm.getBlockState();

        cs.setSpawnedType(et);
        bsm.setBlockState(cs);
        is.setItemMeta(bsm);

        return is;
    }
}
