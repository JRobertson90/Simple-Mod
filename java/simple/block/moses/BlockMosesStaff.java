package simple.block.moses;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import simple.item.SimpleItems;

public class BlockMosesStaff extends BlockFence {

	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyBool TOP_STAFF = PropertyBool.create("topstaff");

	public BlockMosesStaff() {
		super(Material.wood);
		setCreativeTab(null);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(TOP_STAFF, Boolean.valueOf(false)));
	}

	@Override
	protected BlockState createBlockState() {
		return new BlockState(this, FACING, TOP_STAFF);
	}

	// Metadata 0-3 = direction, 4 = top part of staff
	@Override
	public int getMetaFromState(IBlockState state) {
		EnumFacing direction = (EnumFacing) state.getValue(FACING);
		boolean isTopStaff = (Boolean) state.getValue(TOP_STAFF);
		return direction.getIndex() | (isTopStaff ? 4 : 0);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState()
				.withProperty(FACING, EnumFacing.getHorizontal(meta & 3))
				.withProperty(TOP_STAFF, Boolean.valueOf((meta & 4) == 4));
	}

	/**
     * Called right before the block is destroyed by a player.  Args: world, x, y, z, metaData
     */
	@Override
	public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {

		EnumFacing facing = (EnumFacing) state.getValue(FACING);
		boolean isTopStaff = (Boolean) state.getValue(TOP_STAFF);

		// If this block is top part of staff
    	if(isTopStaff) {

    		worldIn.setBlockToAir(pos.down());
    		if( ! worldIn.isRemote) {
    			MosesStaff.diffuseAir(worldIn, pos.down(), false, facing);
    		}
    	}
    	else {
    		worldIn.setBlockToAir(pos.up());
    		if( ! worldIn.isRemote) {
    			MosesStaff.diffuseAir(worldIn, pos, false, facing);
    		}
    	}
    }
	
	/**
	 * Adds all intersecting collision boxes to a list. (Be sure to only add boxes to the list if they intersect the
	 * mask.) Parameters: World, X, Y, Z, mask, list, colliding entity
	 */
	@Override
	public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity) {
		float f = 0.375F;
		float f1 = 0.625F;
		float f2 = 0.375F;
		float f3 = 0.625F;

		this.setBlockBounds(f, 0.0F, f2, f1, 1.0F, f3);
	}

	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y, z
	 */
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) {
		float f = 0.375F;
		float f1 = 0.625F;
		float f2 = 0.375F;
		float f3 = 0.625F;

		this.setBlockBounds(f, 0.0F, f2, f1, 1.0F, f3);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean isSolidFullCube() {
		return false;
	}

	@Override
	public boolean isNormalCube() {
		return false;
	}

	@Override
	public boolean isVisuallyOpaque() {
		return false;
	}

	@Override
	public boolean isFullCube() {
		return false;
	}

	@Override
	public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		return true;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
		return false;
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos) {
        return new ItemStack(SimpleItems.moses_staff, 1);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return SimpleItems.moses_staff;
    }


}