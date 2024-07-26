package folk.sisby.antique_atlas.item;

import folk.sisby.antique_atlas.AntiqueAtlas;
import folk.sisby.antique_atlas.WorldAtlasData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.Objects;

public class EmptyAtlasItem extends Item {
    public EmptyAtlasItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player,
                                            Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        if (world.isClient()) {
            world.playSound(player, player.getBlockPos(), SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.PLAYERS, 1F, 1F);
            return new TypedActionResult<>(ActionResult.SUCCESS, stack);
        }

        int atlasID = Objects.requireNonNull(AntiqueAtlas.getAtlasIdData(world)).getNextAtlasId();
        ItemStack atlasStack = AtlasItems.getAtlasFromId(atlasID);

        stack.decrement(1);
        if (stack.isEmpty()) {
            return new TypedActionResult<>(ActionResult.SUCCESS, atlasStack);
        } else {
            if (!player.getInventory().insertStack(atlasStack.copy())) {
                player.dropItem(atlasStack, true);
            }

            return new TypedActionResult<>(ActionResult.SUCCESS, stack);
        }
    }
}