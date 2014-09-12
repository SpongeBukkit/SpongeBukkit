package br.com.gamemods.spongebukkit.mod;

import br.com.gamemods.spongebukkit.server.BukkitServer;
import br.com.gamemods.spongebukkit.server.VolatileServer;
import com.google.common.base.Preconditions;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLServerAboutToStartEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.server.MinecraftServer;
import org.bukkit.Bukkit;

public class SpongeForgeListener
{
    private final SpongeBukkitMod mod;

    public SpongeForgeListener(SpongeBukkitMod mod)
    {
        this.mod = Preconditions.checkNotNull(mod);
    }

    @Mod.EventHandler
    public void on(FMLServerAboutToStartEvent event)
    {
        mod.getLogger().info("Initializing a new bukkit server");
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

        volatileServer.setCurrentServer(new BukkitServer(mod, minecraftServer));
    }

    @SubscribeEvent
    public void on(FMLServerStoppingEvent event)
    {
        Bukkit.shutdown();
    }

}
