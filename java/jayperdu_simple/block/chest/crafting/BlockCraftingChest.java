package jayperdu_simple.block.chest.crafting;

import jayperdu_simple.block.SimpleBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockCraftingChest extends BlockChest {

    public static final String NAME = "crafting_chest";

    public BlockCraftingChest() {
        super(0);
        setHardness(2.5F);
        setUnlocalizedName(NAME);
        setCreativeTab(CreativeTabs.tabBlock);
        GameRegistry.registerBlock(this, NAME);
        GameRegistry.registerTileEntity(TileEntityCraftingChest.class, "crafting_chest_tile");
        GameRegistry.addRecipe(new ItemStack(SimpleBlocks.crafting_chest, 1), "XXX", "X#X", "XXX", 'X', Blocks.planks, '#', Blocks.crafting_table);
        GameRegistry.addShapelessRecipe(new ItemStack(SimpleBlocks.crafting_chest, 1), Blocks.chest, Blocks.crafting_table);
    }

    @Override public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {}
    @Override public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {}
    @Override public IBlockState checkForSurroundingChests(World worldIn, BlockPos pos, IBlockState state) { return state; }
    @Override public boolean canPlaceBlockAt(World worldIn, BlockPos pos) { return true; }

    @Override
    /** Returns a new instance of a block's tile entity class. Called on placing the block. */
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