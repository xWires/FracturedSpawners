package xyz.tangledwires.fracturedspawners.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;
import xyz.tangledwires.fracturedspawners.util.PersistantDataContainerUtils;

public class NoPlaceFracturedSpawner implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (event.getBlock().getType() == Material.SPAWNER) {
            ItemStack is = event.getItemInHand();
            if (PersistantDataContainerUtils.isFracturedSpawner(is)) {
                event.getPlayer().sendMessage(ChatColor.RED + "You can't place a fractured spawner!");
                event.setCancelled(true);
            }
        }
    }
}
