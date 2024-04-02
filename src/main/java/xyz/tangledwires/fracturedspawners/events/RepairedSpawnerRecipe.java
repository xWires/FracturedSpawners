package xyz.tangledwires.fracturedspawners.events;

import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

import xyz.tangledwires.fracturedspawners.SpawnerItems;
import xyz.tangledwires.fracturedspawners.util.PersistantDataContainerUtils;

public class RepairedSpawnerRecipe implements Listener {
    @EventHandler
    public void onPrepareItemCraft(PrepareItemCraftEvent event) {
        CraftingInventory inventory = event.getInventory();
        ItemStack[] items = inventory.getMatrix();

        if (
            items[0] != null && items[0].getType() == Material.DIAMOND &&
            items[1] != null && items[1].getType() == Material.ECHO_SHARD &&
            items[2] != null && items[2].getType() == Material.DIAMOND &&
            items[3] != null && items[3].getType() == Material.ECHO_SHARD &&
            items[4] != null && items[4].getType() == Material.SPAWNER && PersistantDataContainerUtils.isFracturedSpawner(items[4]) &&
            items[5] != null && items[5].getType() == Material.ECHO_SHARD &&
            items[6] != null && items[6].getType() == Material.DIAMOND &&
            items[7] != null && items[7].getType() == Material.ECHO_SHARD &&
            items[8] != null && items[8].getType() == Material.DIAMOND
        ) {
            BlockStateMeta oldBSM = (BlockStateMeta) items[4].getItemMeta();
            CreatureSpawner oldCS = (CreatureSpawner) oldBSM.getBlockState();

            ItemStack rs = SpawnerItems.getRepairedSpawner();
            BlockStateMeta newBSM = (BlockStateMeta) rs.getItemMeta();
            CreatureSpawner newCS = (CreatureSpawner) newBSM.getBlockState();

            newCS.setSpawnedType(oldCS.getSpawnedType());
            newBSM.setBlockState(newCS);
            rs.setItemMeta(newBSM);
            inventory.setResult(rs);
        }
        else if (
            items[0] != null && items[0].getType() == Material.DIAMOND &&
            items[1] != null && items[1].getType() == Material.ECHO_SHARD &&
            items[2] != null && items[2].getType() == Material.DIAMOND &&
            items[3] != null && items[3].getType() == Material.ECHO_SHARD &&
            items[4] != null && items[4].getType() == Material.SPAWNER &&
            items[5] != null && items[5].getType() == Material.ECHO_SHARD &&
            items[6] != null && items[6].getType() == Material.DIAMOND &&
            items[7] != null && items[7].getType() == Material.ECHO_SHARD &&
            items[8] != null && items[8].getType() == Material.DIAMOND
        ) {
            inventory.setResult(null);
        }
    }
}
