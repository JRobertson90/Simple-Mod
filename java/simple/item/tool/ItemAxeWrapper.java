package simple.item.tool;

import net.minecraft.item.ItemAxe;

//This class created to circumvent protection on ItemAxe constructor
public class ItemAxeWrapper extends ItemAxe {
	public ItemAxeWrapper(ToolMaterial p_i45327_1_) {
		super(p_i45327_1_);
	}
}
