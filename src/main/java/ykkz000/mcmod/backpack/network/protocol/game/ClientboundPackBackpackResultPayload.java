package ykkz000.mcmod.backpack.network.protocol.game;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record ClientboundPackBackpackResultPayload(boolean success) implements CustomPacketPayload {
    public static final StreamCodec<RegistryFriendlyByteBuf, ClientboundPackBackpackResultPayload> CODEC = StreamCodec.composite(ByteBufCodecs.BOOL, ClientboundPackBackpackResultPayload::success, ClientboundPackBackpackResultPayload::new);
    @Override
    public Type<? extends CustomPacketPayload> type() {
        return Payloads.PACK_BACKPACK_RESULT.type();
    }
}
