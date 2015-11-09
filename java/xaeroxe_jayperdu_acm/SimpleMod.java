package xaeroxe_jayperdu_acm;

import xaeroxe_jayperdu_acm.block.BlockLight;
import xaeroxe_jayperdu_acm.block.SimpleBlocks;
import xaeroxe_jayperdu_acm.block.chest.christmas.TileEntityChristmasChest;
import xaeroxe_jayperdu_acm.block.chest.christmas.TileEntityChristmasChestRenderer;
import xaeroxe_jayperdu_acm.block.chest.crafting.BlockCraftingChest;
import xaeroxe_jayperdu_acm.block.chest.crafting.TileEntityCraftingChest;
import xaeroxe_jayperdu_acm.block.chest.crafting.TileEntityCraftingChestRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLStateEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

import static net.minecraft.init.Items.*;

@Mod(modid= SimpleMod.ID, name= SimpleMod.NAME, version = SimpleMod.VERSION)
public class SimpleMod {
	public static final String ID = "xaeroxe_jayperdu_acm";
	public static final String NAME = "Simple Mod";
	public static final String VERSION = "1.8-11.14.3.1450-forge";

	@Mod.Instance(value = SimpleMod.ID)
	public static SimpleMod INSTANCE;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		SimplePlayerModifier.setup();
		NetworkRegistry.INSTANCE.registerGuiHandler(SimpleMod.INSTANCE, new SimpleGuiHandler());
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		loadRecipes();
		increaseStackSizes();
		registerItemRenderers(event);
	}

	private static void loadRecipes() {
		GameRegistry.addShapelessRecipe(new ItemStack(string, 4), new ItemStack(Blocks.wool, 15));
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.sand, 4), Blocks.sandstone);
	}

	private static void increaseStackSizes() {
		final Item[] items = {egg, snowball, acacia_door, birch_door, dark_oak_door, iron_door, jungle_door, oak_door, spruce_door, sign};
		for(Item i : items) {
			i.setMaxStackSize(64);
		}
	}

	private void registerItemRenderers(FMLStateEvent event) {
		if(event.getSide() == Side.CLIENT) {
			RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
			renderItem.getItemModelMesher().register(Item.getItemFromBlock(SimpleBlocks.light_block), 0, new ModelResourceLocation(SimpleMod.ID + ":" + BlockLight.NAME, "inventory"));
			renderItem.getItemModelMesher().register(Item.getItemFromBlock(SimpleBlocks.crafting_chest), 0, new ModelResourceLocation(SimpleMod.ID + ":" + BlockCraftingChest.NAME, "inventory"));
			renderItem.getItemModelMesher().register(Item.getItemFromBlock(SimpleBlocks.christmas_chest), 0, new ModelResourceLocation(SimpleMod.ID + ":" + BlockCraftingChest.NAME, "inventory"));
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCraftingChest.class, new TileEntityCraftingChestRenderer());
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntityChristmasChest.class, new TileEntityChristmasChestRenderer());
		}
	}

}
