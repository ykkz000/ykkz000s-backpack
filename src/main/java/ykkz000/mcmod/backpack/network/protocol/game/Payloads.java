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
import ykkz000.mcmod.backpack.Ykkz000sBackpack;
import ykkz000.mcmod.backpack.world.inventory.BackpackContainer;

public class Payloads {
    public static final CustomPacketPayload.TypeAndCodec<? super RegistryFriendlyByteBuf, ServerboundOpenBackpackPayload> OPEN_BACKPACK = registerServerbound("open_backpack", ServerboundOpenBackpackPayload.CODEC, (_, context) -> {
        ServerPlayer player = context.player();
        player.openMenu(
                new SimpleMenuProvider((containerId, inventory, _) -> ChestMenu.sixRows(containerId, inventory, player.ykkz000_sBackpack$getBackpackInventory()), BackpackContainer.CONTAINER_TITLE)
        );
    });
    public static final CustomPacketPayload.TypeAndCodec<? super RegistryFriendlyByteBuf, ServerboundPackBackpackPayload> PACK_BACKPACK = registerServerbound("pack_backpack", ServerboundPackBackpackPayload.CODEC, (_, context) -> {
        ServerPlayer player = context.player();
        player.ykkz000_sBackpack$getBackpackInventory().ykkz000_sBackpack$pack();
    });

    public static void bootstrap() {
    }

    private static <T extends CustomPacketPayload> CustomPacketPayload.TypeAndCodec<? super RegistryFriendlyByteBuf, T> registerServerbound(final String name, final StreamCodec<RegistryFriendlyByteBuf, T> codec, final  ServerPlayNetworking.PlayPayloadHandler<T> handler) {
        CustomPacketPayload.TypeAndCodec<? super RegistryFriendlyByteBuf, T> typeAndCodec = PayloadTypeRegistry.serverboundPlay().register(new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath(Ykkz000sBackpack.MOD_ID, name)), codec);
        ServerPlayNetworking.registerGlobalReceiver(typeAndCodec.type(), handler);
        return typeAndCodec;
    }
}
