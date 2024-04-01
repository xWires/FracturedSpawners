package xyz.tangledwires.fracturedspawners.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import xyz.tangledwires.fracturedspawners.SpawnerItems;

public class DropFracturedSpawner implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getBlock().getType() == Material.SPAWNER) {
            Block block = event.getBlock();
            block.getWorld().dropItemNaturally(block.getLocation(), SpawnerItems.getFracturedSpawner());
        }
    }
}
