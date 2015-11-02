package simple.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockNewLeaf;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import simple.block.moses.BlockMosesAir;
import simple.block.moses.BlockMosesStaff;
import simple.block.chest.christmas.BlockChristmasChest;
import simple.block.chest.crafting.BlockCraftingChest;
import simple.block.light.BlockLight;
import simple.block.light.BlockLightAir;
import net.minecraft.init.Blocks;

public class SimpleBlocks {

	public static final Block christmas_chest;
	public static final Block crafting_chest;
	public static final Block light_block;
	public static final Block door_glass;
	public static final Block moses_staff;
	public static final Block moses_air_block;
	public static final BlockLightAir floodlight_beam;

	public static final BlockStairs glass_stairs;
	public static final BlockStairs dirt_stairs;
	public static final BlockStairs leaves_oak_stairs;
	public static final BlockStairs leaves_spruce_stairs;
	public static final BlockStairs leaves_birch_stairs;
	public static final BlockStairs leaves_jungle_stairs;
	public static final BlockStairs leaves_acacia_stairs;
	public static final BlockStairs leaves_dark_oak_stairs;

	static {
		christmas_chest = new BlockChristmasChest().setHardness(2.5F).setUnlocalizedName("christmas_chest").setCreativeTab(CreativeTabs.tabBlock);
		crafting_chest = new BlockCraftingChest().setHardness(2.5F).setUnlocalizedName("crafting_chest").setCreativeTab(CreativeTabs.tabBlock);
		light_block = new BlockLight().setHardness(1.5F).setUnlocalizedName("light_block").setCreativeTab(CreativeTabs.tabBlock);
		door_glass = new BlockSimpleDoor(Material.glass).setHardness(1.5F).setUnlocalizedName("glass_door");
		moses_staff = (new BlockMosesStaff()).setHardness(1.0F).setResistance(5.0F).setStepSound(Block.soundTypeWood).setUnlocalizedName("blockMosesStaff");
		moses_air_block = (new BlockMosesAir());
		floodlight_beam = new BlockLightAir();

		glass_stairs = (BlockStairs) new BlockSimpleStairs(Blocks.glass.getDefaultState()).setUnlocalizedName("glassStairs");
		dirt_stairs = (BlockStairs) new BlockSimpleStairs(Blocks.dirt.getDefaultState()).setUnlocalizedName("dirtStairs");
		leaves_oak_stairs = (BlockStairs) new BlockSimpleStairs(Blocks.leaves.getDefaultState().withProperty(BlockNewLeaf.VARIANT, BlockPlanks.EnumType.OAK)).setUnlocalizedName("leavesOakStairs");
		leaves_spruce_stairs = (BlockStairs) new BlockSimpleStairs(Blocks.leaves.getDefaultState().withProperty(BlockNewLeaf.VARIANT, BlockPlanks.EnumType.SPRUCE)).setUnlocalizedName("leavesSpruceStairs");
		leaves_birch_stairs = (BlockStairs) new BlockSimpleStairs(Blocks.leaves.getDefaultState().withProperty(BlockNewLeaf.VARIANT, BlockPlanks.EnumType.BIRCH)).setUnlocalizedName("leavesSpruceStairs");
		leaves_jungle_stairs = (BlockStairs) new BlockSimpleStairs(Blocks.leaves.getDefaultState().withProperty(BlockNewLeaf.VARIANT, BlockPlanks.EnumType.JUNGLE)).setUnlocalizedName("leavesBirchStairs");
		leaves_acacia_stairs = (BlockStairs) new BlockSimpleStairs(Blocks.leaves.getDefaultState().withProperty(BlockNewLeaf.VARIANT, BlockPlanks.EnumType.ACACIA)).setUnlocalizedName("leavesJungleStairs");
		leaves_dark_oak_stairs = (BlockStairs) new BlockSimpleStairs(Blocks.leaves.getDefaultState().withProperty(BlockNewLeaf.VARIANT, BlockPlanks.EnumType.DARK_OAK)).setUnlocalizedName("leavesSpruceStairs");
	}
}