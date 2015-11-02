package simple.block.chest.christmas;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;
import simple.block.chest.SimpleBlockChestAdapter;

public class BlockChristmasChest extends SimpleBlockChestAdapter {

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityChristmasChest();
    }

    @Override
    public ILockableContainer getLockableContainer(World worldIn, BlockPos pos) {
        TileEntity tileentity = worldIn.getTileEntity(pos);
        return tileentity instanceof TileEntityChristmasChest ? (TileEntityChristmasChest) tileentity : null;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public int getRenderType() {
        return -1;
    }
    
}
