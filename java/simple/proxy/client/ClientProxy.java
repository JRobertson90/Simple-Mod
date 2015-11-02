package simple.proxy.client;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import simple.block.chest.christmas.TileEntityChristmasChest;
import simple.block.chest.christmas.TileEntityChristmasChestRenderer;
import simple.block.chest.crafting.TileEntityCraftingChest;
import simple.block.chest.crafting.TileEntityCraftingChestRenderer;
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
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCraftingChest.class, new TileEntityCraftingChestRenderer());

		RenderingRegistry.registerEntityRenderingHandler(EntityTeleportArrow.class, new RenderTeleportArrow(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityTorchArrow.class, new RenderTorchArrow(Minecraft.getMinecraft().getRenderManager()));
	}
}