package simple.block.chest.crafting;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;
import simple.block.chest.SimpleBlockChestAdapter;

public class BlockCraftingChest extends SimpleBlockChestAdapter {

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityCraftingChest();
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) {
    	this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
    }

    @Override
    public ILockableContainer getLockableContainer(World worldIn, BlockPos pos) {
        TileEntity tileentity = worldIn.getTileEntity(pos);
        return tileentity instanceof TileEntityCraftingChest ? (TileEntityCraftingChest) tileentity : null;
    }

}