package xyz.tangledwires.fracturedspawners.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.persistence.PersistentDataContainer;

import xyz.tangledwires.fracturedspawners.util.PersistantDataContainerUtils;

public class PlaceRepairedSpawner implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Block block = event.getBlock();
        if (block.getType() != Material.SPAWNER) {
            return;
        }
        ItemStack eventItemStack = event.getItemInHand();
        CreatureSpawner cs = ((CreatureSpawner) block.getState());
        if (PersistantDataContainerUtils.isRepairedSpawner(eventItemStack.getItemMeta().getPersistentDataContainer())) {
            PersistentDataContainer pdc = cs.getPersistentDataContainer();
            PersistantDataContainerUtils.setIsRepairedSpawner(pdc, true);
            cs.update();
        }
        BlockStateMeta itemBsm = (BlockStateMeta) eventItemStack.getItemMeta();
        CreatureSpawner itemCs = (CreatureSpawner) itemBsm.getBlockState();
        cs.setSpawnedType(itemCs.getSpawnedType());
        cs.update();
    }
}
