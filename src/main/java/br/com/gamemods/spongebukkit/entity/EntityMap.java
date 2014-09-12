package br.com.gamemods.spongebukkit.entity;

import org.bukkit.entity.Entity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Convert entity types between implementations
 */
public class EntityMap
{
    private static Map<Integer, Map<UUID, Entity>> bukkitEntities = new HashMap<>();


    public static Entity getBukkitEntity(int dimension, UUID entityId)
    {
        Map<UUID, Entity> map = bukkitEntities.get(dimension);
        if(map == null) return null;
        return map.get(entityId);
    }
}
