package br.com.gamemods.spongebukkit.mod;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

import java.util.Map;

public class SpongeBukkitCoremod implements IFMLLoadingPlugin
{
    @Override
    public String[] getASMTransformerClass()
    {
        return new String[0];
    }

    @Override
    public String getModContainerClass()
    {
        return "br.com.gamemods.spongebukkit.mod.SpongeBukkitMod";
    }

    @Override
    public String getSetupClass()
    {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data)
    {

    }

    @Override
    public String getAccessTransformerClass()
    {
        return null;
    }
}
