package ykkz000.mcmod.backpack.mixin;

import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ykkz000.mcmod.backpack.world.entity.player.BackpackPlayer;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin {
    @Inject(method = "restoreFrom(Lnet/minecraft/server/level/ServerPlayer;Z)V", at = @At("RETURN"))
    private void restoreFrom(ServerPlayer oldPlayer, boolean restoreAll, CallbackInfo ci) {
        ((BackpackPlayer) this).ykkz000_sBackpack$setBackpackInventory(oldPlayer.ykkz000_sBackpack$getBackpackInventory());
    }
}
