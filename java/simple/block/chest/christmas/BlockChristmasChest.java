package simple.block.chest.christmas;

import net.minecraft.block.BlockChest;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import simple.SimpleMod;

public class BlockChristmasChest extends BlockChest {

    public static final String NAME = "christmas_chest";

    public BlockChristmasChest() {
        super(0);
        setHardness(2.5F);
        setUnlocalizedName(SimpleMod.ID + ":" + NAME);
        setCreativeTab(CreativeTabs.tabBlock);
        GameRegistry.registerBlock(this, NAME);
        GameRegistry.registerTileEntity(TileEntityChristmasChest.class, "christmas_chest_tile");
        GameRegistry.addRecipe(new ItemStack(this, 1), "XXX", "X#X", "XXX", 'X', new ItemStack(Blocks.wool, 1, 14), '#', Blocks.chest);
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityChristmasChest();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote)
        {
            return true;
        }
        else
        {
            ILockableContainer ilockablecontainer = this.getLockableContainer(worldIn, pos);

            if (ilockablecontainer != null)
            {
                playerIn.openGui(SimpleMod.INSTANCE, 0, worldIn, pos.getX(), pos.getY(), pos.getZ());
            }

            return true;
        }
    }

    @Override
    public int getRenderType() {
        return -1;
    }

}
