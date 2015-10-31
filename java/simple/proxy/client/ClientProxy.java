package simple.proxy.client;

import acm.proxy.Proxy;
import acm.proxy.client.ClientEventHandler;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import simple.block.SimpleBlocks;
import simple.christmasChest.ItemRendererChristmasChest;
import simple.christmasChest.TileEntityChristmasChest;
import simple.christmasChest.TileEntityChristmasChestRenderer;
import simple.craftingChest.ItemRendererCraftingChest;
import simple.craftingChest.TileEntityCraftingChest;
import simple.craftingChest.TileEntityCraftingChestRenderer;
import simple.entity.EntityTeleportArrow;
import simple.entity.EntityTorchArrow;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy implements Proxy {

	@Override
	public void registerEventHandlers() {
		MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
		MinecraftForge.EVENT_BUS.register(new ServerAndClientEventHandler());
	}

	@Override
    public void registerRenderers() {

		RenderingRegistry.registerEntityRenderingHandler(EntityTeleportArrow.class, new RenderTeleportArrow());
		RenderingRegistry.registerEntityRenderingHandler(EntityTorchArrow.class, new RenderTorchArrow());

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityChristmasChest.class, new TileEntityChristmasChestRenderer());
		MinecraftForgeClient.registerItemRenderer(SimpleBlocks.christmas_chest.blockId, new ItemRendererChristmasChest());

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCraftingChest.class, new TileEntityCraftingChestRenderer());
		MinecraftForgeClient.registerItemRenderer(SimpleBlocks.crafting_chest.blockID, new ItemRendererCraftingChest());
	}
}