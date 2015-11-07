package simple.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockStairsWool extends BlockStairs {

	public BlockStairsWool(IBlockState modelState) {
		super(modelState);
	}

	public static final String COLORS[] = { "White", "Orange", "Magenta", "Light Blue", "Yellow", "Lime",
		"Pink", "Gray", "Light Gray", "Cyan", "Purple", "Blue", "Brown", "Green", "Red", "Black"
	};

	public static final BlockStairs[] WOOL_STAIRS = new BlockStairs[COLORS.length];

	static {
		
		for(int i = 0; i < COLORS.length; i++) {
			WOOL_STAIRS[i] = new BlockStairsWool(Blocks.wool.getDefaultState());
			WOOL_STAIRS[i].setUnlocalizedName(COLORS[i]+"WoolStairs");
			WOOL_STAIRS[i].setHardness(0.8F).setStepSound(Block.soundTypeCloth);
		}
	}

	//----------------------
	//  Register
	//----------------------
	public static void register() {
		
		for(int i = 0; i < COLORS.length; i++) {
			GameRegistry.registerBlock(WOOL_STAIRS[i], COLORS[i] + "WoolStairs");
		}
	}

	//----------------------
	//  Recipes
	//----------------------
	public static void loadRecipes() {
		
		for(int i = 0; i < COLORS.length; i++) {
			GameRegistry.addRecipe(new ItemStack(WOOL_STAIRS[i], 4), "  #", " ##", "###", '#', new ItemStack(Blocks.wool, 1 ,i));
		}
	}
	
}
