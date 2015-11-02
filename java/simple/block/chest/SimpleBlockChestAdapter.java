package simple.block.chest;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;

/**
 * Created by Jason Robertson on 11/1/15.
 */
public abstract class SimpleBlockChestAdapter extends BlockChest {

    public SimpleBlockChestAdapter() {
        super(0);
    }

    /** Returns a new instance of a block's tile entity class. Called on placing the block. */
    @Override abstract public TileEntity createTileEntity(World world, IBlockState state);
    @Override abstract public ILockableContainer getLockableContainer(World worldIn, BlockPos pos);

    @Override public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {}
    @Override public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {}
    @Override public IBlockState checkForSurroundingChests(World worldIn, BlockPos pos, IBlockState state) { return state; }
    @Override public boolean canPlaceBlockAt(World worldIn, BlockPos pos) { return true; }
}