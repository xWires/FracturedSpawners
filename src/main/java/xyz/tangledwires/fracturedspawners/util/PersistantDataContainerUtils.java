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
     * Returns whether an ItemStack is a Fractured Spawner
     * 
     * @param i The ItemStack to check.
     * @return Either true or false.
     */
    public static boolean isFracturedSpawner(ItemStack i) {
        ItemMeta m = i.getItemMeta();
        if (m != null) {
            PersistentDataContainer pdc = m.getPersistentDataContainer();
            if (pdc.get(namespace, PersistentDataType.BOOLEAN) != null) {
                return pdc.get(namespace, PersistentDataType.BOOLEAN);
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }
    /**
     * Sets whether an ItemStack is a Fractured Spawner
     * 
     * @param i The ItemStack to set the flag on.
     * @param b Either true or false.
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