package simple;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import simple.proxy.Proxy;

@Mod(modid= SimpleMod.ID, name= SimpleMod.NAME, version = SimpleMod.VERSION)
public class SimpleMod {
	public static final String ID = "simple_mod_jayperdu_xaeroxe";
	public static final String NAME = "Simple Mod";
	public static final String VERSION = "1.0";
	
	@SidedProxy(clientSide="simple.proxy.client.ClientProxy", serverSide="simple.proxy.server.ServerProxy")
	public static Proxy proxy;
	
	@EventHandler
    public void preInit(FMLPreInitializationEvent event) {
		SimpleModSettings.loadConfig(event);
		proxy.registerEventHandlers();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {

	}
	
	@EventHandler
    public void postInit(FMLPostInitializationEvent event) {
		
	}
	
}
