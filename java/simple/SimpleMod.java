package simple;

import net.minecraft.init.Items;
import net.minecraftforge.fml.common.SidedProxy;
import simple.block.BlockLight;
import simple.block.BlockLightAir;
import simple.block.SimpleBlocks;
import simple.block.chest.christmas.BlockChristmasChest;
import simple.block.chest.christmas.TileEntityChristmasChest;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import simple.block.chest.crafting.BlockCraftingChest;
import simple.block.chest.crafting.TileEntityCraftingChest;
import simple.proxy.Proxy;

import static net.minecraft.init.Items.*;

@Mod(modid= SimpleMod.ID, name= SimpleMod.NAME, version = SimpleMod.VERSION)
public class SimpleMod {
	public static final String ID = "jayperdu_simple";
	public static final String NAME = "Simple Mod";
	public static final String VERSION = "2.0_minecraft-1.8";

	@Mod.Instance(value = SimpleMod.ID)
	public static SimpleMod INSTANCE;

	@SidedProxy(clientSide="simple.proxy.ClientProxy", serverSide="simple.proxy.ServerProxy")
	public static Proxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		SimplePlayerModifier.setup();
		NetworkRegistry.INSTANCE.registerGuiHandler(SimpleMod.INSTANCE, new SimpleGuiHandler());
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		register();
		loadRecipes();
		increaseStackSizes();
		proxy.register();
	}

	private static void register() {
		GameRegistry.registerBlock(SimpleBlocks.light_block, BlockLight.NAME);
		GameRegistry.registerBlock(SimpleBlocks.light_block_air, BlockLightAir.NAME);
		GameRegistry.registerBlock(SimpleBlocks.crafting_chest, BlockCraftingChest.NAME);
		GameRegistry.registerBlock(SimpleBlocks.christmas_chest, BlockChristmasChest.NAME);

		GameRegistry.registerTileEntity(TileEntityCraftingChest.class, "jayperdu_crafting_chest_tile");
		GameRegistry.registerTileEntity(TileEntityChristmasChest.class, "jayperdu_christmas_chest_tile");

	}

	private static void loadRecipes() {
		GameRegistry.addShapelessRecipe(new ItemStack(string, 4), new ItemStack(Blocks.wool, 15));
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.sand, 4), Blocks.sandstone);
		GameRegistry.addRecipe(new ItemStack(SimpleBlocks.light_block, 4), "###", "#Q#", "###", '#', Items.glowstone_dust, 'Q', Blocks.quartz_block);
		GameRegistry.addRecipe(new ItemStack(SimpleBlocks.christmas_chest, 1), "XXX", "X#X", "XXX", 'X', new ItemStack(Blocks.wool, 1, 14), '#', Blocks.chest);
		GameRegistry.addRecipe(new ItemStack(SimpleBlocks.crafting_chest, 1), "XXX", "X#X", "XXX", 'X', Blocks.planks, '#', Blocks.crafting_table);
		GameRegistry.addShapelessRecipe(new ItemStack(SimpleBlocks.crafting_chest, 1), Blocks.chest, Blocks.crafting_table);
	}

	private static void increaseStackSizes() {
		final Item[] items = {egg, snowball, acacia_door, birch_door, dark_oak_door, iron_door, jungle_door, oak_door, spruce_door, sign};
		for(Item i : items) {
			i.setMaxStackSize(64);
		}
	}

}
