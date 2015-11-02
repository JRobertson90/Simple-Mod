package simple.block.moses;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import simple.block.SimpleBlocks;
import net.minecraft.block.Block;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class MosesStaff {

	//------------------------
	//  Fields
	//------------------------
	private static final int FORWARD_LENGTH = 64;
	private static final int SIDE_LENGTH = 2;
	private static final int BACKWARD_LENGTH = 2;
	
	private static BlockPos staffLocation;
	private static Map<BlockPos, Boolean> canPlaceBlock;
	private static Queue<BlockPos> diffusionQueue;
	
	private static int boundXPos;
	private static int boundXNeg;
	private static int boundZPos;
	private static int boundZNeg;
	
	private static int greatestHeightSoFar;
	
	//------------------------
	//  Initialize
	//------------------------
	private static void initialize(BlockPos pos, EnumFacing facing) {
		
		staffLocation = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
		canPlaceBlock = new HashMap<BlockPos, Boolean>();
		diffusionQueue = new LinkedList<BlockPos>();
		canPlaceBlock.put(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), false);
		greatestHeightSoFar = 0;
		addNeighborsToQueue(pos);
		adjustBoundaries(facing);
	}

	//------------------------
	//  Replace Water
	//------------------------
	// Replaces some air blocks nearby the staff with water to ensure the water caves in all the way.
	private static void replaceWater(World world) {

		for(BlockPos pos : canPlaceBlock.keySet()) {
			if(pos.getY() == greatestHeightSoFar && world.isAirBlock(pos) &&
					pos.getX() > pos.getX() - SIDE_LENGTH && pos.getX() < pos.getX() + SIDE_LENGTH &&
					pos.getZ() > pos.getZ() - SIDE_LENGTH && pos.getZ() < pos.getZ() + SIDE_LENGTH) {

				world.setBlockState(pos, Blocks.flowing_water.getDefaultState());
				world.markBlockForUpdate(pos);
			}
		}
		world.notifyBlockOfStateChange(staffLocation, world.getBlockState(staffLocation).getBlock());
	}


	/*------------------------------------------\
	|             Adjust Boundaries             |
	|-------------------------------------------|
	|                   NORTH                   |
	|                                           |
	|                    z-                     |
	|                                           |
	|    WEST      x-          x+      EAST     |
	|                                           |
	|                    z+                     |
	|                                           |
	|                  SOUTH                    |
	|-------------------------------------------|
	|            Coordinates Map                |
	\------------------------------------------*/
	private static void adjustBoundaries(EnumFacing facing) {
		
		switch(facing) {
			case SOUTH: {
				boundZPos = FORWARD_LENGTH;
				boundZNeg = BACKWARD_LENGTH;
				boundXNeg = SIDE_LENGTH;
				boundXPos = SIDE_LENGTH;
				break;
			}
			case WEST: {
				boundXNeg = FORWARD_LENGTH;
				boundXPos = BACKWARD_LENGTH;
				boundZNeg = SIDE_LENGTH;
				boundZPos = SIDE_LENGTH;
				break;
			}
			case NORTH: {
				boundZNeg = FORWARD_LENGTH;
				boundZPos = BACKWARD_LENGTH;
				boundXNeg = SIDE_LENGTH;
				boundXPos = SIDE_LENGTH;
				break;
			}
			case EAST: {
				boundXPos = FORWARD_LENGTH;
				boundXNeg = BACKWARD_LENGTH;
				boundZNeg = SIDE_LENGTH;
				boundZPos = SIDE_LENGTH;
				break;
			}
		}
	}

	//------------------------
	//  Diffuse Air
	//------------------------
	public static void diffuseAir(World world, BlockPos pos, boolean placed, EnumFacing facing) {

		if(!world.isRemote) {
			
			initialize(pos, facing);
			
			while(diffusionQueue.size() > 0) {
				
				BlockPos next = diffusionQueue.remove();
				diffuseAirRec(world, next, placed);
			}
			
			if(!placed) {
				replaceWater(world);
			}
		}
	}

	//--------------------------
	//  Diffuse Air Recursive
	//--------------------------
	private static void diffuseAirRec(World world, BlockPos pos, boolean placed) {

		if( ! insideBoundaries(world, pos) || alreadyVisited(pos)) {
			return;
		}

		Block block = world.getBlockState(pos).getBlock();
		
		if (block != null && canPlaceBlock(world, pos)) {
			canPlaceBlock.put(pos, true);
			if(pos.getY() > greatestHeightSoFar) {
				greatestHeightSoFar = pos.getY();
			}
		}
		else {
			canPlaceBlock.put(pos, false);
			return;
		}
		
		placeInvisMosesBlock(world, pos, placed);
		addNeighborsToQueue(pos);
	}

	//------------------------
	//  Already Visited
	//------------------------
	private static boolean alreadyVisited(BlockPos pos) {
		
		return canPlaceBlock.get(pos) != null;
	}

	//------------------------
	//  Inside Boundaries
	//------------------------
	private static boolean insideBoundaries(World world, BlockPos pos) {

		return !(pos.getX() < staffLocation.getX() - boundXNeg ||
				pos.getX() > staffLocation.getX() + boundXPos ||
				pos.getZ() < staffLocation.getZ() - boundZNeg ||
				pos.getZ() > staffLocation.getZ() + boundZPos ||
				pos.getY() < 0 ||
				pos.getY() >= world.getHeight());
	}

	//------------------------
	//  Can Place Block
	//------------------------
	private static boolean canPlaceBlock(World world, BlockPos pos) {
		
		Block block = world.getBlockState(pos).getBlock();
		return block == Blocks.flowing_water || block == Blocks.water || block == SimpleBlocks.moses_air_block;
	}

	//----------------------------
	//  Add Neighbors to Queue
	//----------------------------
	private static void addNeighborsToQueue(BlockPos pos) {
		
		diffusionQueue.add(pos.west());
		diffusionQueue.add(pos.east());
		diffusionQueue.add(pos.up());
		diffusionQueue.add(pos.down());
		diffusionQueue.add(pos.north());
		diffusionQueue.add(pos.south());
	}

	//------------------------
	//  Place Moses Block
	//------------------------
	private static void placeInvisMosesBlock(World world, BlockPos pos, boolean blockPlaced) {

		if (blockPlaced) {
			world.setBlockState(pos, SimpleBlocks.moses_air_block.getDefaultState());
			world.markBlockForUpdate(pos);
		}
		else {
			world.setBlockState(pos, Blocks.air.getDefaultState());
			world.markBlockForUpdate(pos);
			world.notifyBlockOfStateChange(pos, Blocks.air);
		}
	}
	
}