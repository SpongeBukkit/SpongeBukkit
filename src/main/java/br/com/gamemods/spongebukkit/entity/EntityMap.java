package br.com.gamemods.spongebukkit.entity;

import br.com.gamemods.spongebukkit.server.BukkitServer;
import br.com.gamemods.spongebukkit.server.VolatileServer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;

/**
 * Convert entity types between implementations
 */
public class EntityMap
{
    private static final Map<Integer, Map<UUID, Entity>> bukkitEntities = new HashMap<>();
    private static boolean cacheFieldFailed;
    private static Field cacheField;

    private static Field getCacheField()
    {
        if(!cacheFieldFailed && cacheField == null)
        {
            try
            {
                cacheField = net.minecraft.entity.Entity.class.getDeclaredField("_sb_bukkit_entity");
                if(cacheField == null)
                    cacheFieldFailed = true;
            }
            catch (ReflectiveOperationException e)
            {
                cacheFieldFailed = true;
                cacheField = null;
                Bukkit.getLogger().log(Level.WARNING, "Failed to load entities from cache field (Core Mod Failed?) - Entity caching as been disabled", e);
            }
        }
        return cacheField;
    }

    private static Entity getFromCache(net.minecraft.entity.Entity entity)
    {
        getCacheField();

        if(cacheField == null)
            return null;

        try
        {
            return (Entity) cacheField.get(entity);
        }
        catch (ReflectiveOperationException e)
        {
            Bukkit.getLogger().log(Level.WARNING, "Failed to access the cache field", e);
            return null;
        }
    }

    private static void setCache(net.minecraft.entity.Entity entity, Entity bukkitEntity)
    {
        getCacheField();

        if(cacheField == null)
            return;

        try
        {
            cacheField.set(entity, bukkitEntity);
        }
        catch (ReflectiveOperationException e)
        {
            Bukkit.getLogger().log(Level.WARNING, "Failed to update the cache field", e);
        }
    }

    /**
     * @deprecated Only returns registered entities
     */
    @Deprecated
    public static Entity getBukkitEntity(int dimension, UUID entityId)
    {
        Map<UUID, Entity> map = bukkitEntities.get(dimension);
        if(map == null) return null;
        return map.get(entityId);
    }

    private static Entity createBukkitEntity(net.minecraft.entity.Entity entity)
    {
        BukkitServer server = ((VolatileServer)Bukkit.getServer()).getCurrentServer();

        Entity bukkitEntity;
        if(entity instanceof EntityPlayer)
            bukkitEntity = new BukkitPlayer<>(server, (EntityPlayer) entity);
        else if(entity instanceof EntityLivingBase)
            bukkitEntity = new BukkitLivingEntity<>(server, (EntityLivingBase) entity);
        else
            bukkitEntity = new BukkitEntity<>(server, entity);

        setCache(entity, bukkitEntity);
        Map<UUID, Entity> map;
        synchronized (bukkitEntities)
        {
            map = bukkitEntities.get(entity.dimension);
            if (map == null) bukkitEntities.put(entity.dimension, map = new HashMap<>());
        }
        map.put(entity.getUniqueID(), bukkitEntity);

        return bukkitEntity;
    }

    public static Entity getBukkitEntity(net.minecraft.entity.Entity entity)
    {
        Entity cache = getFromCache(entity);
        if(cache != null)
            return cache;

        cache = getBukkitEntity(entity.dimension, entity.getUniqueID());
        if(cache != null)
            return cache;

        return createBukkitEntity(entity);
    }

    public static void clear()
    {
        bukkitEntities.clear();
    }
}
