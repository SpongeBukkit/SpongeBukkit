package br.com.gamemods.spongebukkit.command;

import br.com.gamemods.spongebukkit.entity.EntityMap;
import br.com.gamemods.spongebukkit.server.BukkitServer;
import br.com.gamemods.spongebukkit.world.WorldMap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.entity.Entity;
import net.minecraft.network.rcon.RConConsoleSource;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChunkCoordinates;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.SimpleCommandMap;

import java.util.*;

public class BukkitCommandMap extends SimpleCommandMap
{
    private final Map<ICommandSender, UnknownCommandSender> unknownCommandSenderMap = new HashMap<>();
    private final Map<Command, List<BukkitPluginCommand>> commandListMap = new HashMap<>();

    private final BukkitServer server;
    private final ServerCommandManager manager;

    private List<Object[]> delayedRegisters;

    public BukkitCommandMap(BukkitServer server)
    {
        super(server);
        this.server = server;
        manager = (ServerCommandManager) server.getVanillaServer().getCommandManager();

        for(Object[] args: delayedRegisters)
        {
            registerInForge((Boolean)args[0], (String)args[1], (String)args[2], (Command)args[3]);
        }
        delayedRegisters = null;
    }

    private UnknownCommandSender getUnknownSender(ICommandSender sender)
    {
        UnknownCommandSender obj = unknownCommandSenderMap.get(sender);
        if(obj == null) unknownCommandSenderMap.put(sender, obj = new UnknownCommandSender(server, sender));
        return obj;
    }

    public CommandSender getCommandSender(ICommandSender sender)
    {
        if(sender instanceof Entity)
        {
            org.bukkit.entity.Entity entity = EntityMap.getBukkitEntity((Entity) sender);
            if(entity instanceof CommandSender)
               return  (CommandSender) entity;
            else
                return getUnknownSender(sender);
        }
        else if(sender instanceof CommandBlockLogic)
        {
            CommandBlockLogic commandBlockLogic = (CommandBlockLogic) sender;

            net.minecraft.world.World vanillaWorld = commandBlockLogic.getEntityWorld();
            World world = WorldMap.getBukkitWorld(vanillaWorld);
            ChunkCoordinates coordinates = commandBlockLogic.getPlayerCoordinates();
            Location location = new Location(world, coordinates.posX, coordinates.posY, coordinates.posZ);

            if(commandBlockLogic.func_145751_f() == 1)
            {
                // CommandMinecart
                ByteBuf buff = Unpooled.buffer(4,8);
                commandBlockLogic.func_145757_a(buff);
                int id = buff.readInt();

                Entity vanillaEntity = vanillaWorld.getEntityByID(id);
                org.bukkit.entity.Entity entity = EntityMap.getBukkitEntity(vanillaEntity);
                if(entity instanceof CommandSender)
                    return (CommandSender) entity;
                else
                    return getUnknownSender(sender);
            }
            else
                return new BukkitBlockCommandSender(server, location.getBlock(), commandBlockLogic);
        }
        else if(sender instanceof MinecraftServer)
        {
            return Bukkit.getConsoleSender();
        }
        else if(sender instanceof RConConsoleSource)
        {
            return new BukkitRemoteCommandSender(server, (RConConsoleSource) sender);
        }
        else return getUnknownSender(sender);
    }

    @Override
    public synchronized void clearCommands()
    {
        for(List<BukkitPluginCommand> commands: this.commandListMap.values())
            for(BukkitPluginCommand cmd: commands)
                unregisterInForge(cmd);

        super.clearCommands();
    }

    public void unregisterInForge(ICommand command)
    {
        Map<String, ICommand> commandMap = this.manager.getCommands();
        Set<ICommand> commandSet = this.manager.commandSet;
        commandSet.remove(command);
        ICommand otherCommand = commandMap.get(command.getCommandName());
        if(otherCommand == command)
            commandMap.remove(command.getCommandName());
        else
            for(Map.Entry<String, ICommand> entry: commandMap.entrySet())
            {
                if(entry.getValue() == command)
                {
                    commandMap.remove(entry.getKey());
                    break;
                }
            }
    }

    @Override
    public boolean register(String label, String fallbackPrefix, Command command)
    {
        boolean registered = super.register(label, fallbackPrefix, command);

        // This is null when the superclass is constructing although some IDEs says that this condition is always false
        if(this.commandListMap == null)
        {
            if(delayedRegisters == null)
                delayedRegisters = new ArrayList<>();
            delayedRegisters.add(new Object[]{registered, label, fallbackPrefix, command});
            return registered;
        }

        registerInForge(registered, label, fallbackPrefix, command);

        return registered;
    }

    private void registerInForge(boolean registered, String label, String fallbackPrefix, Command command)
    {
        label = label.trim();
        fallbackPrefix = fallbackPrefix.trim();
        List<String> aliases = command.getAliases();

        List<BukkitPluginCommand> cmdList = this.commandListMap.get(command);
        if(cmdList == null)
            this.commandListMap.put(command, cmdList = new ArrayList<>((registered?2:1)+aliases.size()*2));

        BukkitPluginCommand cmd;
        if(registered)
        {
            cmd = new BukkitPluginCommand(this, command, label);
            manager.registerCommand(cmd);
            cmdList.add(cmd);
        }
        cmd = new BukkitPluginCommand(this, command, fallbackPrefix+":"+label);
        manager.registerCommand(cmd);
        cmdList.add(cmd);

        //TODO: Make correct use of Forge's CommandBase
        for(String alias: aliases)
        {
            cmd = new BukkitPluginCommand(this, command, alias);
            manager.registerCommand(cmd);
            cmdList.add(cmd);

            cmd = new BukkitPluginCommand(this, command, fallbackPrefix+":"+alias);
            manager.registerCommand(cmd);
            cmdList.add(cmd);
        }
    }
}
