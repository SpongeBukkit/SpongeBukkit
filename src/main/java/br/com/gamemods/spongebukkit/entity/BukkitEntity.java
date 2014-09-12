package br.com.gamemods.spongebukkit.entity;

import br.com.gamemods.spongebukkit.server.BukkitServer;
import br.com.gamemods.spongebukkit.world.WorldMap;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BukkitEntity<E extends net.minecraft.entity.Entity> implements Entity
{
    protected BukkitServer server;
    protected E entity;
    private EntityDamageEvent lastDamage;

    @Override
    public Location getLocation()
    {
        return new Location(WorldMap.getBukkitWorld(entity.worldObj), entity.posX, entity.posY, entity.posZ, entity.rotationYaw, entity.rotationPitch);
    }

    @Override
    public Location getLocation(Location location)
    {
        if(location == null)
            return null;

        location.setWorld(WorldMap.getBukkitWorld(entity.worldObj));
        location.setX(entity.posX);
        location.setY(entity.posY);
        location.setZ(entity.posZ);
        location.setYaw(entity.rotationYaw);
        location.setPitch(entity.rotationPitch);
        return location;
    }

    @Override
    public void setVelocity(Vector vector)
    {
        entity.motionX = vector.getX();
        entity.motionY = vector.getY();
        entity.motionZ = vector.getZ();
    }

    @Override
    public Vector getVelocity()
    {
        return new Vector(entity.motionX, entity.motionY, entity.motionZ);
    }

    @Override
    public boolean isOnGround()
    {
        return entity.onGround;
    }

    @Override
    public World getWorld()
    {
        return WorldMap.getBukkitWorld(entity.worldObj);
    }

    @Override
    public boolean teleport(Location location)
    {
        entity.worldObj = WorldMap.getVanillaWorld(location.getWorld());
        entity.setPositionAndRotation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        return true;
    }

    @Override
    public boolean teleport(Location location, PlayerTeleportEvent.TeleportCause teleportCause)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean teleport(Entity entity)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean teleport(Entity entity, PlayerTeleportEvent.TeleportCause teleportCause)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Entity> getNearbyEntities(double x, double y, double z)
    {
        List<net.minecraft.entity.Entity> entities = entity.worldObj.getEntitiesWithinAABBExcludingEntity(entity, entity.boundingBox.expand(x, y, z));
        List<Entity> bukkitEntities = new ArrayList<>(entities.size());
        for(net.minecraft.entity.Entity entity: entities)
            bukkitEntities.add(EntityMap.getBukkitEntity(entity.dimension, entity.getUniqueID()));

        return bukkitEntities;
    }

    @Override
    public int getEntityId()
    {
        return entity.getEntityId();
    }

    @Override
    public int getFireTicks()
    {
        return entity.fire;
    }

    @Override
    public int getMaxFireTicks()
    {
        return entity.fireResistance;
    }

    @Override
    public void setFireTicks(int ticks)
    {
        entity.fire = ticks;
    }

    @Override
    public void remove()
    {
        entity.setDead();
    }

    @Override
    public boolean isDead()
    {
        return entity.isDead;
    }

    @Override
    public boolean isValid()
    {
        return !isDead();
    }

    @Override
    public Server getServer()
    {
        return server;
    }

    @Override
    public Entity getPassenger()
    {
        net.minecraft.entity.Entity passenger = entity.riddenByEntity;
        if(passenger == null) return null;
        return EntityMap.getBukkitEntity(passenger);
    }

    @Override
    public boolean setPassenger(Entity entity)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEmpty()
    {
        return entity.riddenByEntity == null;
    }

    @Override
    public boolean eject()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public float getFallDistance()
    {
        return entity.fallDistance;
    }

    @Override
    public void setFallDistance(float v)
    {
        entity.fallDistance = v;
    }

    @Override
    public void setLastDamageCause(EntityDamageEvent event)
    {
        lastDamage = event;
    }

    @Override
    public EntityDamageEvent getLastDamageCause()
    {
        return lastDamage;
    }

    @Override
    public UUID getUniqueId()
    {
        return entity.getUniqueID();
    }

    @Override
    public int getTicksLived()
    {
        return entity.ticksExisted;
    }

    @Override
    public void setTicksLived(int i)
    {
        entity.ticksExisted = i;
    }

    @Override
    public void playEffect(EntityEffect entityEffect)
    {
        entity.worldObj.setEntityState(entity, entityEffect.getData());
    }

    @Override
    public EntityType getType()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isInsideVehicle()
    {
        return entity.ridingEntity != null;
    }

    @Override
    public boolean leaveVehicle()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Entity getVehicle()
    {
        net.minecraft.entity.Entity entity = this.entity.ridingEntity;
        if(entity == null) return null;
        return EntityMap.getBukkitEntity(entity);
    }

    @Override
    public void setMetadata(String s, MetadataValue metadataValue)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<MetadataValue> getMetadata(String s)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasMetadata(String s)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeMetadata(String s, Plugin plugin)
    {
        throw new UnsupportedOperationException();
    }
}
