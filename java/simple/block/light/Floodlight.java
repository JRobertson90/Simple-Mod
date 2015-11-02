package simple.block.light;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import simple.block.SimpleBlocks;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Floodlight {

	public static final int maxRange = 8;

	// I'm using this coordinate as a marker to know when to increment the distance
	private static final BlockPos marker = new BlockPos(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
	
	public static boolean DEBUG_MODE = false;

	private static Map<BlockPos, Boolean> isAirBlock = new HashMap<BlockPos, Boolean>();
	private static Queue<BlockPos> diffusionQueue;
	private static int distance;
	
	static void diffuseLight(World world, BlockPos pos, boolean placed) {

		if(!world.isRemote) {
			
			initialize(pos);
			
			addNeighborsToQueue(pos);
			diffusionQueue.add(marker);
			isAirBlock.put(pos, false);
			
			while(diffusionQueue.size() > 0) {
				
				BlockPos next = diffusionQueue.remove();
				
				if(next.equals(marker)) {
					distance++;
					if(diffusionQueue.size() > 0) {
						diffusionQueue.add(marker);		
					}
				}
				else {
					diffuseLightRec(world, next, placed);
				}
			}
		}
	}

	private static void initialize(BlockPos pos) {
		
		isAirBlock = new HashMap<BlockPos, Boolean>();
		diffusionQueue = new LinkedList<BlockPos>();
		distance = 1;
	}

	static void diffuseLightRec(World world, BlockPos pos, boolean placed) {

		Boolean b = isAirBlock.get(pos);
		if(b != null) {
			return; // Because it means we've already checked this block
		}

		if(distance > maxRange) {
			return;
		}

		if (pos.getY() < 0 || pos.getY() >= world.getHeight()) {
			return;
		}

		Block block = world.getBlockState(pos).getBlock();
		
		if (block != null && ! block.isAir(world, pos)) {
			isAirBlock.put(pos, false);
			return;
		}
		else {
			isAirBlock.put(pos, true);
		}

		placeInvisLightBlock(world, pos, placed);
		
		addNeighborsToQueue(pos);
	}

	private static void addNeighborsToQueue(BlockPos pos) {

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

	static void placeInvisLightBlock(World world, BlockPos pos, boolean blockPlaced) {
		
		Block block = world.getBlockState(pos).getBlock();

		if (block == null && blockPlaced) {

			world.setBlockState(pos, SimpleBlocks.floodlight_beam.getDefaultState());
			world.markBlockForUpdate(pos);
			world.notifyLightSet(pos);
		}
		
		if( ! blockPlaced) {
			world.setBlockState(pos, Blocks.air.getDefaultState());
			world.markBlockForUpdate(pos);
//			world.notifyLightSet(pos);
		}
	}

}