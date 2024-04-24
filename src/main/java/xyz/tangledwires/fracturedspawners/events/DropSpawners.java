package xyz.tangledwires.fracturedspawners.events;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.configuration.Configuration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

import xyz.tangledwires.fracturedspawners.FracturedSpawners;
import xyz.tangledwires.fracturedspawners.SpawnerItems;
import xyz.tangledwires.fracturedspawners.util.PersistantDataContainerUtils;

public class DropSpawners implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getBlock().getType() == Material.SPAWNER) {
            Configuration config = FracturedSpawners.getPlugin(FracturedSpawners.class).getConfig();
            ItemStack mainHandItem = event.getPlayer().getInventory().getItemInMainHand();
            Block block = event.getBlock();
            ItemStack fs = SpawnerItems.getFracturedSpawner();
            BlockStateMeta bsm = (BlockStateMeta) fs.getItemMeta();
            CreatureSpawner cs = (CreatureSpawner) bsm.getBlockState();
            if (Boolean.parseBoolean(config.get("silkTouchRequired").toString()) && mainHandItem.getEnchantmentLevel(Enchantment.SILK_TOUCH) == 0) {
                return;
            }
            if (!FracturedSpawners.getPlugin(FracturedSpawners.class).getAllowedTools().contains(event.getPlayer().getInventory().getItemInMainHand().getType())) {
                return;
            }
            if (!Boolean.parseBoolean(config.get("spawnerDropsInCreative").toString()) && event.getPlayer().getGameMode() == GameMode.CREATIVE) {
                return;
            }
            if (Boolean.parseBoolean(config.get("repairedSpawnersStayRepaired").toString()) && PersistantDataContainerUtils.isRepairedSpawner(((CreatureSpawner)block.getState()).getPersistentDataContainer())) {
                dropRepairedSpawner(event);
                return;
            }

            cs.setSpawnedType(((CreatureSpawner) block.getState()).getSpawnedType());
            bsm.setBlockState(cs);
            fs.setItemMeta(bsm);
            
            block.getWorld().dropItemNaturally(block.getLocation(), fs);
        }
    }
    public void dropRepairedSpawner(BlockBreakEvent event) {
        Block block = event.getBlock();
        ItemStack fs = SpawnerItems.getRepairedSpawner();
        BlockStateMeta bsm = (BlockStateMeta) fs.getItemMeta();
        CreatureSpawner cs = (CreatureSpawner) bsm.getBlockState();

        cs.setSpawnedType(((CreatureSpawner) block.getState()).getSpawnedType());
        bsm.setBlockState(cs);
        fs.setItemMeta(bsm);
        
        block.getWorld().dropItemNaturally(block.getLocation(), fs);
    }
}
