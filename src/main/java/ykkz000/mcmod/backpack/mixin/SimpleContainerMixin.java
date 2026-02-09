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

package ykkz000.mcmod.backpack.mixin;

import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import ykkz000.mcmod.backpack.world.PackableContainer;

@Mixin(SimpleContainer.class)
public abstract class SimpleContainerMixin implements PackableContainer {
    @Final
    @Shadow
    private int size;

    @Shadow
    protected abstract void moveItemsBetweenStacks(final ItemStack sourceStack, final ItemStack targetStack);

    @Shadow
    public abstract ItemStack getItem(final int slot);

    @Override
    public void ykkz000_sBackpack$pack() {
        for (int i = 1; i < this.size; i++) {
            ItemStack sourceStack = this.getItem(i);
            for (int j = 0; j < i; j++) {
                ItemStack targetStack = this.getItem(j);
                if (targetStack.isEmpty() || ItemStack.isSameItemSameComponents(targetStack, sourceStack)) {
                    this.moveItemsBetweenStacks(sourceStack, targetStack);
                    if (sourceStack.isEmpty()) {
                        break;
                    }
                }
            }
        }
    }
}
