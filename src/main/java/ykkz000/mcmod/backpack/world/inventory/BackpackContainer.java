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

package ykkz000.mcmod.backpack.world.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.ItemStackWithSlot;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.ContainerUser;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.Nullable;
import ykkz000.mcmod.backpack.network.protocol.game.Payloads;

import java.util.Objects;

/**
 * Simple container for backpack.
 *
 * @author ykkz000
 */
public class BackpackContainer extends SimpleContainer {
    /**
     * @apiNote This constant integer is matched with the {@link ChestMenu#sixRows(int, Inventory, Container)}. Change this constant means you should overwrite the related code in {@link Payloads}.
     */
    public static final int SIZE = 54;
    @Nullable
    private ContainerUser containerUser;

    public BackpackContainer() {
        super(SIZE);
    }

    @Override
    public boolean stillValid(Player player) {
        return Objects.equals(this.containerUser, player) && super.stillValid(player);
    }

    @Override
    public void startOpen(ContainerUser containerUser) {
        super.startOpen(containerUser);
        this.containerUser = containerUser;
    }

    @Override
    public void stopOpen(ContainerUser containerUser) {
        super.stopOpen(containerUser);
        this.containerUser = null;
    }

    public void fromSlots(final ValueInput.TypedInputList<ItemStackWithSlot> list) {
        for (int i = 0; i < this.getContainerSize(); i++) {
            this.setItem(i, ItemStack.EMPTY);
        }

        for (ItemStackWithSlot item : list) {
            if (item.isValidInContainer(this.getContainerSize())) {
                this.setItem(item.slot(), item.stack());
            }
        }
    }

    public void storeAsSlots(final ValueOutput.TypedOutputList<ItemStackWithSlot> output) {
        for (int i = 0; i < this.getContainerSize(); i++) {
            ItemStack itemStack = this.getItem(i);
            if (!itemStack.isEmpty()) {
                output.add(new ItemStackWithSlot(i, itemStack));
            }
        }
    }
}
