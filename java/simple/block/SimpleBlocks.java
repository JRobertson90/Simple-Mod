package simple.block;

import net.minecraft.block.BlockChest;
import simple.block.chest.christmas.BlockChristmasChest;
import simple.block.chest.crafting.BlockCraftingChest;
import net.minecraft.block.Block;

public class SimpleBlocks {
	public static final Block light_block = new BlockLight();
	public static final BlockLightAir light_block_air = new BlockLightAir();
	public static final BlockCraftingChest crafting_chest = new BlockCraftingChest();
	public static final BlockChest christmas_chest = new BlockChristmasChest();
}