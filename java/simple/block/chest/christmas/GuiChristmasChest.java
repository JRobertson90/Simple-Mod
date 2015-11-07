package simple.block.chest.christmas;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiChristmasChest extends GuiContainer {

	private static final ResourceLocation christmasChestResourceLocation = new ResourceLocation("simple:textures/gui/container/christmas_chest.png");

    /**
     * window height is calculated with this values, the more rows, the heigher
     */
    private int inventoryRows;

    public GuiChristmasChest(IInventory playerInventory, IInventory chestInventory, EntityPlayer player)
    {
        super(new ContainerChest(playerInventory, chestInventory, player));
        this.allowUserInput = false;
        short short1 = 222;
        int i = short1 - 108;
        this.inventoryRows = chestInventory.getSizeInventory() / 9;
        this.ySize = i + this.inventoryRows * 18;
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(christmasChestResourceLocation);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.inventoryRows * 18 + 17);
        this.drawTexturedModalRect(k, l + this.inventoryRows * 18 + 17, 0, 126, this.xSize, 96);
    }
	
}