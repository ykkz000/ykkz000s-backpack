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
public abstract class PlayerMixin implements BackpackPlayer {
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
