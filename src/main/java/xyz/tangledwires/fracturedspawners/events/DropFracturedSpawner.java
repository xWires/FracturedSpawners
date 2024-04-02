package xyz.tangledwires.fracturedspawners.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

import xyz.tangledwires.fracturedspawners.SpawnerItems;

public class DropFracturedSpawner implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getBlock().getType() == Material.SPAWNER) {
            Block block = event.getBlock();
            ItemStack fs = SpawnerItems.getFracturedSpawner();
            BlockStateMeta bsm = (BlockStateMeta) fs.getItemMeta();
            CreatureSpawner cs = (CreatureSpawner) bsm.getBlockState();

            cs.setSpawnedType(((CreatureSpawner) block.getState()).getSpawnedType());
            bsm.setBlockState(cs);
            fs.setItemMeta(bsm);
            
            block.getWorld().dropItemNaturally(block.getLocation(), fs);
        }
    }
}
