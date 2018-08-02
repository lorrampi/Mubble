package hugman.mod.objects.blocks;

import hugman.mod.Main;
import hugman.mod.init.BlockInit;
import hugman.mod.init.ItemInit;
import hugman.mod.util.handlers.SoundHandler;
import hugman.mod.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockNote extends Block implements IHasModel
{
	String type;
	public BlockNote(String type)
	{
		super(Material.ROCK);
		this.type = type;
		if(type == "normal")
		{
			setTranslationKey("note_block");
			setRegistryName("note_block");
		}
		if(type == "super")
		{
			setTranslationKey("super_note_block");
			setRegistryName("super_note_block");
		}
		setCreativeTab(Main.NINTENDO_BLOCKS);
		setHardness(1.4f);
		this.blockResistance = 10f;
		setSoundType(SoundType.STONE);
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance)
    {
        entityIn.fall(fallDistance, 0.0F);
    }
	
	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn)
    {
		if (Math.abs(entityIn.motionY) < 0.45D) entityIn.motionY = 0.5D;
        super.onEntityWalk(worldIn, pos, entityIn);
    }

	@Override
    public void onLanded(World worldIn, Entity entityIn)
    {
		BlockPos pos = new BlockPos(entityIn).down();
        final double x = pos.getX() + 0.5D;
        final double y = pos.getY() + 0.5D;
        final double z = pos.getZ() + 0.5D;
        if(entityIn instanceof EntityLivingBase)
        {
        	if(!entityIn.isSneaking())
    		{
    			if(type == "normal") entityIn.motionY = 0.9D;
    			if(type == "super") entityIn.motionY = 1.5D;
    	        worldIn.playSound(null, x, y, z, SoundHandler.BLOCK_NOTE_BLOCK_JUMP_HIGH, SoundCategory.BLOCKS, 1f, 1f);
    		}
    		else if(entityIn.isSneaking())
    		{
    			worldIn.playSound(null, x, y, z, SoundHandler.BLOCK_NOTE_BLOCK_JUMP, SoundCategory.BLOCKS, 1f, 1f);
    	        entityIn.motionY = 0.5D;
    		}
    		else
    		{
    			entityIn.motionY = 0D;
    		}
        }
	}
	
	@Override
	public void registerModels()
	{
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
}