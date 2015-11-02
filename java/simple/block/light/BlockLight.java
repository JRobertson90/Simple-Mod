package simple.block.light;

import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.*;

public class BlockLight extends Block {

	public BlockLight() {
		super(Material.cloth);
	}
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		
		if ( ! worldIn.isRemote) {
			Floodlight.diffuseLight(worldIn, pos, true);
		}
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		
		Floodlight.diffuseLight(worldIn, pos, false);
		super.breakBlock(worldIn, pos, state);
	}

	@Override
	public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
		//Floodlight.checkIfShouldStay(world, x, y, z);
	}
	
}
