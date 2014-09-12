package br.com.gamemods.spongebukkit.world;

import com.google.common.base.Preconditions;
import net.minecraft.world.EnumSkyBlock;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

import java.util.Collection;
import java.util.List;

public class BukkitBlock implements Block
{
    private final BukkitChunk chunk;
    private final int x, y, z;

    public BukkitBlock(BukkitChunk chunk, int x, int y, int z)
    {
        this.chunk = Preconditions.checkNotNull(chunk);
        this.x = x; this.y = y; this.z = z;
    }

    @Override
    public byte getData()
    {
        return (byte) chunk.getVanillaChunk().getBlockMetadata(x, y, z);
    }

    @Override
    public Block getRelative(int x, int y, int z)
    {
        return getWorld().getBlockAt(this.x+x, this.y+y, this.z+z);
    }

    @Override
    public Block getRelative(BlockFace blockFace)
    {
        return getRelative(blockFace.getModX(), blockFace.getModY(), blockFace.getModZ());
    }

    @Override
    public Block getRelative(BlockFace blockFace, int i)
    {
        return getRelative(blockFace.getModX()*i, blockFace.getModY()*i, blockFace.getModZ()*i);
    }

    @Override
    public Material getType()
    {
        return Material.getMaterial(getTypeId());
    }

    @Override
    public int getTypeId()
    {
        return net.minecraft.block.Block.getIdFromBlock(chunk.getVanillaChunk().getBlock(x,y,z));
    }

    @Override
    public byte getLightLevel()
    {
        return (byte)chunk.getVanillaChunk().worldObj.getBlockLightValue(x,y,z);
    }

    @Override
    public byte getLightFromSky()
    {
        return (byte)chunk.getVanillaChunk().worldObj.getSavedLightValue(EnumSkyBlock.Sky, x, y, z);
    }

    @Override
    public byte getLightFromBlocks()
    {
        return (byte)chunk.getVanillaChunk().worldObj.getSavedLightValue(EnumSkyBlock.Block, x, y, z);
    }

    @Override
    public World getWorld()
    {
        return chunk.getWorld();
    }

    @Override
    public int getX()
    {
        return x;
    }

    @Override
    public int getY()
    {
        return y;
    }

    @Override
    public int getZ()
    {
        return z;
    }

    @Override
    public Location getLocation()
    {
        return new Location(chunk.getWorld(), x, y, z);
    }

    @Override
    public Location getLocation(Location location)
    {
        location.setWorld(chunk.getWorld());
        location.setX(x);
        location.setY(y);
        location.setZ(z);
        return location;
    }

    @Override
    public Chunk getChunk()
    {
        return chunk;
    }

    @Override
    public void setData(byte b)
    {
        chunk.getVanillaChunk().worldObj.setBlockMetadataWithNotify(x, y, z, b, 3);
    }

    @Override
    public void setData(byte b, boolean applyPhysics)
    {
        if(applyPhysics)
            setData(b);
        else
            chunk.getVanillaChunk().worldObj.setBlockMetadataWithNotify(x, y, z, b, 2);
    }

    @Override
    public void setType(Material material)
    {
        setTypeId(material.getId());
    }

    @Override
    public boolean setTypeId(int i)
    {
        return false;
    }

    @Override
    public boolean setTypeId(int i, boolean applyPhysics)
    {
        return setTypeIdAndData(i, getData(), applyPhysics);
    }

    @Override
    public boolean setTypeIdAndData(int id, byte data, boolean applyPhysics)
    {
        net.minecraft.block.Block block = chunk.getVanillaChunk().getBlock(x,y,z);
        net.minecraft.world.chunk.Chunk chunk = this.chunk.getVanillaChunk();
        if(applyPhysics)
            return chunk.worldObj.setBlock(x,y,z,block,data, 3);
        else if(chunk.worldObj.setBlock(x,y,z,block,data,2))
        {
            chunk.worldObj.notifyBlockChange(x,y,z,block);
            return true;
        }
        return false;
    }

    @Override
    public BlockFace getFace(Block block)
    {
        int thisX=getX(), otherX=block.getX(), thisY=getY(), otherY=block.getY(), thisZ=getZ(), otherZ=block.getZ();
        for(BlockFace face: BlockFace.values())
            if(thisX+face.getModX()==otherX && thisY+face.getModY()==otherY && thisZ+face.getModZ()==otherZ)
                return face;

        return null;
    }

    @Override
    public BlockState getState()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Biome getBiome()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setBiome(Biome biome)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isBlockPowered()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isBlockIndirectlyPowered()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isBlockFacePowered(BlockFace blockFace)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isBlockFaceIndirectlyPowered(BlockFace blockFace)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getBlockPower(BlockFace blockFace)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getBlockPower()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEmpty()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isLiquid()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public double getTemperature()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public double getHumidity()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public PistonMoveReaction getPistonMoveReaction()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean breakNaturally()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean breakNaturally(ItemStack stack)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<ItemStack> getDrops()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<ItemStack> getDrops(ItemStack stack)
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
