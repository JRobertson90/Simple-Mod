package jayperdu_simple.block;

import java.util.Random;
import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.*;
import net.minecraft.util.*;

public class BlockLightAir extends Block {

	public BlockLightAir() {
		super(Material.vine); // WHy is this vine!?
		setLightLevel(1.0F);
		setLightOpacity(0);
		setHardness(-1);
		setResistance(6000000);
		setUnlocalizedName("light_block_air");
		if (BlockLight.DEBUG_MODE) {
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
	public boolean isAir(IBlockAccess world, BlockPos pos)  {
		return true;
	}
	
	@Override
	public boolean canBeReplacedByLeaves(IBlockAccess world, BlockPos pos) {
		return true;
	}
	
	@Override
	public boolean isLeaves(IBlockAccess world, BlockPos pos) {
		// This is needed so that trees can grow close to this block.
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

}
