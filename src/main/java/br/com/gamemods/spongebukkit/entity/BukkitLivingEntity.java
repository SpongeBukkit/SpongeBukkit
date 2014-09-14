package br.com.gamemods.spongebukkit.entity;

import br.com.gamemods.spongebukkit.server.BukkitServer;
import net.minecraft.entity.EntityLivingBase;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class BukkitLivingEntity<E extends EntityLivingBase> extends BukkitEntity<E> implements LivingEntity
{
    public BukkitLivingEntity(BukkitServer server, E entity)
    {
        super(server, entity);
    }

    @Override
    public double getEyeHeight()
    {
        return entity.getEyeHeight();
    }

    @Override
    public double getEyeHeight(boolean ignoreSneaking)
    {
        return entity.getEyeHeight();
    }

    @Override
    public Location getEyeLocation()
    {
        Location location = getLocation();
        location.setY(location.getY() + getEyeHeight());
        return location;
    }

    @Override
    public List<Block> getLineOfSight(HashSet<Byte> bytes, int i)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Block getTargetBlock(HashSet<Byte> bytes, int i)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Block> getLastTwoTargetBlocks(HashSet<Byte> bytes, int i)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Egg throwEgg()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Snowball throwSnowball()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Arrow shootArrow()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getRemainingAir()
    {
        return entity.getAir();
    }

    @Override
    public void setRemainingAir(int i)
    {
        entity.setAir(i);
    }

    @Override
    public int getMaximumAir()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setMaximumAir(int i)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getMaximumNoDamageTicks()
    {
        return entity.maxHurtResistantTime;
    }

    @Override
    public void setMaximumNoDamageTicks(int i)
    {
        entity.maxHurtResistantTime = i;
    }

    @Override
    public double getLastDamage()
    {
        return entity.lastDamage;
    }

    @Override
    public int _INVALID_getLastDamage()
    {
        return (int) getLastDamage();
    }

    @Override
    public void setLastDamage(double v)
    {
        entity.lastDamage = (float) v;
    }

    @Override
    public void _INVALID_setLastDamage(int i)
    {
        setLastDamage((double)i);
    }

    @Override
    public int getNoDamageTicks()
    {
        return entity.hurtResistantTime;
    }

    @Override
    public void setNoDamageTicks(int i)
    {
        entity.hurtResistantTime = i;
    }

    @Override
    public Player getKiller()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addPotionEffect(PotionEffect potionEffect)
    {
        return false;
    }

    @Override
    public boolean addPotionEffect(PotionEffect potionEffect, boolean b)
    {
        return false;
    }

    @Override
    public boolean addPotionEffects(Collection<PotionEffect> potionEffects)
    {
        return false;
    }

    @Override
    public boolean hasPotionEffect(PotionEffectType potionEffectType)
    {
        return false;
    }

    @Override
    public void removePotionEffect(PotionEffectType potionEffectType)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<PotionEffect> getActivePotionEffects()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasLineOfSight(Entity entity)
    {
        return false;
    }

    @Override
    public boolean getRemoveWhenFarAway()
    {
        return false;
    }

    @Override
    public void setRemoveWhenFarAway(boolean b)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public EntityEquipment getEquipment()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setCanPickupItems(boolean b)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean getCanPickupItems()
    {
        return false;
    }

    @Override
    public void setCustomName(String s)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getCustomName()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setCustomNameVisible(boolean b)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isCustomNameVisible()
    {
        return false;
    }

    @Override
    public boolean isLeashed()
    {
        return false;
    }

    @Override
    public Entity getLeashHolder() throws IllegalStateException
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean setLeashHolder(Entity entity)
    {
        return false;
    }

    @Override
    public void damage(double v)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void _INVALID_damage(int i)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void damage(double v, Entity entity)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void _INVALID_damage(int i, Entity entity)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public double getHealth()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int _INVALID_getHealth()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setHealth(double v)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void _INVALID_setHealth(int i)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public double getMaxHealth()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int _INVALID_getMaxHealth()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setMaxHealth(double v)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void _INVALID_setMaxHealth(int i)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void resetMaxHealth()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends Projectile> T launchProjectile(Class<? extends T> aClass)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends Projectile> T launchProjectile(Class<? extends T> aClass, Vector vector)
    {
        throw new UnsupportedOperationException();
    }
}
