package simple.block.chest.christmas;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelLargeChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityChristmasChestRenderer extends TileEntitySpecialRenderer {

	private static final ResourceLocation RES_TRAPPED_DOUBLE = new ResourceLocation("textures/entity/chest/trapped_double.png");
	private static final ResourceLocation RES_CHRISTMAS_DOUBLE = new ResourceLocation("textures/entity/chest/christmas_double.png");
	private static final ResourceLocation RES_NORMAL_DOUBLE = new ResourceLocation("textures/entity/chest/normal_double.png");
	private static final ResourceLocation RES_TRAPPED_SINGLE = new ResourceLocation("textures/entity/chest/trapped.png");
	private static final ResourceLocation RES_CHRISTMAS_SINGLE = new ResourceLocation("textures/entity/chest/christmas.png");
	private static final ResourceLocation RES_NORMAL_SINGLE = new ResourceLocation("textures/entity/chest/normal.png");

	/** The normal small chest model. */
	private ModelChest chestModel = new ModelChest();

	/** The large double chest model. */
	private ModelChest largeChestModel = new ModelLargeChest();

	/** If true, chests will be rendered with the Christmas present textures. */
	private boolean isChristmas;

	public TileEntityChristmasChestRenderer()
	{
		this.isChristmas = true;
	}

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double posX, double posY, double posZ, float p_180535_8_, int p_180535_9_) {
		this.renderTileEntityChestAt((TileEntityChest) tileEntity, posX, posY, posZ, p_180535_8_, p_180535_9_);
	}

	/**
	 * Renders the TileEntity for the chest at a position.
	 */
	private void renderTileEntityChestAt(TileEntityChest te, double posX, double posY, double posZ, float p_180538_8_, int p_180538_9_)
	{
		int j;

		if (!te.hasWorldObj())
		{
			j = 0;
		}
		else
		{
			Block block = te.getBlockType();
			j = te.getBlockMetadata();

			if (block instanceof BlockChest && j == 0)
			{
				((BlockChest)block).checkForSurroundingChests(te.getWorld(), te.getPos(), te.getWorld().getBlockState(te.getPos()));
				j = te.getBlockMetadata();
			}

			te.checkForAdjacentChests();
		}

		if (te.adjacentChestZNeg == null && te.adjacentChestXNeg == null)
		{
			ModelChest modelchest;

			if (te.adjacentChestXPos == null && te.adjacentChestZPos == null)
			{
				modelchest = this.chestModel;

				if (p_180538_9_ >= 0)
				{
					this.bindTexture(DESTROY_STAGES[p_180538_9_]);
					GlStateManager.matrixMode(5890);
					GlStateManager.pushMatrix();
					GlStateManager.scale(4.0F, 4.0F, 1.0F);
					GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
					GlStateManager.matrixMode(5888);
				}
				else if (te.getChestType() == 1)
				{
					this.bindTexture(RES_TRAPPED_SINGLE);
				}
				else if (this.isChristmas)
				{
					this.bindTexture(RES_CHRISTMAS_SINGLE);
				}
				else
				{
					this.bindTexture(RES_NORMAL_SINGLE);
				}
			}
			else
			{
				modelchest = this.largeChestModel;

				if (p_180538_9_ >= 0)
				{
					this.bindTexture(DESTROY_STAGES[p_180538_9_]);
					GlStateManager.matrixMode(5890);
					GlStateManager.pushMatrix();
					GlStateManager.scale(8.0F, 4.0F, 1.0F);
					GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
					GlStateManager.matrixMode(5888);
				}
				else if (te.getChestType() == 1)
				{
					this.bindTexture(RES_TRAPPED_DOUBLE);
				}
				else if (this.isChristmas)
				{
					this.bindTexture(RES_CHRISTMAS_DOUBLE);
				}
				else
				{
					this.bindTexture(RES_NORMAL_DOUBLE);
				}
			}

			GlStateManager.pushMatrix();
			GlStateManager.enableRescaleNormal();

			if (p_180538_9_ < 0)
			{
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			}

			GlStateManager.translate((float)posX, (float)posY + 1.0F, (float)posZ + 1.0F);
			GlStateManager.scale(1.0F, -1.0F, -1.0F);
			GlStateManager.translate(0.5F, 0.5F, 0.5F);
			short short1 = 0;

			if (j == 2)
			{
				short1 = 180;
			}

			if (j == 3)
			{
				short1 = 0;
			}

			if (j == 4)
			{
				short1 = 90;
			}

			if (j == 5)
			{
				short1 = -90;
			}

			if (j == 2 && te.adjacentChestXPos != null)
			{
				GlStateManager.translate(1.0F, 0.0F, 0.0F);
			}

			if (j == 5 && te.adjacentChestZPos != null)
			{
				GlStateManager.translate(0.0F, 0.0F, -1.0F);
			}

			GlStateManager.rotate((float)short1, 0.0F, 1.0F, 0.0F);
			GlStateManager.translate(-0.5F, -0.5F, -0.5F);
			float f1 = te.prevLidAngle + (te.lidAngle - te.prevLidAngle) * p_180538_8_;
			float f2;

			if (te.adjacentChestZNeg != null)
			{
				f2 = te.adjacentChestZNeg.prevLidAngle + (te.adjacentChestZNeg.lidAngle - te.adjacentChestZNeg.prevLidAngle) * p_180538_8_;

				if (f2 > f1)
				{
					f1 = f2;
				}
			}

			if (te.adjacentChestXNeg != null)
			{
				f2 = te.adjacentChestXNeg.prevLidAngle + (te.adjacentChestXNeg.lidAngle - te.adjacentChestXNeg.prevLidAngle) * p_180538_8_;

				if (f2 > f1)
				{
					f1 = f2;
				}
			}

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
	}

}
