package jayperdu_simple.block.chest.crafting;

import jayperdu_simple.SimpleMod;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityCraftingChestRenderer extends TileEntitySpecialRenderer {

    private static final ResourceLocation RES_CRAFTING_CHEST = new ResourceLocation(SimpleMod.ID + ":textures/entity/chest/crafting.png");
    private ModelChest simpleChest = new ModelChest();

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

        modelchest = this.simpleChest;

        if (p_180538_9_ >= 0) {
            this.bindTexture(DESTROY_STAGES[p_180538_9_]);
            GlStateManager.matrixMode(5890);
            GlStateManager.pushMatrix();
            GlStateManager.scale(4.0F, 4.0F, 1.0F);
            GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
            GlStateManager.matrixMode(5888);
        } else {
            this.bindTexture(RES_CRAFTING_CHEST);
        }

        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();

        if (p_180538_9_ < 0) {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        }

        GlStateManager.translate((float)posX, (float)posY + 1.0F, (float)posZ + 1.0F);
        GlStateManager.scale(1.0F, -1.0F, -1.0F);
        GlStateManager.translate(0.5F, 0.5F, 0.5F);

        short rotation = 0;

        switch(facing) {
            case 2: rotation = 180; break;
            case 3: rotation = 0; break;
            case 4: rotation = 90; break;
            case 5: rotation = -90; break;
        }

        GlStateManager.rotate((float)rotation, 0.0F, 1.0F, 0.0F);
        GlStateManager.translate(-0.5F, -0.5F, -0.5F);
        float f1 = te.prevLidAngle + (te.lidAngle - te.prevLidAngle) * p_180538_8_;
        float f2;

        f1 = 1.0F - f1;
        f1 = 1.0F - f1 * f1 * f1;
        modelchest.chestLid.rotateAngleX = -(f1 * (float)Math.PI / 2.0F);
        modelchest.renderAll();
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        if (p_180538_9_ >= 0)
        {
            GlStateManager.matrixMode(5890);
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5888);
        }
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double posX, double posY, double posZ, float p_180535_8_, int p_180535_9_) {
        this.renderTileEntityChestAt((TileEntityChest)tileEntity, posX, posY, posZ, p_180535_8_, p_180535_9_);
    }

}