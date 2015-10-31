package simple.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.creativetab.CreativeTabs;
import simple.block.moses.BlockMosesAir;
import simple.block.moses.BlockMosesStaff;
import simple.christmasChest.BlockChristmasChest;
import simple.craftingChest.BlockCraftingChest;
import simple.lighting.BlockLight;
import simple.lighting.BlockLightAir;
import net.minecraft.init.Blocks;

public class SimpleBlocks {
	public static final Block christmas_chest = new BlockChristmasChest(3209,0).setHardness(2.5F).setUnlocalizedName("christmas_chest").setCreativeTab(CreativeTabs.tabBlock);
	public static final Block crafting_chest = new BlockCraftingChest(3210).setHardness(2.5F).setUnlocalizedName("crafting_chest").setCreativeTab(CreativeTabs.tabBlock);
	public static final Block light_block = new BlockLight(3211).setHardness(1.5F).setUnlocalizedName("light_block").setCreativeTab(CreativeTabs.tabBlock);
	public static final Block door_glass = new BlockGlassDoor(3250).setHardness(1.5F).setUnlocalizedName("glass_door");
	public static final Block moses_staff = (new BlockMosesStaff(3277)).setHardness(1.0F).setResistance(5.0F).setStepSound(Block.soundTypeWood).setUnlocalizedName("blockMosesStaff");
	public static final Block moses_air_block = (new BlockMosesAir(3278));
	public static final BlockLightAir floodlight_beam = new BlockLightAir(3212);
	public static final BlockStairs glass_stairs = (BlockStairs) new BlockStairsAlt(3251, Blocks.glass,0).setUnlocalizedName("glassStairs").setLightOpacity(0).setHardness(0.3F).setStepSound(Block.soundTypeGlass);
	public static final BlockStairs dirt_stairs = (BlockStairs) new BlockStairsAlt(3268, Blocks.dirt, 0).setUnlocalizedName("dirtStairs").setHardness(0.5F).setStepSound(Block.soundTypeGravel);
	public static final BlockStairs oak_stairs = (BlockStairs) new BlockStairsAlt(3269, Blocks.planks, 0).setUnlocalizedName("oakStairs").setHardness(2.0F).setStepSound(Block.soundTypeWood);
	public static final BlockStairs spruce_stairs = (BlockStairs) new BlockStairsAlt(3270, Blocks.planks, 1).setUnlocalizedName("spruceStairs").setHardness(2.0F).setStepSound(Block.soundTypeWood);
	public static final BlockStairs birch_stairs = (BlockStairs) new BlockStairsAlt(3271, Blocks.planks, 2).setUnlocalizedName("birchStairs").setHardness(2.0F).setStepSound(Block.soundTypeWood);
	public static final BlockStairs jungle_stairs = (BlockStairs) new BlockStairsAlt(3272, Blocks.planks, 3).setUnlocalizedName("jungleStairs").setHardness(2.0F).setStepSound(Block.soundTypeWood);
	public static final BlockStairs leaves_oak_stairs = (BlockStairs) new BlockStairsAlt(3273, Blocks.leaves, 0).setUnlocalizedName("leavesOakStairs").setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundTypeGrass);
	public static final BlockStairs leaves_spruce_stairs = (BlockStairs) new BlockStairsAlt(3274, Blocks.leaves, 1).setUnlocalizedName("leavesSpruceStairs").setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundTypeGrass);
	public static final BlockStairs leaves_birch_stairs = (BlockStairs) new BlockStairsAlt(3275, Blocks.leaves, 2).setUnlocalizedName("leavesBirchStairs").setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundTypeGrass);
	public static final BlockStairs leaves_jungle_stairs = (BlockStairs) new BlockStairsAlt(3276, Blocks.leaves, 3).setUnlocalizedName("leavesJungleStairs").setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundTypeGrass);
}