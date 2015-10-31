package simple.item;

import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemToolMassive extends ItemTool {

	public Block[] blocksEffectiveAgainst;

    public ItemToolMassive(float attackDamage, ToolMaterial material, Set effectiveBlocks) {
        super(attackDamage, material, effectiveBlocks);
    }

    public void blastAway(EntityLivingBase entity, World world, int x, int y, int z) {

        BlockPos pos = new BlockPos(x, y, z);
        for (int i = 0; i < this.blocksEffectiveAgainst.length; ++i)
        {
            if (this.blocksEffectiveAgainst[i] == world.getBlockState(pos).getBlock())
            {
                world.destroyBlock(pos, true);
            }
        }
	}

    /**
     * Called when a Block is destroyed using this Item. Return true to trigger the "Use Item" statistic.
     */
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, Block blockIn, BlockPos pos, EntityLivingBase playerIn)
    {
        if ((double)blockIn.getBlockHardness(worldIn, pos) != 0.0D)
        {
            stack.damageItem(1, playerIn);
        }
        
        //0=north, 1=east, 2=south, 3=west.... according to this code
        int whichDirectionFacing = MathHelper.floor_double(playerIn.rotationYaw * 4.0F / 360.0F + 2.5D) & 3;

        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        if(whichDirectionFacing == 0 || whichDirectionFacing == 2) {

          blastAway(playerIn, worldIn, x + 1, y - 1, z);
          blastAway(playerIn, worldIn, x - 1, y - 1, z);
          blastAway(playerIn, worldIn, x + 1, y, z);
          blastAway(playerIn, worldIn, x - 1, y, z);
          blastAway(playerIn, worldIn, x - 1, y + 1, z);
          blastAway(playerIn, worldIn, x + 1, y + 1, z);
        }
        else {
        	blastAway(playerIn, worldIn, x, y - 1, z + 1);
            blastAway(playerIn, worldIn, x, y - 1, z - 1);
            blastAway(playerIn, worldIn, x, y, z + 1);
            blastAway(playerIn, worldIn, x, y, z - 1);
            blastAway(playerIn, worldIn, x, y + 1, z + 1);
            blastAway(playerIn, worldIn, x, y + 1, z - 1);
        }
        
        blastAway(playerIn, worldIn, x, y + 1, z);
        blastAway(playerIn, worldIn, x, y - 1, z);
        
        return true;
    }
	
}
