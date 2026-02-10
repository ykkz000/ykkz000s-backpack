package ykkz000.mcmod.backpack;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import ykkz000.mcmod.backpack.keymapping.client.KeyMappingHandlers;
import ykkz000.mcmod.backpack.network.client.ClientPayloadHandlers;

@Environment(EnvType.CLIENT)
public class Ykkz000sBackpackClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		KeyMappingHandlers.bootstrap();
		ClientPayloadHandlers.bootstrap();
	}
}