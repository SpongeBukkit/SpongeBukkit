package br.com.gamemods.spongebukkit.scheduler;

import org.bukkit.scheduler.BukkitTask;

import java.util.concurrent.Future;

public interface FutureBukkitTask<V> extends BukkitTask, Future<V>
{
}
