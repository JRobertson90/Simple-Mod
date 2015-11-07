package simple.block.chest.christmas;

import net.minecraft.block.BlockChest;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;

public class BlockChristmasChest extends BlockChest {

    public BlockChristmasChest() {
        super(0);
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityChristmasChest();
    }

    @Override
    public ILockableContainer getLockableContainer(World worldIn, BlockPos pos) {
        TileEntity tileentity = worldIn.getTileEntity(pos);
        return tileentity instanceof TileEntityChristmasChest ? (TileEntityChristmasChest) tileentity : null;
    }

}
