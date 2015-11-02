package simple;

import simple.block.SimpleBlocks;
import simple.block.BlockStairsWool;
import simple.block.chest.christmas.TileEntityChristmasChest;
import simple.block.chest.crafting.TileEntityCraftingChest;
import simple.entity.EntityTeleportArrow;
import simple.entity.EntityTorchArrow;
import simple.item.SimpleItems;
import simple.item.ItemBedColor;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SimpleRegistry {
	
	public static void load()
	{
		//-------------------
		//   I T E M
		//-------------------
		GameRegistry.registerBlock(SimpleBlocks.moses_staff, "block_moses_staff");
		GameRegistry.registerItem(SimpleItems.moses_staff, "item_moses_staff");

		BlockStairsWool.register();
		
		GameRegistry.registerBlock(SimpleBlocks.glass_stairs, "glass_stairs");
		GameRegistry.registerBlock(SimpleBlocks.dirt_stairs, "dirt_stairs");
		GameRegistry.registerBlock(SimpleBlocks.leaves_oak_stairs, "leaves_oak_stairs");
		GameRegistry.registerBlock(SimpleBlocks.leaves_spruce_stairs, "leaves_spruce_stairs");
		GameRegistry.registerBlock(SimpleBlocks.leaves_birch_stairs, "leaves_birch_stairs");
		GameRegistry.registerBlock(SimpleBlocks.leaves_jungle_stairs, "leaves_jungle_stairs");

		ItemBedColor.register();
		
		GameRegistry.registerBlock(SimpleBlocks.light_block, "light_block");
		GameRegistry.registerBlock(SimpleBlocks.floodlight_beam, "light_block_beam");

		GameRegistry.registerItem(SimpleItems.glass_door, "glass_door");
		
		GameRegistry.registerItem(SimpleItems.diamond_sword_alt1, "diamond_sword_alt1");
		GameRegistry.registerItem(SimpleItems.diamond_sword_alt2, "diamond_sword_alt_2");
		GameRegistry.registerItem(SimpleItems.diamond_sword_alt3, "zelda_sword");
		GameRegistry.registerItem(SimpleItems.diamond_sword_alt4, "energy_sword");
		GameRegistry.registerItem(SimpleItems.diamond_sword_alt5, "lich_king_sword");
		
		GameRegistry.registerItem(SimpleItems.blue_sword, "blue_sword");
		GameRegistry.registerItem(SimpleItems.blue_pickaxe, "blue_pickaxe");
		GameRegistry.registerItem(SimpleItems.blue_axe, "blue_axe");
		GameRegistry.registerItem(SimpleItems.blue_shovel, "blue_shovel");
		GameRegistry.registerItem(SimpleItems.blue_hoe, "blue_hoe");

		GameRegistry.registerItem(SimpleItems.red_sword, "red_sword");
		GameRegistry.registerItem(SimpleItems.red_pickaxe, "red_pickaxe");
		GameRegistry.registerItem(SimpleItems.red_axe, "red_axe");
		GameRegistry.registerItem(SimpleItems.red_shovel, "red_shovel");
		GameRegistry.registerItem(SimpleItems.red_hoe, "red_hoe");

		GameRegistry.registerItem(SimpleItems.arrow_teleport, "arrow_port");
		GameRegistry.registerItem(SimpleItems.bow_teleport, "bow_port");
		GameRegistry.registerBlock(SimpleBlocks.christmas_chest, "christmas_chest");
		GameRegistry.registerItem(SimpleItems.arrow_torch, "arrow_torch");
		GameRegistry.registerItem(SimpleItems.bow_torch, "bow_torch");
		GameRegistry.registerBlock(SimpleBlocks.crafting_chest, "crafting_chest");
		
		EntityRegistry.registerModEntity(EntityTeleportArrow.class, "Teleport Arrow", 0, SimpleMod.instance, 128, 1, true);
		EntityRegistry.registerModEntity(EntityTorchArrow.class, "Torch Arrow", 1, SimpleMod.instance, 128, 1, true);

		GameRegistry.registerTileEntity(TileEntityChristmasChest.class, "christmas_chest_tile");
		GameRegistry.registerTileEntity(TileEntityCraftingChest.class, "crafting_chest_tile");
	}
}
