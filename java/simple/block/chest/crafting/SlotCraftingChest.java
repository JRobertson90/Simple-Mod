package simple.block.chest.crafting;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;

public class SlotCraftingChest extends Slot
{
    /** The craft matrix inventory linked to this result slot. */
    private final TileEntityCraftingChest craftingChest;

    /** The player that is using the GUI where this slot resides. */
    private final EntityPlayer thePlayer;
    /** The number of items that have been crafted so far. Gets passed to ItemStack.onCrafting before being reset. */
    private int amountCrafted;
    private static final String __OBFID = "CL_00001761";

    public SlotCraftingChest(EntityPlayer player, TileEntityCraftingChest craftingChest, IInventory p_i45790_3_, int slotIndex, int xPosition, int yPosition)
    {
        super(p_i45790_3_, slotIndex, xPosition, yPosition);
        this.thePlayer = player;
        this.craftingChest= craftingChest;
    }

    /**
     * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
     */
    public boolean isItemValid(ItemStack stack)
    {
        return false;
    }

    /**
     * Decrease the size of the stack in slot (first int arg) by the amount of the second int arg. Returns the new
     * stack.
     */
    public ItemStack decrStackSize(int amount)
    {
        if (this.getHasStack())
        {
            this.amountCrafted += Math.min(amount, this.getStack().stackSize);
        }

        return super.decrStackSize(amount);
    }

    /**
     * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood. Typically increases an
     * internal count then calls onCrafting(item).
     */
    protected void onCrafting(ItemStack stack, int amount)
    {
        this.amountCrafted += amount;
        this.onCrafting(stack);
    }

    /**
     * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood.
     */
    protected void onCrafting(ItemStack stack)
    {
        if (this.amountCrafted > 0)
        {
            stack.onCrafting(this.thePlayer.worldObj, this.thePlayer, this.amountCrafted);
        }

        this.amountCrafted = 0;

        if (stack.getItem() == Item.getItemFromBlock(Blocks.crafting_table))
        {
            this.thePlayer.triggerAchievement(AchievementList.buildWorkBench);
        }

        if (stack.getItem() instanceof ItemPickaxe)
        {
            this.thePlayer.triggerAchievement(AchievementList.buildPickaxe);
        }

        if (stack.getItem() == Item.getItemFromBlock(Blocks.furnace))
        {
            this.thePlayer.triggerAchievement(AchievementList.buildFurnace);
        }

        if (stack.getItem() instanceof ItemHoe)
        {
            this.thePlayer.triggerAchievement(AchievementList.buildHoe);
        }

        if (stack.getItem() == Items.bread)
        {
            this.thePlayer.triggerAchievement(AchievementList.makeBread);
        }

        if (stack.getItem() == Items.cake)
        {
            this.thePlayer.triggerAchievement(AchievementList.bakeCake);
        }

        if (stack.getItem() instanceof ItemPickaxe && ((ItemPickaxe)stack.getItem()).getToolMaterial() != Item.ToolMaterial.WOOD)
        {
            this.thePlayer.triggerAchievement(AchievementList.buildBetterPickaxe);
        }

        if (stack.getItem() instanceof ItemSword)
        {
            this.thePlayer.triggerAchievement(AchievementList.buildSword);
        }

        if (stack.getItem() == Item.getItemFromBlock(Blocks.enchanting_table))
        {
            this.thePlayer.triggerAchievement(AchievementList.enchantments);
        }

        if (stack.getItem() == Item.getItemFromBlock(Blocks.bookshelf))
        {
            this.thePlayer.triggerAchievement(AchievementList.bookcase);
        }

        if (stack.getItem() == Items.golden_apple && stack.getMetadata() == 1)
        {
            this.thePlayer.triggerAchievement(AchievementList.overpowered);
        }
    }

    public void onPickupFromSlot(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack)
    {
        this.onCrafting(par2ItemStack);

        for (int i = 0; i < 9; ++i)
        {
            ItemStack itemstack1 = craftingChest.getStackInSlot(i + 48);

            if (itemstack1 != null)
            {
                this.craftingChest.decrStackSize(i + 48, 1);

                if (itemstack1.getItem().hasContainerItem())
                {
                    ItemStack itemstack2 = itemstack1.getItem().getContainerItem(itemstack1);

                    if (itemstack2.isItemStackDamageable() && itemstack2.getItemDamage() > itemstack2.getMaxDamage())
                    {
                        MinecraftForge.EVENT_BUS.post(new PlayerDestroyItemEvent(thePlayer, itemstack2));
                        itemstack2 = null;
                    }

                    if (itemstack2 != null && !this.thePlayer.inventory.addItemStackToInventory(itemstack2))
                    {
                        if (this.craftingChest.getStackInSlot(i + 48) == null)
                        {
                            this.craftingChest.setInventorySlotContents(i + 48, itemstack2);
                        }
                        else
                        {
                            this.thePlayer.dropPlayerItemWithRandomChoice(itemstack2, false);
                        }
                    }
                }
            }
        }
    }
}