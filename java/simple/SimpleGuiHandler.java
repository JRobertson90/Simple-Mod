package simple;

import simple.christmasChest.GuiChristmasChest;
import simple.christmasChest.TileEntityChristmasChest;
import simple.craftingChest.ContainerCraftingChest;
import simple.craftingChest.GuiCraftingChest;
import simple.craftingChest.TileEntityCraftingChest;
import net.minecraft.block.BlockChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class SimpleGuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
		if(tileEntity instanceof TileEntityCraftingChest){
            return new ContainerCraftingChest(player.inventory, (TileEntityCraftingChest) tileEntity, world, x, y, z);
		}
		else if(tileEntity instanceof TileEntityChristmasChest) {
        	BlockChest chest = (BlockChest) world.getBlockState(new BlockPos(x, y, z)).getBlock();
            return new ContainerChest(player.inventory, chest.getInventory(world, x, y, z));
        }
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));

		if(tileEntity instanceof TileEntityCraftingChest){
            return new GuiCraftingChest(player.inventory, (TileEntityCraftingChest) tileEntity, world, x, y, z);
		}
		else if(tileEntity instanceof TileEntityChristmasChest) {
			BlockChest chest = (BlockChest) world.getBlockState(new BlockPos(x, y, z)).getBlock();
        	return new GuiChristmasChest(player.inventory, chest.getInventory(world, x, y, z));
        }
        return null;
	}

}
