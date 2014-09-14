package br.com.gamemods.spongebukkit.mod;

import com.google.common.base.Preconditions;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLServerAboutToStartEvent;

/**
 * Handles FML Events
 */
public class SpongeFMLEventListener
{
    private final SpongeBukkitMod mod;

    public SpongeFMLEventListener(SpongeBukkitMod mod)
    {
        this.mod = Preconditions.checkNotNull(mod);
    }

    @Mod.EventHandler
    public void on(FMLServerAboutToStartEvent event)
    {
        mod.getLogger().info("###### ServerAboutToStartEvent ######");
    }
}
