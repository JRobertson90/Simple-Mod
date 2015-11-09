package xaeroxe_jayperdu_acm.block.chest.christmas;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xaeroxe_jayperdu_acm.SimpleMod;

import java.util.Iterator;

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


    // Copied straight from BlockChest -- Changes: TileEntityChristmasChest casting
    @Override
    public ILockableContainer getLockableContainer(World worldIn, BlockPos pos)
    {
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (!(tileentity instanceof TileEntityChristmasChest))
        {
            return null;
        }
        else
        {
            Object object = (TileEntityChristmasChest)tileentity;

            if (this.isBlocked(worldIn, pos))
            {
                return null;
            }
            else
            {
                Iterator iterator = EnumFacing.Plane.HORIZONTAL.iterator();

                while (iterator.hasNext())
                {
                    EnumFacing enumfacing = (EnumFacing)iterator.next();
                    BlockPos blockpos1 = pos.offset(enumfacing);
                    Block block = worldIn.getBlockState(blockpos1).getBlock();

                    if (block == this)
                    {
                        if (this.isBlocked(worldIn, blockpos1))
                        {
                            return null;
                        }

                        TileEntity tileentity1 = worldIn.getTileEntity(blockpos1);

                        if (tileentity1 instanceof TileEntityChristmasChest)
                        {
                            if (enumfacing != EnumFacing.WEST && enumfacing != EnumFacing.NORTH)
                            {
                                object = new InventoryLargeChest("container.chestDouble", (ILockableContainer)object, (TileEntityChristmasChest)tileentity1);
                            }
                            else
                            {
                                object = new InventoryLargeChest("container.chestDouble", (TileEntityChristmasChest)tileentity1, (ILockableContainer)object);
                            }
                        }
                    }
                }

                return (ILockableContainer)object;
            }
        }
    }

    // Copied from BlockChest -- No Changes (it is private)
    private boolean isBlocked(World worldIn, BlockPos pos)
    {
        return this.isBelowSolidBlock(worldIn, pos) || this.isOcelotSittingOnChest(worldIn, pos);
    }

    // Copied from BlockChest -- No Changes (it is private)
    private boolean isBelowSolidBlock(World worldIn, BlockPos pos)
    {
        return worldIn.isSideSolid(pos.up(), EnumFacing.DOWN, false);
    }

    // Copied from BlockChest -- No Changes (it is private)
    private boolean isOcelotSittingOnChest(World worldIn, BlockPos pos)
    {
        Iterator iterator = worldIn.getEntitiesWithinAABB(EntityOcelot.class, new AxisAlignedBB((double)pos.getX(), (double)(pos.getY() + 1), (double)pos.getZ(), (double)(pos.getX() + 1), (double)(pos.getY() + 2), (double)(pos.getZ() + 1))).iterator();
        EntityOcelot entityocelot;

        do
        {
            if (!iterator.hasNext())
            {
                return false;
            }

            Entity entity = (Entity)iterator.next();
            entityocelot = (EntityOcelot)entity;
        }
        while (!entityocelot.isSitting());

        return true;
    }

}
