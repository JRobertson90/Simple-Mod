package xaeroxe_jayperdu_acm.block;

import java.util.Random;

import xaeroxe_jayperdu_acm.SimpleMod;
import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.*;
import net.minecraft.util.*;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockLightAir extends Block {

	public static boolean DEBUG_MODE = false;
	public static final String NAME = "light_block_air";

	public BlockLightAir() {
		super(Material.air);
		setLightLevel(1.0F);
		setLightOpacity(0);
		setBlockUnbreakable();
		setUnlocalizedName(SimpleMod.ID + ":" + NAME);
		if (DEBUG_MODE) {
			setBlockBounds(3/8.0F, 3/8.0F, 3/8.0F, 5/8.0F, 5/8.0F, 5/8.0F);
		} else {
			setBlockBounds(0F, 0F, 0F, 0F, 0F, 0F);
		}
		GameRegistry.registerBlock(this, NAME);
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
    
	@Override
	public boolean isAir(IBlockAccess world, BlockPos pos)  {
		return true;
	}
	
	@Override
	public boolean canBeReplacedByLeaves(IBlockAccess world, BlockPos pos) {
		return true;
	}
	
	@Override
	public boolean isLeaves(IBlockAccess world, BlockPos pos) {
		// This is supposedly needed so that trees can grow close to this block.
		return true;
	}

	@Override
	public boolean isNormalCube() {
		return false;
	}

	@Override
	public boolean isFullCube() {
		return ! DEBUG_MODE;
	}
	
	@Override
	public int quantityDropped(Random par1Random) {
		return 0;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
		return null;
	}

	@Override
	public int getRenderType() {
		// -1 means don't render it (i.e. air)
		return DEBUG_MODE ? super.getRenderType() : -1;
	}

}
