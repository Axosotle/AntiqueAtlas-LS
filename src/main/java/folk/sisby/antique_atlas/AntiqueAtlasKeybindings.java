package folk.sisby.antique_atlas;

import folk.sisby.antique_atlas.gui.AtlasScreen;
import folk.sisby.antique_atlas.item.AtlasItems;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;


public class AntiqueAtlasKeybindings {
    public static final KeyBinding ATLAS_KEYMAPPING = new KeyBinding("key.antique_atlas.open", InputUtil.Type.KEYSYM, 77, "key.antique_atlas.category");

    public static void init() {
        KeyBindingHelper.registerKeyBinding(ATLAS_KEYMAPPING);
        ClientTickEvents.END_CLIENT_TICK.register(AntiqueAtlasKeybindings::onClientTick);
    }

    public static boolean hasSpecialItem(PlayerEntity player) {
        for (ItemStack itemStack : player.getInventory().main) {
            if (itemStack.getItem() == AtlasItems.EMPTY_ATLAS) { // Replace YOUR_SPECIAL_ITEM with the actual item
                return true;
            }
        }
        return false;
    }

    public static void onClientTick(MinecraftClient client) {
        var player = client.player;
        while (ATLAS_KEYMAPPING.wasPressed()) {
            assert player != null;
            if (!hasSpecialItem(player)) break;
            if (client.currentScreen == null) {
                AtlasScreen screen = new AtlasScreen();
                screen.init();
                screen.prepareToOpen();
                screen.tick();
                client.setScreen(screen);
            }
        }
    }
}
