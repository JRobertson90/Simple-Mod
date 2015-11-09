package xaeroxe_jayperdu_acm;

import xaeroxe_jayperdu_acm.block.chest.crafting.ContainerCraftingChest;
import xaeroxe_jayperdu_acm.block.chest.crafting.GuiCraftingChest;
import xaeroxe_jayperdu_acm.block.chest.crafting.TileEntityCraftingChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class SimpleGuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity tileEntity = world.getTileEntity(pos);
        if(tileEntity instanceof TileEntityCraftingChest){
            return new ContainerCraftingChest(player.inventory, (TileEntityCraftingChest) tileEntity, world);
        }
//        else if(tileEntity instanceof TileEntityChristmasChest) {
//            return new ContainerChest(player.inventory, (TileEntityChristmasChest) tileEntity, player);
//        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));

        if(tileEntity instanceof TileEntityCraftingChest){
            return new GuiCraftingChest(player.inventory, (TileEntityCraftingChest) tileEntity, world);
        }
//        else if(tileEntity instanceof TileEntityChristmasChest) {
//            return new GuiChristmasChest(player.inventory, (TileEntityChristmasChest) tileEntity, player);
//        }
        return null;
    }

}
