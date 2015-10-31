package simple.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;
import simple.block.moses.ItemMosesStaff;
import simple.ranged.ItemTeleportBow;
import simple.ranged.ItemTorchBow;
import net.minecraftforge.common.util.EnumHelper;

//Next Available ID = 3280

public class SimpleItems {
	public static Item moses_staff = new ItemMosesStaff(3279).setMaxStackSize(1).setUnlocalizedName("itemMosesStaff");
	public static Item glass_door = new ItemGlassDoor(3249).setCreativeTab(CreativeTabs.tabRedstone);

	public static ToolMaterial blue_tool = EnumHelper.addToolMaterial("BLUE", 2, 670, 6.0F, 2.0F, 14);
	public static ItemSword blue_sword = (ItemSword) (new ItemSword(blue_tool)).setUnlocalizedName("blue_sword");
	public static ItemPickaxe blue_pickaxe = (ItemPickaxe) (new ItemPickaxe(blue_tool)).setUnlocalizedName("blue_pickaxe");
	public static ItemAxe blue_axe = (ItemAxe) (new ItemAxe(blue_tool)).setUnlocalizedName("blue_axe");
	public static ItemSpade blue_shovel = (ItemSpade) (new ItemSpade(blue_tool)).setUnlocalizedName("blue_shovel");
	public static ItemHoe blue_hoe = (ItemHoe) (new ItemHoe(blue_tool)).setUnlocalizedName("blue_hoe");

	public static ToolMaterial red_tool = EnumHelper.addToolMaterial("RED",2, 670, 6.0F, 2.0F, 14);
	public static ItemSword red_sword = (ItemSword) (new ItemSword(red_tool)).setUnlocalizedName("red_sword");
	public static ItemPickaxe red_pickaxe = (ItemPickaxe) (new ItemPickaxe(red_tool)).setUnlocalizedName("red_pickaxe");
	public static ItemAxe red_axe = (ItemAxe) (new ItemAxe(red_tool)).setUnlocalizedName("red_axe");
	public static ItemSpade red_shovel = (ItemSpade) (new ItemSpade(red_tool)).setUnlocalizedName("red_shovel");
	public static ItemHoe red_hoe = (ItemHoe) (new ItemHoe(red_tool)).setUnlocalizedName("red_hoe");

	public static ItemTeleportBow bow_teleport = (ItemTeleportBow) new ItemTeleportBow(3206).setUnlocalizedName("bow_teleport").setFull3D();
	public static Item arrow_teleport = (new Item()).setUnlocalizedName("arrow_teleport").setCreativeTab(CreativeTabs.tabCombat);

	public static ItemTorchBow bow_torch = (ItemTorchBow) new ItemTorchBow(3210).setUnlocalizedName("bow_torch").setFull3D();
	public static Item arrow_torch = (new Item()).setUnlocalizedName("arrow_torch").setCreativeTab(CreativeTabs.tabCombat);

	public static Item diamond_sword_alt1 = new ItemSword(ToolMaterial.EMERALD).setUnlocalizedName("diamond_sword_alt1");
	public static Item diamond_sword_alt2 = new ItemSword(ToolMaterial.EMERALD).setUnlocalizedName("diamond_sword_alt2");
	public static Item diamond_sword_alt3 = new ItemSword(ToolMaterial.EMERALD).setUnlocalizedName("diamond_sword_alt3");
	public static Item diamond_sword_alt4 = new ItemSword(ToolMaterial.EMERALD).setUnlocalizedName("diamond_sword_alt4");
	public static Item diamond_sword_alt5 = new ItemSword(ToolMaterial.EMERALD).setUnlocalizedName("diamond_sword_alt5");
}


