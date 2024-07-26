package folk.sisby.antique_atlas.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class AtlasItem extends Item {

    public AtlasItem(Item.Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        System.out.println(stack.getOrCreateNbt());
        return new TypedActionResult<>(ActionResult.SUCCESS, stack);
    }

    public static int getAtlasID(ItemStack stack) {
        return stack.getOrCreateNbt().getInt("atlasID");
    }

    @Override
    public Text getName(ItemStack stack) {
        return Text.translatable(this.getTranslationKey(), getAtlasID(stack));
    }
    
}
