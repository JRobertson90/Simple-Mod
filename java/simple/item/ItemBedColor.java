package simple.item;

import java.awt.Color;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBed;
import net.minecraft.item.ItemStack;
import simple.SimpleRecipes;
import simple.block.BlockBedColor;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemBedColor extends ItemBed {

	public final int color;
	public final static String colors[] = {"black", "red", "green", "brown", "blue", "purple", "cyan", "light Gray",
		"gray", "pink", "lime", "yellow", "light Blue", "magenta", "orange", "white"};
	
	public final static Block[] blocks = new Block[colors.length];
	public final static Item[] items = new Item[colors.length];
	public final static Color[] rgbColors = {new Color(221,221,221),//0
												new Color(219,125,62),//1
												new Color(179,80,188),//2
												new Color(107,138,201),//3
												new Color(177,166,39),//4
												new Color(65,174,56),//5
												new Color(208,132,153),//6
												new Color(64,64,64),//7
												new Color(154,161,161),//8
												new Color(46,110,137),//9
												new Color(126,61,181),//10
												new Color(46,56,141),//11
												new Color(79,50,31),//12
												new Color(53,70,27),//13
												new Color(150,52,48),//14
												new Color(25,22,22)//15
												};

	//--------------------------
	// Constructor
	//--------------------------
	public ItemBedColor(int color) {
		super();
		this.color = color;
	}
	
	public static Color getRGB(int input)
	{
		return input>=0 && input<=15 ?  rgbColors[input] : new Color(0,0,0);
	}
	
	public static int getWoolValueFromColor(Color input)
	{
		int currentClosestMatch = 0;
		int currentSmallestDiff = 765;
		for(int i = 0;i<rgbColors.length;i++)
		{
			int redDiff = Math.abs(input.getRed()-rgbColors[i].getRed());
			int greenDiff = Math.abs(input.getGreen()-rgbColors[i].getGreen());
			int blueDiff = Math.abs(input.getBlue()-rgbColors[i].getBlue());
			int totalDiff = redDiff+greenDiff+blueDiff;
			if(totalDiff<currentSmallestDiff)
			{
				currentClosestMatch = i;
				currentSmallestDiff = totalDiff;
			}
		}
		return currentClosestMatch;
	}
	
	public static void loadRecipes() {
		
		// Remove original recipe for creating beds
		SimpleRecipes.removeRecipe(new ItemStack(Items.bed));
		
		// Recipe for colored beds = an original bed + the colored dye
		for(int i = 0; i < colors.length; i++) {
			
			for(int k = 0; k < colors.length; k++) {
				
				GameRegistry.addShapelessRecipe(new ItemStack(items[i]), items[k], new ItemStack(Items.dye, 1, i));
			}
			//This line is no longer needed, Xaeroxe's code below replaces it.
			//GameRegistry.addRecipe(new ItemStack(ItemBedColor.items[i]), new Object[] {"XXX","###",'X',new ItemStack(Block.cloth,1,15-i),'#',Block.planks});
		}
		//Code written by Xaeroxe, adds recipes for beds with
		//every possible combination of wool blocks.
		for(int x = 0; x < colors.length; x++)
		{
			for(int y = 0; y < colors.length; y++)
			{
				for(int z = 0; z < colors.length; z++)
				{
					//The nested for statements will go through every possible combination of x y and z.
					//x y and z represent the colors to be used.
					//Crafting recipe like so:
					//xyz
					//ppp
					//where p is wooden planks
					if(x==y)
					{
						GameRegistry.addRecipe(new ItemStack(items[x]), "xyz","ppp",'x',new ItemStack(Blocks.wool,1,15-x),'y',new ItemStack(Blocks.wool,1,15-y),'z',new ItemStack(Blocks.wool,1,15-z),'p',Blocks.planks);
					}
					else if(y==z)
					{
						GameRegistry.addRecipe(new ItemStack(items[y]), "xyz","ppp",'x',new ItemStack(Blocks.wool,1,15-x),'y',new ItemStack(Blocks.wool,1,15-y),'z',new ItemStack(Blocks.wool,1,15-z),'p',Blocks.planks);
					}
					else if(x==z)
					{
						GameRegistry.addRecipe(new ItemStack(items[z]), "xyz","ppp",'x',new ItemStack(Blocks.wool,1,15-x),'y',new ItemStack(Blocks.wool,1,15-y),'z',new ItemStack(Blocks.wool,1,15-z),'p',Blocks.planks);
					}
					else
					{
						Color xcolor = getRGB(x);
						Color ycolor = getRGB(y);
						Color zcolor = getRGB(z);
						int redValue = (xcolor.getRed() + ycolor.getRed() + zcolor.getRed())/3;
						int greenValue = (xcolor.getGreen() + ycolor.getGreen() + zcolor.getGreen())/3;
						int blueValue = (xcolor.getBlue() + ycolor.getBlue() + zcolor.getBlue())/3;
						Color mixedColor = new Color(redValue, greenValue, blueValue);
						int colorId = getWoolValueFromColor(mixedColor);
						GameRegistry.addRecipe(new ItemStack(items[colorId]), "xyz","ppp",'x',new ItemStack(Blocks.wool,1,15-x),'y',new ItemStack(Blocks.wool,1,15-y),'z',new ItemStack(Blocks.wool,1,15-z),'p',Blocks.planks);
					}
				}
			}
		}
	}
	
	public static void register() {
		
		initialize();
		
		for(int i = 0; i < colors.length; i++) {
			
			GameRegistry.registerBlock(blocks[i], "block_bed_" + colors[i]);
			GameRegistry.registerItem(items[i], "item_bed_" + colors[i]);
		}
	}
	
	private static void initialize() {

		for(int i = 0; i < colors.length; i++) {

			blocks[i] = new BlockBedColor(i).setHardness(0.2F).setUnlocalizedName("block_bed_" + colors[i]);
			items[i] = new simple.item.ItemBedColor(i).setMaxStackSize(1).setUnlocalizedName("item_bed_" + colors[i]);
		}
	}
    
}