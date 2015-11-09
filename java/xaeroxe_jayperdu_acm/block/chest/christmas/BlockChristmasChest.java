package xaeroxe_jayperdu_acm.block.chest.christmas;

import net.minecraft.block.BlockChest;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xaeroxe_jayperdu_acm.SimpleMod;

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
    public ILockableContainer getLockableContainer(World worldIn, BlockPos pos) {
        TileEntity tileentity = worldIn.getTileEntity(pos);
        return tileentity instanceof TileEntityChristmasChest ? (TileEntityChristmasChest) tileentity : null;
    }

}
