package folk.sisby.antique_atlas.util;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.world.PersistentState;


public class AtlasManager extends PersistentState {
    public static final String TAG_NEXT_ID = "aaNextID";
    private int nextId = 1;

    public AtlasManager() {
    }

    public int getNextAtlasId() {
        int id = nextId++;
        markDirty();
        return id;
    }

    public static AtlasManager fromNbt(NbtCompound compound) {
        AtlasManager data = new AtlasManager();
        if (compound.contains(TAG_NEXT_ID, NbtElement.NUMBER_TYPE)) {
            data.nextId = compound.getInt(TAG_NEXT_ID);
        } else {
            data.nextId = 1;
        }
        return data;
    }

    @Override
    public NbtCompound writeNbt(NbtCompound compound) {
        compound.putInt(TAG_NEXT_ID, nextId);
        return compound;
    }
}
