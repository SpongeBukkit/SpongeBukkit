package br.com.gamemods.spongebukkit.command;

import br.com.gamemods.spongebukkit.server.BukkitServer;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import org.bukkit.command.CommandSender;

public class UnknownCommandSender extends BasicCommandSender implements CommandSender
{
    private final ICommandSender sender;
    public UnknownCommandSender(BukkitServer server, ICommandSender sender)
    {
        super(server);
        this.sender = sender;
    }

    @Override
    public void sendMessage(String message)
    {
        if(sender != null)
            sender.addChatMessage(new ChatComponentText(message));
    }

    @Override
    public String getName()
    {
        return sender == null? "Unknown" : sender.getCommandSenderName();
    }
}
