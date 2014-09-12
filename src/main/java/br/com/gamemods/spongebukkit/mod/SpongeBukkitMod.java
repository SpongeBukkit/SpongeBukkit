package br.com.gamemods.spongebukkit.mod;

import br.com.gamemods.spongebukkit.server.BukkitServer;
import br.com.gamemods.spongebukkit.server.VolatileServer;
import com.google.common.base.Preconditions;
import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerAboutToStartEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.Logger;
import org.bukkit.Bukkit;

/**
 * The forge mod class, it will setup the bukkit environment and load bukkit plugins.
 */
@Mod(modid = "SpongeBukkit")
public class SpongeBukkitMod extends DummyModContainer
{
    private Logger logger;

    @Mod.EventHandler
    void onPreInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        Bukkit.setServer(new VolatileServer());
        //MinecraftForge.EVENT_BUS.register(new SpongeForgeListener(this));
        //FMLCommonHandler.instance().bus().register(new SpongeForgeListener(this));
    }

    @Mod.EventHandler
    public void on(FMLServerAboutToStartEvent event)
    {
        getLogger().info("Initializing a new bukkit server");
        MinecraftServer minecraftServer = event.getServer();
        VolatileServer volatileServer;
        try
        {
            volatileServer = (VolatileServer)Bukkit.getServer();
            Preconditions.checkNotNull(volatileServer);
        }
        catch (Exception e)
        {
            throw new IllegalStateException("Bukkit.getServer is NOT a VolatileServer!",e);
        }

        volatileServer.setCurrentServer(new BukkitServer(this, minecraftServer));
    }

    @Mod.EventHandler
    public void on(FMLServerStoppingEvent event)
    {
        Bukkit.shutdown();
    }

    public Logger getLogger()
    {
        return logger;
    }
}
