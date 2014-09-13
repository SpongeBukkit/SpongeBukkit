package br.com.gamemods.spongebukkit.command;

import com.google.common.base.Preconditions;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class BukkitPluginCommand extends CommandBase
{
    private final BukkitCommandMap commandMap;
    private final Command command;
    private final String label;
    public BukkitPluginCommand(BukkitCommandMap commandMap, Command command, String label)
    {
        this.commandMap = Preconditions.checkNotNull(commandMap);
        this.command = Preconditions.checkNotNull(command);
        this.label = Preconditions.checkNotNull(label);
    }

    @Override
    public String getCommandName()
    {
        return label;
    }

    @Override
    public String getCommandUsage(ICommandSender sender)
    {
        return command.getUsage();
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args)
    {
        CommandSender bukkitSender = commandMap.getCommandSender(sender);
        command.execute(bukkitSender, label, args);
    }
}
