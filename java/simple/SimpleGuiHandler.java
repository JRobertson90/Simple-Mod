package simple;

import net.minecraft.inventory.ContainerChest;
import simple.block.chest.christmas.BlockChristmasChest;
import simple.block.chest.christmas.GuiChristmasChest;
import simple.block.chest.christmas.TileEntityChristmasChest;
import simple.block.chest.crafting.ContainerCraftingChest;
import simple.block.chest.crafting.GuiCraftingChest;
import simple.block.chest.crafting.TileEntityCraftingChest;
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
        else if(tileEntity instanceof TileEntityChristmasChest) {
            return new ContainerChest(player.inventory, ((BlockChristmasChest) tileEntity.getBlockType()).getLockableContainer(world, pos), player);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity tileEntity = world.getTileEntity(pos);

        if(tileEntity instanceof TileEntityCraftingChest){
            return new GuiCraftingChest(player.inventory, (TileEntityCraftingChest) tileEntity, world);
        }
        else if(tileEntity instanceof TileEntityChristmasChest) {
            return new GuiChristmasChest(player.inventory, ((BlockChristmasChest) tileEntity.getBlockType()).getLockableContainer(world, pos), player);
        }
        return null;
    }

}
