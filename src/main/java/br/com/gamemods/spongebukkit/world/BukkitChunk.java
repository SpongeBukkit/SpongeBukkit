package br.com.gamemods.spongebukkit.world;

import org.bukkit.Chunk;
import org.bukkit.ChunkSnapshot;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Entity;

/**
 * Created by José Roberto on 11/09/2014.
 */
public class BukkitChunk implements Chunk
{
	private net.minecraft.world.chunk.Chunk chunk;

	public net.minecraft.world.chunk.Chunk getVanillaChunk()
	{
		return chunk;
	}

	@Override
	public int getX()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public int getZ()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public World getWorld()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public Block getBlock(int i, int i2, int i3)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public ChunkSnapshot getChunkSnapshot()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public ChunkSnapshot getChunkSnapshot(boolean b, boolean b2, boolean b3)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public Entity[] getEntities()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public BlockState[] getTileEntities()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isLoaded()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean load(boolean b)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean load()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean unload(boolean b, boolean b2)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean unload(boolean b)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean unload()
	{
		throw new UnsupportedOperationException();
	}
}
