package ykkz000.mcmod.backpack.network.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import ykkz000.mcmod.backpack.ClientComponents;
import ykkz000.mcmod.backpack.network.protocol.game.Payloads;

@Environment(EnvType.CLIENT)
public class ClientPayloadHandlers {
    public static void bootstrap() {
        ClientPlayNetworking.registerGlobalReceiver(Payloads.PACK_BACKPACK_RESULT.type(), ( payload, context)-> context.player().displayClientMessage(payload.success() ? ClientComponents.PACK_BACKPACK_RESULT_SUCCESS : ClientComponents.PACK_BACKPACK_RESULT_FAILURE, false));
    }
}
