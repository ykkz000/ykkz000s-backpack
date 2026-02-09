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
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import ykkz000.mcmod.backpack.Ykkz000sBackpack;

public class Payloads {
    public static final CustomPacketPayload.TypeAndCodec<? super RegistryFriendlyByteBuf, ServerboundOpenBackpackPackerPayload> OPEN_BACKPACK = registerC2S("open_backpack", ServerboundOpenBackpackPackerPayload.CODEC);

    public static void bootstrap() {
    }

    private static <T extends CustomPacketPayload> CustomPacketPayload.TypeAndCodec<? super RegistryFriendlyByteBuf, T> registerC2S(final String name, final StreamCodec<RegistryFriendlyByteBuf, T> codec) {
        CustomPacketPayload.Type<T> type = new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath(Ykkz000sBackpack.MOD_ID, name));
        return PayloadTypeRegistry.serverboundPlay().register(type, codec);
    }
}
