package simple.tool;

import net.minecraft.item.ItemPickaxe;

//This class created to circumvent protection on ItemPickaxe constructor
public class ItemPickaxeWrapper extends ItemPickaxe{
	public ItemPickaxeWrapper(ToolMaterial p_i45347_1_) {
		super(p_i45347_1_);
	}
}
