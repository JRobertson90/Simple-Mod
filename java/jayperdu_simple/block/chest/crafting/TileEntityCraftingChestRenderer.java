package jayperdu_simple.block.chest.crafting;

import jayperdu_simple.SimpleMod;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

@SideOnly(Side.CLIENT)
public class TileEntityCraftingChestRenderer extends TileEntitySpecialRenderer {

    private static final ResourceLocation RES_CRAFTING_CHEST = new ResourceLocation(SimpleMod.ID + ":textures/entity/chest/crafting.png");

    /** The normal small chest model. */
    private ModelChest chestModel = new ModelChest();

    /**
     * Renders the TileEntity for the chest at a position.
     */
    private void renderTileEntityChestAt(TileEntityChest te, double posX, double posY, double posZ, float p_180538_8_, int p_180538_9_)
    {
        int facing;

        if (!te.hasWorldObj()) {
            facing = 0;
        }
        else {
            facing = te.getBlockMetadata();
        }

        ModelChest modelchest;

        modelchest = this.chestModel;
        this.bindTexture(RES_CRAFTING_CHEST);

        GL11.glPushMatrix();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glTranslatef((float)posX - 0.06F, (float)posY + 1.06F, (float)posZ + 1.06F);
        GL11.glScalef(1.12F, -1.06F, -1.12F);
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);

        short rotation = 0;

        switch(facing) {
            case 2: rotation = 180; break;
            case 3: rotation = 0; break;
            case 4: rotation = 90; break;
            case 5: rotation = -90; break;
        }

        GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        float f1 = te.prevLidAngle + (te.lidAngle - te.prevLidAngle) * p_180538_8_;
        float f2;

        f1 = 1.0F - f1;
        f1 = 1.0F - f1 * f1 * f1;
        modelchest.chestLid.rotateAngleX = -(f1 * (float)Math.PI / 2.0F);
        modelchest.renderAll();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double posX, double posY, double posZ, float p_180535_8_, int p_180535_9_) {
        this.renderTileEntityChestAt((TileEntityChest)tileEntity, posX, posY, posZ, p_180535_8_, p_180535_9_);
    }

}