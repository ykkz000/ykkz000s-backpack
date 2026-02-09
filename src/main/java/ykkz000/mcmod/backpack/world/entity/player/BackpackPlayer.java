/*
 * ykkz000's Backpack
 * Copyright © 2026  ykkz000
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

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
