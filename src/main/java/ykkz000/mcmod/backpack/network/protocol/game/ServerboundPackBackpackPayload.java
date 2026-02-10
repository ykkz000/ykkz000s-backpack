package ykkz000.mcmod.backpack.network.protocol.game;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import ykkz000.mcmod.backpack.Ykkz000sBackpack;

public record ServerboundPackBackpackPayload(int i) implements CustomPacketPayload {
    public static final StreamCodec<RegistryFriendlyByteBuf, ServerboundPackBackpackPayload> CODEC = StreamCodec.composite(ByteBufCodecs.VAR_INT, ServerboundPackBackpackPayload::i, ServerboundPackBackpackPayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return new Type<>(Identifier.fromNamespaceAndPath(Ykkz000sBackpack.MOD_ID, "pack_backpack"));
    }
}
