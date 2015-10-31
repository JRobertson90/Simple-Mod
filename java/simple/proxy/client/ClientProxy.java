package simple.proxy.client;

import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import simple.block.SimpleBlocks;
import simple.christmasChest.ItemRendererChristmasChest;
import simple.christmasChest.TileEntityChristmasChest;
import simple.christmasChest.TileEntityChristmasChestRenderer;
import simple.craftingChest.ItemRendererCraftingChest;
import simple.craftingChest.TileEntityCraftingChest;
import simple.craftingChest.TileEntityCraftingChestRenderer;
import simple.entity.EntityTeleportArrow;
import simple.entity.EntityTorchArrow;
import simple.proxy.Proxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy implements Proxy {

	@Override
    public void registerRenderers() {
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
		ItemModelMesher itemModelMesher = renderItem.getItemModelMesher();

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityChristmasChest.class, new TileEntityChristmasChestRenderer());
		MinecraftForgeClient.registerItemRenderer(SimpleBlocks.christmas_chest.blockId, new ItemRendererChristmasChest());

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCraftingChest.class, new TileEntityCraftingChestRenderer());
		MinecraftForgeClient.registerItemRenderer(SimpleBlocks.crafting_chest.blockID, new ItemRendererCraftingChest());

		RenderingRegistry.registerEntityRenderingHandler(EntityTeleportArrow.class, new RenderTeleportArrow(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityTorchArrow.class, new RenderTorchArrow(Minecraft.getMinecraft().getRenderManager()));
	}
}