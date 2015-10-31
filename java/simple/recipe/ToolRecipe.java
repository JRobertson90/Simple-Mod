package simple.recipe;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by Jason Robertson on 10/31/15.
 */
public enum ToolRecipe {
    SWORD("X", "X", "#"),
    PICKAXE("XXX", " # ", " # "),
    SHOVEL("X", "#", "#"),
    AXE("XX ", "X# ", " # "),
    HOE("XX ", " # ", " # ");

    private List<Object> template;

    ToolRecipe(Object... params) {
        this.template = Lists.newArrayList(params);
    }

    public List<Object> getTemplate() {
        return Lists.newArrayList(template);
    }

}