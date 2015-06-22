package simple.proxy.server;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import simple.proxy.Proxy;

/**
 * Created by Jason Robertson on 6/21/15.
 */
public class ServerProxy implements Proxy {
    @Override
    public void registerEventHandlers() {
        MinecraftForge.EVENT_BUS.register(new ServerHandler());
        FMLCommonHandler.instance().bus().register(new ServerHandler());
    }
}
