package simple.block.moses;

import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import simple.block.SimpleBlocks;
import simple.item.SimpleItems;

public class ItemMosesStaff extends Item {

	public ItemMosesStaff() {
		this.setCreativeTab(CreativeTabs.tabTools);
		setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int damage) {
		return damage;
	}

	@Override
	public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		if( par1ItemStack.hasTagCompound()) {
			par1ItemStack.setTagCompound( new NBTTagCompound( ) );
		}
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, BlockPos pos, EnumFacing facing, float par8, float par9, float par10) {
		if( itemStack.hasTagCompound()) {
			itemStack.setTagCompound(new NBTTagCompound());
		}

		if(itemStack.getItemDamage() == 0) {
			return placeStaff(itemStack, player, world, pos, facing);
		}

		return false;
	}
	
	private boolean placeStaff(ItemStack itemStack, EntityPlayer player, World world, BlockPos pos, EnumFacing facing) {
		
		BlockPos up = pos.up();
		BlockPos up2 = pos.up(2);

		if (player.canPlayerEdit(up, facing, itemStack) && player.canPlayerEdit(up2, facing, itemStack)
				&& ! world.getBlockState(up).getBlock().isOpaqueCube() && ! world.getBlockState(up2).getBlock().isOpaqueCube()) {

			IBlockState bottomStaff = SimpleBlocks.moses_staff.getDefaultState();
			bottomStaff.withProperty(BlockMosesStaff.FACING, EnumFacing.fromAngle(player.rotationYaw));
			bottomStaff.withProperty(BlockMosesStaff.TOP_STAFF, Boolean.valueOf(false));
			world.setBlockState(up, bottomStaff);

			itemStack.getTagCompound().setInteger("staff_x", up.getX());
			itemStack.getTagCompound().setInteger("staff_y", up.getY());
			itemStack.getTagCompound().setInteger("staff_z", up.getZ());
			itemStack.setItemDamage(1);
			
			if (world.getBlockState(up).getBlock() == SimpleBlocks.moses_staff) {

				IBlockState topStaff = SimpleBlocks.moses_staff.getDefaultState();
				topStaff.withProperty(BlockMosesStaff.FACING, EnumFacing.fromAngle(player.rotationYaw));
				topStaff.withProperty(BlockMosesStaff.TOP_STAFF, Boolean.valueOf(true));

				world.setBlockState(up2, topStaff);
			}
			
			MosesStaff.diffuseAir(world, up, true, facing);
			
			return true;
		}
		return false;
	}

	@Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {

    	if(itemStack.getItemDamage() == 1) {
    		recallStaff(itemStack, world);
    	}
    	return itemStack;
    }
    
	private void recallStaff(ItemStack itemStack, World world) {

		NBTTagCompound compound = itemStack.getTagCompound();

		int x = compound.getInteger("staff_x");
		int y = compound.getInteger("staff_y");
		int z = compound.getInteger("staff_z");
		BlockPos pos = new BlockPos(x,y,z);

		IBlockState state = world.getBlockState(pos);
		if(state.getBlock() == SimpleBlocks.moses_staff) {
			world.setBlockToAir(pos);
			world.markBlockForUpdate(pos);
			SimpleBlocks.moses_staff.onBlockDestroyedByPlayer(world, pos, state);
		}
		else {
			itemStack.stackSize--;
		}
		
		itemStack.setItemDamage(0);
	}

	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		par3List.add("Parts the Red Sea");
	}

	/* This is kind of a shapeless / shaped recipe. Draw a staff plus a book. Mirrored versions happen automatically. */
	public static void addRecipe() {
		GameRegistry.addRecipe(new ItemStack(SimpleItems.moses_staff, 1), "S ", "S ", "SB", 'S', Items.stick, 'B', Items.book);
		GameRegistry.addRecipe(new ItemStack(SimpleItems.moses_staff, 1), "S ", "SB", "S ", 'S', Items.stick, 'B', Items.book);
		GameRegistry.addRecipe(new ItemStack(SimpleItems.moses_staff, 1), "SB", "S ", "S ", 'S', Items.stick, 'B', Items.book);
		GameRegistry.addRecipe(new ItemStack(SimpleItems.moses_staff, 1), "S  ", "S  ", "S B", 'S', Items.stick, 'B', Items.book);
		GameRegistry.addRecipe(new ItemStack(SimpleItems.moses_staff, 1), "S  ", "S B", "S  ", 'S', Items.stick, 'B', Items.book);
		GameRegistry.addRecipe(new ItemStack(SimpleItems.moses_staff, 1), "S B", "S  ", "S  ", 'S', Items.stick, 'B', Items.book);
	}

}