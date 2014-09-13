package br.com.gamemods.spongebukkit.command;

import br.com.gamemods.spongebukkit.server.BukkitServer;
import com.google.common.base.Preconditions;
import net.minecraft.network.rcon.RConConsoleSource;
import net.minecraft.util.ChatComponentText;
import org.bukkit.command.RemoteConsoleCommandSender;

public class BukkitRemoteCommandSender extends BasicCommandSender implements RemoteConsoleCommandSender
{
    private final RConConsoleSource sender;
    public BukkitRemoteCommandSender(BukkitServer server, RConConsoleSource sender)
    {
        super(server);
        this.sender = Preconditions.checkNotNull(sender);
    }

    @Override
    public void sendMessage(String message)
    {
        sender.addChatMessage(new ChatComponentText(message));
    }

    @Override
    public String getName()
    {
        return sender.getCommandSenderName();
    }
}
