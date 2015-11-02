package simple.block.chest.crafting;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.world.World;

public class ContainerCraftingChest extends Container {

	public final static int CRAFTING_SLOT_OFFSET = 48;
	private final static int SLOT_HEIGHT = 18;
	private final static int SLOT_WIDTH = 18;
	private int slotID = 0;
	
	protected TileEntityCraftingChest tileEntity;
	public IInventory craftResult = new InventoryCraftResult();

	private World worldObj;

	public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3) {
		@Override public ItemStack getStackInSlotOnClosing(int par1){ return null; }
		@Override public ItemStack decrStackSize(int index, int count) { return super.decrStackSize(index + CRAFTING_SLOT_OFFSET, count); }
		@Override public ItemStack getStackInSlot(int index) { return super.getStackInSlot(index + CRAFTING_SLOT_OFFSET); }
		@Override public void setInventorySlotContents(int index, ItemStack stack) { super.setInventorySlotContents(index + CRAFTING_SLOT_OFFSET, stack); }
	};
	
	public ContainerCraftingChest (InventoryPlayer inventoryPlayer, TileEntityCraftingChest te, World world) {

		this.tileEntity = te;
		te.subscribe(this);
		this.worldObj = world;

		addChestSlots();
		addCraftingSlots(inventoryPlayer);
		addPlayerInventory(inventoryPlayer);
		update();
	}

	public void update() {
		for(int i = 0; i < 9; i++) {
			craftMatrix.setInventorySlotContents(i, tileEntity.getStackInSlot(i + 48));
		}
		this.craftResult.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, this.worldObj));
	}

	private void addChestSlots() {

		for (int row = 0; row < 7; row++) {
			for (int column = 0; column < 9; column++) {

				int x = 8 + (SLOT_WIDTH * column);
				int y = 17 + (SLOT_HEIGHT * row);

				if (row < 2 || row > 4 || column < 2 || column > 6) {
					addSlotToContainer(new Slot(tileEntity, slotID++, x, y));
				}
			}
		}
	}

	private void addCraftingSlots(InventoryPlayer inventoryPlayer) {

		for(int row = 0; row < 3; row++) {
			for(int col = 0; col < 3; col++) {

				int x = 44 + SLOT_WIDTH * col;
				int y = 53 + SLOT_HEIGHT * row;
				addSlotToContainer(new Slot(tileEntity, slotID++, x, y));
			}
		}

		// Crafting result slot
		addSlotToContainer(new SlotCrafting(inventoryPlayer.player, craftMatrix, this.craftResult, 0, 106, 71));
	}

	private void addPlayerInventory(InventoryPlayer inventoryPlayer) {

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9,
						7 + j * 18, 154 + i * 18));
			}
		}

		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(inventoryPlayer, i, 7 + i * 18, 212));
		}
	}

	@Override
	// This is the part that deals with shift-clicking
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		
		// Slot IDs
		// 0 - 47  Chest
		// 48 - 56 Crafting Grid
		// 57      Crafting result
		// 58 - 84 Inventory
		// 85 - 93 Inventory Equipped
		
		ItemStack stack = null;
		Slot slotObject = (Slot) inventorySlots.get(slot);

		if (slotObject != null && slotObject.getHasStack()) {
			ItemStack stackInSlot = slotObject.getStack();
			stack = stackInSlot.copy();

			// Chest sends to inventory first, then to crafting grid, then to itself
			if (slot <= 47) {
				if (!this.mergeItemStack(stackInSlot, 58, 93, false)) {
					if (!this.mergeItemStack(stackInSlot, 48, 56, false)) {
						if (!this.mergeItemStack(stackInSlot, 0, 47, false)) {
							return null;
						}
					}
				}
			}
			
			// Crafting Grid sends first to chest, then to inventory, then to itself
			else if (slot >= 48 && slot <= 56) {
				if (!this.mergeItemStack(stackInSlot, 0, 47, false)) {
					if (!this.mergeItemStack(stackInSlot, 58, 93, false)) {
						if (!this.mergeItemStack(stackInSlot, 48, 56, false)) {
							return null;
						}
					}
				}
			}
			
			// Crafting Result sends first to inventory, then to chest
			else if (slot == 57) {
				if (!this.mergeItemStack(stackInSlot, 58, 93, false)) {
					if (!this.mergeItemStack(stackInSlot, 0, 47, false)) {
						return null;
					}
				}
			}
			
			// Inventory sends to chest, then to grid, then to itself
			else if (slot >= 58 && slot <= 93) {
				if (!this.mergeItemStack(stackInSlot, 0, 56, false)) {
					if (!this.mergeItemStack(stackInSlot, 58, 93, false)) {
						return null;
					}
				}
			}
			
			if (stackInSlot.stackSize == 0) {
				slotObject.putStack(null);
			} else {
				slotObject.onSlotChanged();
			}

			if (stackInSlot.stackSize == stack.stackSize) {
				return null;
			}
			slotObject.onPickupFromSlot(player, stackInSlot);
		}
		return stack;
	}
	
	@Override
	public void onContainerClosed(EntityPlayer par1EntityPlayer) {
		super.onContainerClosed(par1EntityPlayer);
		tileEntity.unsubscribe(this);
	}

	/**
	 * Called to determine if the current slot is valid for the stack merging (double-click) code. The stack passed in
	 * is null for the initial slot that was double-clicked.
	 */
	@Override
	public boolean canMergeSlot(ItemStack stack, Slot slot) {
		return slot.inventory != this.craftResult && super.canMergeSlot(stack, slot);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return tileEntity.isUseableByPlayer(player);
	}

	@Override
	public void onCraftMatrixChanged(IInventory par1IInventory) {
	}
	
}
