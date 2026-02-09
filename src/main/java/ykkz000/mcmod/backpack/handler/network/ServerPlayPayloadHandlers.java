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

package ykkz000.mcmod.backpack.handler.network;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.ChestMenu;
import ykkz000.mcmod.backpack.network.protocol.game.Payloads;
import ykkz000.mcmod.backpack.network.protocol.game.ServerboundOpenBackpackPackerPayload;
import ykkz000.mcmod.backpack.world.inventory.BackpackContainer;

public class ServerPlayPayloadHandlers {
    public static void bootstrap() {
        ServerPlayNetworking.registerGlobalReceiver(Payloads.OPEN_BACKPACK.type(), ServerPlayPayloadHandlers::onOpenBackpackPayload);
    }

    protected static void onOpenBackpackPayload(ServerboundOpenBackpackPackerPayload payload, ServerPlayNetworking.Context context) {
        ServerPlayer player = context.player();
        player.openMenu(
                new SimpleMenuProvider((containerId, inventory, _) -> ChestMenu.sixRows(containerId, inventory, player.ykkz000_sBackpack$getBackpackInventory()), BackpackContainer.CONTAINER_TITLE)
        );
    }
}
