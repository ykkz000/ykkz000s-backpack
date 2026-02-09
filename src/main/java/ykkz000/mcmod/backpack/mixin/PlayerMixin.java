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

import net.minecraft.world.ItemStackWithSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ykkz000.mcmod.backpack.world.entity.player.BackpackPlayer;
import ykkz000.mcmod.backpack.world.inventory.BackpackContainer;

@Mixin(Player.class)
public class PlayerMixin implements BackpackPlayer {
    @Unique
    private BackpackContainer backpackInventory = new BackpackContainer();

    @Override
    public BackpackContainer ykkz000_sBackpack$getBackpackInventory() {
        return this.backpackInventory;
    }

    @Override
    public void ykkz000_sBackpack$setBackpackInventory(final BackpackContainer backpackInventory) {
        this.backpackInventory = backpackInventory;
    }

    @Inject(method = "readAdditionalSaveData(Lnet/minecraft/world/level/storage/ValueInput;)V", at = @At("RETURN"))
    protected void readAdditionalSaveData(ValueInput input, CallbackInfo ci) {
        this.backpackInventory.fromSlots(input.listOrEmpty("ykkz000s-backpack-inventory", ItemStackWithSlot.CODEC));
    }

    @Inject(method = "addAdditionalSaveData(Lnet/minecraft/world/level/storage/ValueOutput;)V", at = @At("RETURN"))
    protected void addAdditionalSaveData(ValueOutput output, CallbackInfo ci) {
        this.backpackInventory.storeAsSlots(output.list("ykkz000s-backpack-inventory", ItemStackWithSlot.CODEC));
    }
}
