package ykkz000.mcmod.backpack;

import net.fabricmc.api.ModInitializer;

import ykkz000.mcmod.backpack.network.protocol.game.Payloads;

public class Ykkz000sBackpack implements ModInitializer {
    public static final String MOD_ID = "ykkz000s-backpack";

    @Override
    public void onInitialize() {
        Payloads.bootstrap();
    }
}