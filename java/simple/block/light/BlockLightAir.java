//------------------------------------------------------
//
//   Greg's Lighting - Floodlight Beam Block
//
//------------------------------------------------------
package simple.block.light;

import java.util.Random;
import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.*;
import net.minecraft.util.*;

public class BlockLightAir extends Block {

	public BlockLightAir() {
		super(Material.vine);
		setLightLevel(1.0F);
		setLightOpacity(0);
		setHardness(-1);
		setResistance(6000000);
		if (Floodlight.DEBUG_MODE)
			setBlockBounds(3/8.0F, 3/8.0F, 3/8.0F, 5/8.0F, 5/8.0F, 5/8.0F);
		else
			setBlockBounds(0F, 0F, 0F, 0F, 0F, 0F);
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
		// This is a bit screwy, but it's needed so that trees are not prevented from growing
		// near a floodlight beam.
		return true;
	}

	@Override
	public boolean isNormalCube() {
		return false;
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
		return -1;
	}

	@Override
	public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
	}
	
}
