package br.com.gamemods.spongebukkit.mod;

import cpw.mods.fml.common.asm.transformers.AccessTransformer;

import java.io.IOException;

/**
 * Created by José Roberto on 14/09/2014.
 */
public class SpongeBukkitAccessTransformer extends AccessTransformer
{
    public SpongeBukkitAccessTransformer() throws IOException
    {
        super("SpongeBukkit_at.cfg");
    }
}
