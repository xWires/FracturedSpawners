package xyz.tangledwires.fracturedspawners.util;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import xyz.tangledwires.fracturedspawners.FracturedSpawners;

public class PersistantDataContainerUtils {
    // Setup the namespace key "fracturedspawners:isFracturedSpawner"
    private static NamespacedKey fracturedNamespace = new NamespacedKey(FracturedSpawners.getPlugin(FracturedSpawners.class), "isFracturedSpawner");
    private static NamespacedKey repairedNamespace = new NamespacedKey(FracturedSpawners.getPlugin(FracturedSpawners.class), "isRepairedSpawner");

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
            return isFracturedSpawner(pdc);
        }
        else {
            return false;
        }
    }
    /**
     * Returns whether an ItemStack is a Fractured Spawner
     * 
     * @param pdc The PersistentDataContainer to check.
     * @return Either true or false.
     */
    public static boolean isFracturedSpawner(PersistentDataContainer pdc) {
        if (pdc.get(fracturedNamespace, PersistentDataType.BOOLEAN) != null) {
            return pdc.get(fracturedNamespace, PersistentDataType.BOOLEAN);
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
            pdc.set(fracturedNamespace, PersistentDataType.BOOLEAN, b);
            i.setItemMeta(m);
        }
    }
    /**
     * Sets whether a PersistentDataContainer is a Fractured Spawner
     * 
     * @param pdc The PersistentDataContainer to set the flag on.
     * @param b Either true or false.
     */
    public static void setIsFracturedSpawner(PersistentDataContainer pdc, boolean b) {
        pdc.set(fracturedNamespace, PersistentDataType.BOOLEAN, b);
    }
    /**
     * Returns whether an ItemStack is a Repaired Spawner
     * 
     * @param i The ItemStack to check.
     * @return Either true or false.
     */
    public static boolean isRepairedSpawner(ItemStack i) {
        ItemMeta m = i.getItemMeta();
        if (m != null) {
            PersistentDataContainer pdc = m.getPersistentDataContainer();
            return isRepairedSpawner(pdc);
        }
        else {
            return false;
        }
    }
    /**
     * Returns whether an ItemStack is a Repaired Spawner
     * 
     * @param pdc The PersistentDataContainer to check.
     * @return Either true or false.
     */
    public static boolean isRepairedSpawner(PersistentDataContainer pdc) {
        if (pdc.get(repairedNamespace, PersistentDataType.BOOLEAN) != null) {
            return pdc.get(repairedNamespace, PersistentDataType.BOOLEAN);
        }
        else {
            return false;
        }
    }
    /**
     * Sets whether an ItemStack is a Repaired Spawner
     * 
     * @param i The ItemStack to set the flag on.
     * @param b Either true or false.
     */
    public static void setIsRepairedSpawner(ItemStack i, boolean b) {
        ItemMeta m = i.getItemMeta();
        if (m != null) {
            PersistentDataContainer pdc = m.getPersistentDataContainer();
            pdc.set(repairedNamespace, PersistentDataType.BOOLEAN, b);
            i.setItemMeta(m);
        }
    }
    /**
     * Sets whether a PersistentDataContainer is a Repaired Spawner
     * 
     * @param pdc The PersistentDataContainer to set the flag on.
     * @param b Either true or false.
     */
    public static void setIsRepairedSpawner(PersistentDataContainer pdc, boolean b) {
        pdc.set(repairedNamespace, PersistentDataType.BOOLEAN, b);
    }
}