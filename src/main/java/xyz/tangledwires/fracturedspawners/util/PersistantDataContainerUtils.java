package xyz.tangledwires.fracturedspawners.util;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import xyz.tangledwires.fracturedspawners.FracturedSpawners;

public class PersistantDataContainerUtils {
    // Setup the namespace key "fracturedspawners:isFracturedSpawner"
    static NamespacedKey namespace = new NamespacedKey(FracturedSpawners.getPlugin(FracturedSpawners.class), "isFracturedSpawner");

    private PersistantDataContainerUtils() {}

    /**
     * Returns the list of command triggers on an item.
     * 
     * @param i The ItemStack to get the triggers from.
     * @return A JSON formatted list of command triggers
     */
    public static boolean isFracturedSpawner(ItemStack i) {
        ItemMeta m = i.getItemMeta();
        if (m != null) {
            PersistentDataContainer pdc = m.getPersistentDataContainer();
            return pdc.get(namespace, PersistentDataType.BOOLEAN);
        }
        else {
            return false;
        }
    }
    /**
     * Sets the list of command triggers on an item.
     * 
     * @param i The ItemStack to set the triggers on
     * @param s A JSON formatted string to set as the list of command triggers
     */
    public static void setIsFracturedSpawner(ItemStack i, boolean b) {
        ItemMeta m = i.getItemMeta();
        if (m != null) {
            PersistentDataContainer pdc = m.getPersistentDataContainer();
            pdc.set(namespace, PersistentDataType.BOOLEAN, b);
            i.setItemMeta(m);
        }
    }
}