package simple;

import simple.recipe.ToolRecipeBuilder;
import net.minecraft.block.BlockStairs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import simple.block.SimpleBlocks;
import simple.block.BlockStairsWool;
import simple.block.moses.ItemMosesStaff;
import simple.item.SimpleItems;
import simple.item.ItemBedColor;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

import java.util.ArrayList;

public class SimpleRecipes {

	public static void load() {
		ItemMosesStaff.addRecipe();
		ItemBedColor.loadRecipes();
		BlockStairsWool.loadRecipes();
		
		addStairsRecipe(Blocks.glass, SimpleBlocks.glass_stairs);
		addStairsRecipe(Blocks.dirt, SimpleBlocks.dirt_stairs);
		
		GameRegistry.addRecipe(new ItemStack(SimpleBlocks.oak_stairs, 8), "  #", " ##", "###", '#', new ItemStack(Blocks.planks, 1, 0));
		GameRegistry.addRecipe(new ItemStack(SimpleBlocks.spruce_stairs, 8), "  #"," ##","###",'#', new ItemStack(Blocks.planks,1,1));
		GameRegistry.addRecipe(new ItemStack(SimpleBlocks.birch_stairs, 8), "  #"," ##","###",'#', new ItemStack(Blocks.planks,1,2));
		GameRegistry.addRecipe(new ItemStack(SimpleBlocks.jungle_stairs, 8), "  #"," ##","###",'#', new ItemStack(Blocks.planks,1,3));
		
		GameRegistry.addRecipe(new ItemStack(SimpleBlocks.leaves_oak_stairs, 4), "  #"," ##","###",'#', new ItemStack(Blocks.leaves,1,0));
		GameRegistry.addRecipe(new ItemStack(SimpleBlocks.leaves_spruce_stairs, 4), "  #"," ##","###",'#', new ItemStack(Blocks.leaves,1,1));
		GameRegistry.addRecipe(new ItemStack(SimpleBlocks.leaves_birch_stairs, 4), "  #"," ##","###",'#', new ItemStack(Blocks.leaves,1,2));
		GameRegistry.addRecipe(new ItemStack(SimpleBlocks.leaves_jungle_stairs, 4), "  #"," ##","###",'#', new ItemStack(Blocks.leaves,1,3));
		
		GameRegistry.addShapelessRecipe(new ItemStack(Items.string,4), new ItemStack(Blocks.wool,15));
		GameRegistry.addRecipe(new ItemStack(SimpleBlocks.light_block, 4), "###", "#Q#", "###", '#', Items.glowstone_dust, 'Q', Blocks.quartz_block);
		GameRegistry.addRecipe(new ItemStack(SimpleItems.glass_door), "##","##","##",'#',Blocks.glass);
		
		GameRegistry.addRecipe(new ItemStack(Blocks.grass), "L", "D", 'L', new ItemStack(Blocks.tallgrass,1,1), 'D', Blocks.dirt);
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.sand, 4), Blocks.sandstone);
		
		final ItemStack lapis = new ItemStack(Items.dye, 1, 4);
		ToolRecipeBuilder.withMaterial(lapis).sword(SimpleItems.blue_sword).pickaxe(SimpleItems.blue_pickaxe).shovel(SimpleItems.blue_shovel).axe(SimpleItems.blue_axe).hoe(SimpleItems.blue_hoe);
		ToolRecipeBuilder.withMaterial(Items.redstone).sword(SimpleItems.red_sword).pickaxe(SimpleItems.red_pickaxe).shovel(SimpleItems.red_shovel).axe(SimpleItems.red_axe).hoe(SimpleItems.red_hoe);

		GameRegistry.addRecipe(new ItemStack(SimpleItems.bow_teleport, 1), " L#", "B #", " L#", '#', Items.string, 'B', Items.blaze_rod, 'L', new ItemStack(Items.dye, 1, 4));
		GameRegistry.addRecipe(new ItemStack(SimpleItems.bow_torch, 1), " S#", "SC#", " S#", '#', Items.string, 'S', Items.stick, 'C', new ItemStack(Items.coal, 1, 0));
		GameRegistry.addRecipe(new ItemStack(SimpleItems.arrow_teleport, 4), "E", "S", "A", 'E', Items.ender_pearl, 'S', Items.slime_ball, 'A', Items.arrow);
		GameRegistry.addShapelessRecipe(new ItemStack(SimpleItems.bow_torch, 1), new ItemStack(Items.bow, 1), new ItemStack(Items.coal, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(SimpleItems.arrow_torch, 4), new ItemStack(Items.arrow, 1), new ItemStack(Items.coal, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(SimpleItems.arrow_torch, 1), new ItemStack(Items.arrow, 1), new ItemStack(Blocks.torch, 1));
		GameRegistry.addRecipe(new ItemStack(SimpleItems.arrow_torch, 1), "T", "S", "F", 'T', Blocks.torch, 'S', Items.stick, 'F', Items.feather);
		GameRegistry.addRecipe(new ItemStack(SimpleItems.arrow_torch, 4), "C", "S", "F", 'C', new ItemStack(Items.coal, 1, 0), 'S', Items.stick, 'F', Items.feather);
		
		GameRegistry.addRecipe(new ItemStack(SimpleBlocks.christmas_chest, 1), "XXX", "X#X", "XXX", 'X', new ItemStack(Blocks.wool, 1, 14), '#', Blocks.chest);
		GameRegistry.addRecipe(new ItemStack(SimpleBlocks.crafting_chest, 1), "XXX", "X#X", "XXX", 'X', Blocks.planks, '#', Blocks.crafting_table);
		GameRegistry.addShapelessRecipe(new ItemStack(SimpleBlocks.crafting_chest, 1), Blocks.chest, Blocks.crafting_table);

		GameRegistry.addShapelessRecipe(new ItemStack(SimpleItems.diamond_sword_alt1), Items.diamond_sword);
		GameRegistry.addShapelessRecipe(new ItemStack(SimpleItems.diamond_sword_alt2), SimpleItems.diamond_sword_alt1);
		GameRegistry.addShapelessRecipe(new ItemStack(SimpleItems.diamond_sword_alt3), SimpleItems.diamond_sword_alt2);
		GameRegistry.addShapelessRecipe(new ItemStack(SimpleItems.diamond_sword_alt4), SimpleItems.diamond_sword_alt3);
		GameRegistry.addShapelessRecipe(new ItemStack(SimpleItems.diamond_sword_alt5), SimpleItems.diamond_sword_alt4);
		GameRegistry.addShapelessRecipe(new ItemStack(Items.diamond_sword), SimpleItems.diamond_sword_alt5);
	}

	public static void addStairsRecipe(Object material, BlockStairs output) {
		GameRegistry.addRecipe(new ItemStack(output, 4), "  #"," ##","###",'#', material);
	}

	// Code by yope_fried inspired by pigalot, modified by jayperdu
	public static void removeRecipe(ItemStack resultItem)
	{
		ItemStack recipeResult = null;
		ArrayList recipes = (ArrayList) CraftingManager.getInstance().getRecipeList();

		for (int i = 0; i < recipes.size(); i++)
		{
			IRecipe tmpRecipe = (IRecipe) recipes.get(i);
			if (tmpRecipe instanceof ShapedRecipes)
			{
				ShapedRecipes recipe = (ShapedRecipes)tmpRecipe;
				recipeResult = recipe.getRecipeOutput();
			}

			if (tmpRecipe instanceof ShapelessRecipes)
			{
				ShapelessRecipes recipe = (ShapelessRecipes)tmpRecipe;
				recipeResult = recipe.getRecipeOutput();
			}

			if (tmpRecipe instanceof ShapedOreRecipe)
			{
				ShapedOreRecipe recipe = (ShapedOreRecipe)tmpRecipe;
				recipeResult = recipe.getRecipeOutput();
			}

			if (ItemStack.areItemStacksEqual(resultItem, recipeResult))
			{
				recipes.remove(i);
			}
		}
	}
}
