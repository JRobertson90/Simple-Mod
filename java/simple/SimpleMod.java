package simple;

import acm.proxy.Proxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid= SimpleMod.ID, name= SimpleMod.NAME, version = SimpleMod.VERSION)
public class SimpleMod {
	public static final String ID = "jayperdu_simple";
	public static final String NAME = "Simple Mod";
	public static final String VERSION = "2.0-1.8mc";

	@SidedProxy(clientSide="acm.proxy.client.ClientProxy", serverSide="acm.proxy.server.ServerProxy")
	public static Proxy proxy;

	@Mod.Instance(value = SimpleMod.ID)
	public static SimpleMod instance;

	@EventHandler
    public void preInit(FMLPreInitializationEvent event) {
		SimplePlayerModifier.setup();
		proxy.registerEventHandlers();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		SimpleRegistry.load();
		SimpleRecipes.load();
		SimpleStackSizes.load();

		proxy.registerRenderers();
	}
	
	@EventHandler
    public void postInit(FMLPostInitializationEvent event) {}
	
}
