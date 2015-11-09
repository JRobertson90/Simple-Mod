package simple.block;

import simple.SimpleMod;
import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.*;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BlockLight extends Block {

	public static final String NAME = "light_block";

	// I'm using this coordinate as a marker to know when to increment the distance
	private static final BlockPos MARKER = new BlockPos(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
	private static final int MAX_RANGE = 8;

	private static HashSet<BlockPos> alreadyVisited;
	private static Queue<BlockPos> diffusionQueue;
	private static Integer distance;

	public BlockLight() {
		super(Material.cloth);
		setHardness(1.5F);
		setUnlocalizedName(SimpleMod.ID + ":" + NAME);
		setCreativeTab(CreativeTabs.tabBlock);
		GameRegistry.registerBlock(this, NAME);
		GameRegistry.addRecipe(new ItemStack(this, 4), "###", "#Q#", "###", '#', Items.glowstone_dust, 'Q', Blocks.quartz_block);
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		diffuseLight(worldIn, pos, true);
		super.onBlockAdded(worldIn, pos, state);
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		diffuseLight(worldIn, pos, false);
		super.breakBlock(worldIn, pos, state);
	}

	private void diffuseLight(World world, BlockPos pos, boolean placeBlock) {

		if( ! world.isRemote) {

			alreadyVisited = new HashSet<BlockPos>();
			alreadyVisited.add(pos);
			diffusionQueue = new LinkedList<BlockPos>();
			diffusionQueue.add(MARKER);
			distance = 0;
			queueUpNeighbors(pos);

			while(diffusionQueue.size() > 0) {

				BlockPos next = diffusionQueue.remove();

				if(next.equals(MARKER)) {
					distance++;
					if(diffusionQueue.size() > 0) {
						diffusionQueue.add(MARKER);
					}
				}
				else {
					diffuseLightRec(world, next, placeBlock);
				}
			}

			alreadyVisited = null;
			diffusionQueue = null;
			distance = null;
		}
	}

	private void diffuseLightRec(World world, BlockPos pos, boolean placeBlock) {

		if(alreadyVisited.contains(pos)) {
			return;
		}

		alreadyVisited.add(pos);

		if(distance > MAX_RANGE) {
			return;
		}

		if (pos.getY() < 0 || pos.getY() >= world.getHeight()) {
			return;
		}

		Block block = world.getBlockState(pos).getBlock();

		if (block != null && block.isAir(world, pos)) {
			if(placeBlock) {
				world.setBlockState(pos, SimpleBlocks.light_block_air.getDefaultState());
			} else {
				world.setBlockToAir(pos);
			}
			queueUpNeighbors(pos);
		}
	}

	private void queueUpNeighbors(BlockPos pos) {

		Set<BlockPos> neighbors = new HashSet<BlockPos>();

		neighbors.add(pos.south());
		neighbors.add(pos.north());
		neighbors.add(pos.up());
		neighbors.add(pos.up().south());
		neighbors.add(pos.up().north());
		neighbors.add(pos.down());
		neighbors.add(pos.down().south());
		neighbors.add(pos.down().north());

		neighbors.add(pos.east());
		neighbors.add(pos.east().south());
		neighbors.add(pos.east().north());
		neighbors.add(pos.east().up());
		neighbors.add(pos.east().up().south());
		neighbors.add(pos.east().up().north());
		neighbors.add(pos.east().down());
		neighbors.add(pos.east().down().south());
		neighbors.add(pos.east().down().north());

		neighbors.add(pos.west());
		neighbors.add(pos.west().south());
		neighbors.add(pos.west().north());
		neighbors.add(pos.west().up());
		neighbors.add(pos.west().up().south());
		neighbors.add(pos.west().up().north());
		neighbors.add(pos.west().down());
		neighbors.add(pos.west().south());
		neighbors.add(pos.west().north());

		neighbors.removeAll(alreadyVisited);
		diffusionQueue.addAll(neighbors);
	}
}
