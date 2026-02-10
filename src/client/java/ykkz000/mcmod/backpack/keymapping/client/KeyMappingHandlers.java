package ykkz000.mcmod.backpack.keymapping.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;
import ykkz000.mcmod.backpack.Ykkz000sBackpack;
import ykkz000.mcmod.backpack.network.protocol.game.ServerboundOpenBackpackPayload;
import ykkz000.mcmod.backpack.network.protocol.game.ServerboundPackBackpackPayload;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Environment(EnvType.CLIENT)
public class KeyMappingHandlers {
    private static final Map<KeyMapping, Consumer<Minecraft>> handlers = new HashMap<>();
    public static final KeyMapping.Category KEY_MAPPING_CATEGORY_BACKPACK = KeyMapping.Category.register(Identifier.fromNamespaceAndPath(Ykkz000sBackpack.MOD_ID, "backpack"));
    public static final KeyMapping KEY_MAPPING_OPEN_BACKPACK = register("open_backpack", GLFW.GLFW_KEY_B, KEY_MAPPING_CATEGORY_BACKPACK, client-> {
        if (client.player != null) {
            ClientPlayNetworking.send(new ServerboundOpenBackpackPayload(0));
        }
    });
    public static final KeyMapping KEY_MAPPING_PACK_BACKPACK = register("pack_backpack", GLFW.GLFW_KEY_C, KEY_MAPPING_CATEGORY_BACKPACK, client-> {
        if (client.player != null) {
            ClientPlayNetworking.send(new ServerboundPackBackpackPayload(0));
        }
    });

    public static void bootstrap() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            Map<KeyMapping, Boolean> isKeyPressed = new HashMap<>();
            for (KeyMapping keyMapping : handlers.keySet()) {
                while (keyMapping.consumeClick()) {
                    isKeyPressed.put(keyMapping, true);
                }
            }
            for (Map.Entry<KeyMapping, Consumer<Minecraft>> entry : handlers.entrySet()) {
                if (isKeyPressed.getOrDefault(entry.getKey(), false)) {
                    entry.getValue().accept(client);
                }
            }
        });
    }

    private static KeyMapping register(String id, int defaultCode, KeyMapping.Category category, Consumer<Minecraft> handler) {
        KeyMapping keyMapping = KeyMappingHelper.registerKeyMapping(new KeyMapping("key." + Ykkz000sBackpack.MOD_ID + "." + id, InputConstants.Type.KEYSYM, defaultCode, category));
        handlers.put(keyMapping, handler);
        return keyMapping;
    }
}
