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

package ykkz000.mcmod.backpack.keymapping.client;

import com.mojang.blaze3d.platform.InputConstants;
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
