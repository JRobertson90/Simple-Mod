package xaeroxe_jayperdu_acm.block;

import xaeroxe_jayperdu_acm.SimpleMod;
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

public class BlockLight extends Block {

	public static final String NAME = "light_block";

	// I'm using this coordinate as a marker to know when to increment the distance
	private static final BlockPos MARKER = new BlockPos(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
	private static final int MAX_RANGE = 8;

	private static HashSet<BlockPos> alreadyVisited;
	private static Queue<BlockPos> diffusionQueue;
	private static int distance;

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
			distance = 1;
			addNeighborsToQueue(pos);

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
			handleAirBlock(world, pos, placeBlock);
			addNeighborsToQueue(pos);
		}
	}

	private void addNeighborsToQueue(BlockPos pos) {

		diffusionQueue.add(pos.south());
		diffusionQueue.add(pos.north());
		diffusionQueue.add(pos.up());
		diffusionQueue.add(pos.up().south());
		diffusionQueue.add(pos.up().north());
		diffusionQueue.add(pos.down());
		diffusionQueue.add(pos.down().south());
		diffusionQueue.add(pos.down().north());

		diffusionQueue.add(pos.east());
		diffusionQueue.add(pos.east().south());
		diffusionQueue.add(pos.east().north());
		diffusionQueue.add(pos.east().up());
		diffusionQueue.add(pos.east().up().south());
		diffusionQueue.add(pos.east().up().north());
		diffusionQueue.add(pos.east().down());
		diffusionQueue.add(pos.east().down().south());
		diffusionQueue.add(pos.east().down().north());

		diffusionQueue.add(pos.west());
		diffusionQueue.add(pos.west().south());
		diffusionQueue.add(pos.west().north());
		diffusionQueue.add(pos.west().up());
		diffusionQueue.add(pos.west().up().south());
		diffusionQueue.add(pos.west().up().north());
		diffusionQueue.add(pos.west().down());
		diffusionQueue.add(pos.west().south());
		diffusionQueue.add(pos.west().north());
	}

	private void handleAirBlock(World world, BlockPos pos, boolean placeBlock) {
		Block block = placeBlock ? SimpleBlocks.light_block_air : Blocks.air;
		world.setBlockState(pos, block.getDefaultState());
		world.markBlockForUpdate(pos);
		world.notifyLightSet(pos);
	}
}