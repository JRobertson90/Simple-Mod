package simple.recipe;

import com.google.common.collect.Lists;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.List;

/**
 * Created by Jason Robertson on 10/31/15.
 */
public class ToolRecipeBuilder {
    private ItemStack material;

    private ToolRecipeBuilder(ItemStack material) {
        this.material = material;
    }

    public static ToolRecipeBuilder withMaterial(Item material) {
        return new ToolRecipeBuilder(new ItemStack(material, 1));
    }

    public static ToolRecipeBuilder withMaterial(ItemStack material) {
        return new ToolRecipeBuilder(material);
    }

    public ToolRecipeBuilder sword(Item output) {
        GameRegistry.addRecipe(new ItemStack(output, 1), merge(ToolRecipe.SWORD));
        return this;
    }

    public ToolRecipeBuilder pickaxe(Item output) {
        GameRegistry.addRecipe(new ItemStack(output, 1), merge(ToolRecipe.PICKAXE));
        return this;
    }

    public ToolRecipeBuilder axe(Item output) {
        GameRegistry.addRecipe(new ItemStack(output, 1), merge(ToolRecipe.AXE));
        return this;
    }

    public ToolRecipeBuilder shovel(Item output) {
        GameRegistry.addRecipe(new ItemStack(output, 1), merge(ToolRecipe.SHOVEL));
        return this;
    }

    public ToolRecipeBuilder hoe(Item output) {
        GameRegistry.addRecipe(new ItemStack(output, 1), merge(ToolRecipe.HOE));
        return this;
    }

    // GameRegistry.addRecipe(output, with(material));

    private Object[] merge(ToolRecipe recipe) {
        List<Object> params = Lists.newArrayList(recipe.getTemplate());
        params.addAll(Lists.newArrayList("X", material, "#", Items.stick));
        return params.toArray();
    }

}
