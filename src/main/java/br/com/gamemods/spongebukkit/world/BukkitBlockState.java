package br.com.gamemods.spongebukkit.world;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.material.MaterialData;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nonnull;
import java.util.List;

public class BukkitBlockState implements BlockState
{
	private final BukkitBlock block;
	private MaterialData data;
	private Material type;

	public BukkitBlockState(@Nonnull BukkitBlock block)
	{
		this.block = block;
		this.type = block.getType();
	}

	@Override
	public Block getBlock()
	{
		return block;
	}

	@Override
	public MaterialData getData()
	{
		return data;
	}

	@Override
	public Material getType()
	{
		return type;
	}

	@Override
	public int getTypeId()
	{
		return type.getId();
	}

	@Override
	public byte getLightLevel()
	{
		return block.getLightLevel();
	}

	@Override
	public World getWorld()
	{
		return block.getWorld();
	}

	@Override
	public int getX()
	{
		return block.getX();
	}

	@Override
	public int getY()
	{
		return block.getY();
	}

	@Override
	public int getZ()
	{
		return block.getZ();
	}

	@Override
	public Location getLocation()
	{
		return block.getLocation();
	}

	@Override
	public Location getLocation(Location location)
	{
		return block.getLocation(location);
	}

	@Override
	public Chunk getChunk()
	{
		return block.getChunk();
	}

	@Override
	public void setData(MaterialData materialData)
	{
		this.data = materialData;
	}

	@Override
	public void setType(Material material)
	{
		this.type = material;
	}

	@Override
	public boolean setTypeId(int i)
	{
		Material type = Material.getMaterial(i);
		if(type == null)
			return false;

		setType(type);
		return true;
	}

	@Override
	public boolean update()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean update(boolean b)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean update(boolean b, boolean b2)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public byte getRawData()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void setRawData(byte b)
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
