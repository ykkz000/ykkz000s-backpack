package ykkz000.mcmod.backpack.world.entity.player;

import ykkz000.mcmod.backpack.world.inventory.BackpackContainer;

/**
 * This interface is for mixin interface injection so that {@link net.minecraft.world.entity.player.Player} can have a backpack inventory.
 *
 * @author ykkz000
 * @apiNote This interface injection can be used in other mods.
 * @see ykkz000.mcmod.backpack.mixin.PlayerMixin
 */
public interface BackpackPlayer {
    /**
     * Get the backpack inventory.
     *
     * @return the backpack inventory
     */
    default BackpackContainer ykkz000_sBackpack$getBackpackInventory() {
        throw new UnsupportedOperationException("This default implementation is for mixin interface injection and must be overridden.");
    }

    /**
     * Set the backpack inventory.
     *
     * @param backpackInventory the backpack inventory
     */
    default void ykkz000_sBackpack$setBackpackInventory(final BackpackContainer backpackInventory) {
        throw new UnsupportedOperationException("This default implementation is for mixin interface injection and must be overridden.");
    }
}
