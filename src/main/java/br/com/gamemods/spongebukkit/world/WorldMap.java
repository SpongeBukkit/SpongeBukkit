package br.com.gamemods.spongebukkit.world;

import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public class WorldMap
{
    private static Map<World, org.bukkit.World> worldMap = new HashMap<>(3);

    public static org.bukkit.World getBukkitWorld(World vanillaWorld)
    {
        return worldMap.get(vanillaWorld);
    }

    public static World getVanillaWorld(org.bukkit.World world)
    {
        throw new UnsupportedOperationException();
    }
}
