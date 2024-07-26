package folk.sisby.antique_atlas.item;

import com.sun.jna.platform.win32.WinBase;
import folk.sisby.antique_atlas.AntiqueAtlas;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.item.Item;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import net.minecraft.util.Identifier;
import folk.sisby.antique_atlas.item.EmptyAtlasItem;
import net.minecraft.world.World;

public class AtlasItems {

    public static final Item EMPTY_ATLAS = registerItem("empty_atlas", new EmptyAtlasItem(new Item.Settings()));

    public static final Item ATLAS = registerItem("atlas", new AtlasItem(new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(AntiqueAtlas.ID, name), item);
    }

    public static ItemStack getAtlasFromId(int atlasID) {
        ItemStack atlas = new ItemStack(ATLAS);
        atlas.getOrCreateNbt().putInt("atlasID", atlasID);

        return atlas;
    }

    public static void registerModItems() {
        AntiqueAtlas.LOGGER.info("Registering Mod Items for " + AntiqueAtlas.ID);
    }

}
