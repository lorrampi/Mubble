package hugman.mod.objects.block;

import hugman.mod.Mubble;
import hugman.mod.init.elements.MubbleBlocks;

public class BlockEmptyDrops extends net.minecraft.block.BlockEmptyDrops
{
    public BlockEmptyDrops(String name, Properties properties)
    {
        super(properties);
        setRegistryName(Mubble.MOD_ID, name);
        MubbleBlocks.register(this);
    }
}