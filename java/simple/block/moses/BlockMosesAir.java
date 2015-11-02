package simple.block.moses;

import java.util.Random;

import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.*;
import net.minecraft.util.*;

public class BlockMosesAir extends Block {

	private static final boolean DEBUG_MODE = false;
	
	public BlockMosesAir() {
		super(Material.vine);
		setLightLevel(1.0F);
		setLightOpacity(0);
		setHardness(-1);
		setResistance(6000000);
		if (DEBUG_MODE) {
			setBlockBounds(3/8.0F, 3/8.0F, 3/8.0F, 5/8.0F, 5/8.0F, 5/8.0F);
		}
		else {
			setBlockBounds(0F, 0F, 0F, 0F, 0F, 0F);
		}
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
    
	@Override
	public boolean isAir(IBlockAccess world, BlockPos pos) {
		return false;
	}
	
	@Override
	public boolean canBeReplacedByLeaves(IBlockAccess world, BlockPos pos) {
		return true;
	}
	

	@Override
	public int getRenderType() {
		return DEBUG_MODE ? super.getRenderType() : -1;
	}

	@Override
	public boolean isLeaves(IBlockAccess world, BlockPos pos) {
		return true;
	}

	@Override
	public int quantityDropped(Random par1Random) {
		return 0;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
		return null;
	}

}
