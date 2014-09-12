package br.com.gamemods.spongebukkit.scheduler;

import com.google.common.base.Preconditions;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scheduler.BukkitWorker;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;

public class SpongeBukkitTask<R> implements FutureBukkitTask<R>
{
    private final SpongeBukkitScheduler scheduler;
    private final int taskId;
    private final Plugin owner;
    private final boolean sync;
    private final Callable<R> callable;
    private final long timer;
    private R result;
    private ExecutionException exception;
    private byte state = 0;
    private long nextRun;

    public SpongeBukkitTask(SpongeBukkitScheduler scheduler, int taskId, Plugin plugin, boolean sync, Callable<R> callable, long delay, long timer)
    {
        this.scheduler = Preconditions.checkNotNull(scheduler);
        this.taskId = taskId;
        this.owner = Preconditions.checkNotNull(plugin);
        this.sync = sync;
        this.callable = Preconditions.checkNotNull(callable);
        this.nextRun = delay <= 0? 1 : delay;
        this.timer = timer;
    }

    public void tick() throws ExecutionException
    {
        if(state == 1)
            return;

        if(--nextRun <= 0)
        {
            if(sync)
            {
                try
                {
                    execute();
                }
                finally
                {
                    if(!isCancelled() && timer >= 0)
                        nextRun = timer;
                    else
                        scheduler.cancelTask(this);
                }
            }
            else
            {
                try
                {
                    state = 1;
                    new SpongeBukkitWorker().start();
                }
                catch (Throwable e)
                {
                    try
                    {
                        state = 3;
                        exception = new ExecutionException(e);
                        throw exception;
                    }
                    finally
                    {
                        if(!isCancelled() && timer >= 0)
                            nextRun = timer;
                        else
                            scheduler.cancelTask(this);
                    }
                }
            }
        }
    }

    public R execute() throws ExecutionException, IllegalStateException
    {
        if(isCancelled() || (timer < 0 && isDone()))
            throw new IllegalStateException();

        state = 1;
        result = null;
        try
        {
            return result = callable.call();
        }
        catch (Throwable e)
        {
            owner.getLogger().log(Level.WARNING, owner.getName()+"'s task #"+taskId+" threw an exception!",e);
            if(e instanceof ExecutionException)
                exception = (ExecutionException) e;
            else
                exception = new ExecutionException(e);

            throw exception;
        }
        finally
        {
            state = 2;

            synchronized (this)
            {
                notifyAll();
            }
        }
    }

    @Override
    public int getTaskId()
    {
        return taskId;
    }

    @Override
    public Plugin getOwner()
    {
        return owner;
    }

    public boolean isRunning()
    {
        return state == 1;
    }

    @Override
    public boolean isSync()
    {
        return sync;
    }

    @Override
    public void cancel()
    {
        cancel(false);
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning)
    {
        if(state != 0)
            return false;

        scheduler.cancelTask(this);

        state = 3;
        return true;
    }

    @Override
    public boolean isCancelled()
    {
        return state == 3;
    }

    @Override
    public boolean isDone()
    {
        return state != 0;
    }

    @Override
    public R get() throws InterruptedException, ExecutionException
    {
        while (true)
        {
            if (state == 2)
                return result;

            if (exception != null)
                throw exception;

            synchronized (this)
            {
                if (result != null)
                    return result;

                wait();
            }
        }
    }

    @Override
    public R get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException
    {
        if(state == 2)
            return result;

        if(exception != null)
            throw exception;

        synchronized (this)
        {
            if(result != null)
                return result;

            if(timeout == -1)
                throw new TimeoutException();

            wait(unit.toMillis(timeout));
            return get(-1, null);
        }
    }

    public class SpongeBukkitWorker implements BukkitWorker
    {
        private Thread thread;

        private void start()
        {
            thread = new Thread(new AsyncWorker(), "SpongeBukkit.AsyncTask."+taskId);
            thread.start();
        }

        private class AsyncWorker implements Runnable
        {
            @Override
            public void run()
            {
                try
                {
                    scheduler.workers.add(SpongeBukkitWorker.this);
                    execute();
                }
                catch (Throwable e)
                {
                    //Do noting
                }
                finally
                {
                    scheduler.workers.remove(SpongeBukkitWorker.this);
                    if(!isCancelled() && timer >= 0)
                        nextRun = timer;
                    else
                        scheduler.cancelTask(SpongeBukkitTask.this);
                }
            }
        }

        @Override
        public int getTaskId()
        {
            return taskId;
        }

        public BukkitTask getTask()
        {
            return SpongeBukkitTask.this;
        }

        public SpongeBukkitTask<R> getSpongeBukkitTask()
        {
            return SpongeBukkitTask.this;
        }

        @Override
        public Plugin getOwner()
        {
            return owner;
        }

        @Override
        public Thread getThread()
        {
            return thread;
        }
    }
}
