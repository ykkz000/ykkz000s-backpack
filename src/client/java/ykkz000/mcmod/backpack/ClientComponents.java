package ykkz000.mcmod.backpack;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;

@Environment(EnvType.CLIENT)
public final class ClientComponents {
    public static final Component PACK_BACKPACK_RESULT_SUCCESS = Component.translatable("message.ykkz000s-backpack.pack_backpack_result.success");
    public static final Component PACK_BACKPACK_RESULT_FAILURE = Component.translatable("message.ykkz000s-backpack.pack_backpack_result.failure");
}
