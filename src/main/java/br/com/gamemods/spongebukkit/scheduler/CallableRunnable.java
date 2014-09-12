package br.com.gamemods.spongebukkit.scheduler;

import java.util.concurrent.Callable;

public class CallableRunnable implements Callable<Void>
{
    private final Runnable runnable;

    public CallableRunnable(Runnable runnable)
    {
        this.runnable = runnable;
    }

    @Override
    public Void call() throws Exception
    {
        runnable.run();
        return null;
    }
}
