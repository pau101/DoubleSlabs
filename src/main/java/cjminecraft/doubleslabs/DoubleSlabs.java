package cjminecraft.doubleslabs;

import cjminecraft.doubleslabs.api.ContainerSupport;
import cjminecraft.doubleslabs.api.SlabSupport;
import cjminecraft.doubleslabs.client.render.TileEntityRendererDoubleSlab;
import cjminecraft.doubleslabs.client.render.TileEntityRendererVerticalSlab;
import cjminecraft.doubleslabs.network.PacketHandler;
import cjminecraft.doubleslabs.proxy.ClientProxy;
import cjminecraft.doubleslabs.proxy.IProxy;
import cjminecraft.doubleslabs.proxy.ServerProxy;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(DoubleSlabs.MODID)
public class DoubleSlabs {
    public static final String MODID = "doubleslabs";
    public static final Logger LOGGER = LogManager.getFormatterLogger(MODID);

    public static IProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    public DoubleSlabs() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SERVER_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
//        bus.addListener(Config::onConfigLoad);
//        bus.addListener(Config::onConfigReload);
        proxy.addListeners(bus);
        bus.addListener(proxy::setup);
        bus.addListener(proxy::clientSetup);
    }

    public void clientSetup(FMLClientSetupEvent event) {

    }

}
