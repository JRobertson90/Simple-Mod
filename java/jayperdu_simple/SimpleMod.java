package jayperdu_simple;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static net.minecraft.init.Items.*;

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
		loadRecipes();
		increaseStackSizes(egg, snowball, acacia_door, birch_door, dark_oak_door, iron_door, jungle_door, oak_door, spruce_door, sign);
	}

	private static void loadRecipes() {
		GameRegistry.addShapelessRecipe(new ItemStack(string, 4), new ItemStack(Blocks.wool, 15));
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.sand, 4), Blocks.sandstone);
	}

	private static void increaseStackSizes(Item... items) {
		for(Item i : items) {
			i.setMaxStackSize(64);
		}
	}

}
