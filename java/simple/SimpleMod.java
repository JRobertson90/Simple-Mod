package simple;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import simple.block.SimpleBlocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid= SimpleMod.ID, name= SimpleMod.NAME, version = SimpleMod.VERSION)
public class SimpleMod {
	public static final String ID = "jayperdu_simple";
	public static final String NAME = "Simple Mod";
	public static final String VERSION = "1.8-11.14.3.1450-forge";

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		SimplePlayerModifier.setup();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		registerBlocks();
		loadRecipes();
		loadStackSizes();
	}

	private static void registerBlocks() {
		GameRegistry.registerBlock(SimpleBlocks.light_block, "light_block");
		GameRegistry.registerBlock(SimpleBlocks.light_block_air, "light_block_air");
	}

	private static void loadRecipes() {
		GameRegistry.addRecipe(new ItemStack(SimpleBlocks.light_block, 4), "###", "#Q#", "###", '#', Items.glowstone_dust, 'Q', Blocks.quartz_block);
		GameRegistry.addShapelessRecipe(new ItemStack(Items.string, 4), new ItemStack(Blocks.wool, 15));
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.sand, 4), Blocks.sandstone);
	}

	private static void loadStackSizes() {
		Items.egg.setMaxStackSize(64);
		Items.snowball.setMaxStackSize(64);
		Items.acacia_door.setMaxStackSize(64);
		Items.birch_door.setMaxStackSize(64);
		Items.dark_oak_door.setMaxStackSize(64);
		Items.iron_door.setMaxStackSize(64);
		Items.jungle_door.setMaxStackSize(64);
		Items.oak_door.setMaxStackSize(64);
		Items.spruce_door.setMaxStackSize(64);
		Items.sign.setMaxStackSize(64);
	}

}
