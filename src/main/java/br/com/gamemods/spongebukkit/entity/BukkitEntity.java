package br.com.gamemods.spongebukkit.entity;

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

import java.util.List;
import java.util.UUID;

public class BukkitEntity<E extends net.minecraft.entity.Entity> implements Entity
{
    protected E entity;

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
    public List<Entity> getNearbyEntities(double v, double v2, double v3)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getEntityId()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getFireTicks()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getMaxFireTicks()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setFireTicks(int i)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isDead()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isValid()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Server getServer()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Entity getPassenger()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean setPassenger(Entity entity)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEmpty()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean eject()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public float getFallDistance()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setFallDistance(float v)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setLastDamageCause(EntityDamageEvent event)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public EntityDamageEvent getLastDamageCause()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public UUID getUniqueId()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getTicksLived()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setTicksLived(int i)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void playEffect(EntityEffect entityEffect)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public EntityType getType()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isInsideVehicle()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean leaveVehicle()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Entity getVehicle()
    {
        throw new UnsupportedOperationException();
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
