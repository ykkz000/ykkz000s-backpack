package ykkz000.mcmod.backpack.network.protocol.game;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.ChestMenu;
import ykkz000.mcmod.backpack.Components;
import ykkz000.mcmod.backpack.Ykkz000sBackpack;

public class Payloads {
    public static final CustomPacketPayload.TypeAndCodec<? super RegistryFriendlyByteBuf, ServerboundOpenBackpackPayload> OPEN_BACKPACK = registerServerbound("open_backpack", ServerboundOpenBackpackPayload.CODEC, (_, context) -> {
        ServerPlayer player = context.player();
        player.openMenu(
                new SimpleMenuProvider((containerId, inventory, _) -> ChestMenu.sixRows(containerId, inventory, player.ykkz000_sBackpack$getBackpackInventory()), Components.TITLE_CONTAINER_BACKPACK)
        );
    });
    public static final CustomPacketPayload.TypeAndCodec<? super RegistryFriendlyByteBuf, ServerboundPackBackpackPayload> PACK_BACKPACK = registerServerbound("pack_backpack", ServerboundPackBackpackPayload.CODEC, (_, context) -> {
        ServerPlayer player = context.player();
        player.ykkz000_sBackpack$getBackpackInventory().ykkz000_sBackpack$pack();
        ServerPlayNetworking.send(player, new ClientboundPackBackpackResultPayload(true));
    });
    public static final CustomPacketPayload.TypeAndCodec<? super RegistryFriendlyByteBuf, ClientboundPackBackpackResultPayload> PACK_BACKPACK_RESULT = registerClientbound("pack_backpack_result", ClientboundPackBackpackResultPayload.CODEC);

    public static void bootstrap() {
    }

    private static <T extends CustomPacketPayload> CustomPacketPayload.TypeAndCodec<? super RegistryFriendlyByteBuf, T> registerServerbound(final String name, final StreamCodec<RegistryFriendlyByteBuf, T> codec, final ServerPlayNetworking.PlayPayloadHandler<T> handler) {
        CustomPacketPayload.TypeAndCodec<? super RegistryFriendlyByteBuf, T> typeAndCodec = PayloadTypeRegistry.serverboundPlay().register(new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath(Ykkz000sBackpack.MOD_ID, name)), codec);
        ServerPlayNetworking.registerGlobalReceiver(typeAndCodec.type(), handler);
        return typeAndCodec;
    }

    private static <T extends CustomPacketPayload> CustomPacketPayload.TypeAndCodec<? super RegistryFriendlyByteBuf, T> registerClientbound(final String name, final StreamCodec<RegistryFriendlyByteBuf, T> codec) {
        return PayloadTypeRegistry.clientboundPlay().register(new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath(Ykkz000sBackpack.MOD_ID, name)), codec);
    }
}
