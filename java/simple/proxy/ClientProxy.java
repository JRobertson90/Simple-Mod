package simple.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import simple.SimpleMod;
import simple.block.BlockLight;
import simple.block.SimpleBlocks;
import simple.block.chest.christmas.BlockChristmasChest;
import simple.block.chest.christmas.TileEntityChristmasChest;
import simple.block.chest.christmas.TileEntityChristmasChestRenderer;
import simple.block.chest.crafting.BlockCraftingChest;
import simple.block.chest.crafting.TileEntityCraftingChest;
import simple.block.chest.crafting.TileEntityCraftingChestRenderer;

/**
 * Created by Jason Robertson on 11/11/15.
 */
public class ClientProxy implements Proxy {

    @Override
    public void register() {
        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        renderItem.getItemModelMesher().register(Item.getItemFromBlock(SimpleBlocks.light_block), 0, new ModelResourceLocation(SimpleMod.ID + ":" + BlockLight.NAME, "inventory"));
        renderItem.getItemModelMesher().register(Item.getItemFromBlock(SimpleBlocks.crafting_chest), 0, new ModelResourceLocation(SimpleMod.ID + ":" + BlockCraftingChest.NAME, "inventory"));
        renderItem.getItemModelMesher().register(Item.getItemFromBlock(SimpleBlocks.christmas_chest), 0, new ModelResourceLocation(SimpleMod.ID + ":" + BlockChristmasChest.NAME, "inventory"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCraftingChest.class, new TileEntityCraftingChestRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityChristmasChest.class, new TileEntityChristmasChestRenderer());
    }
}
