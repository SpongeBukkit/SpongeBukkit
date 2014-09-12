package br.com.gamemods.spongebukkit.mod;

import com.google.common.base.Preconditions;

/**
 * Created by José Roberto on 12/09/2014.
 */
public class SpongeForgeListener
{
    private final SpongeBukkitMod mod;

    public SpongeForgeListener(SpongeBukkitMod mod)
    {
        this.mod = Preconditions.checkNotNull(mod);
    }
}
