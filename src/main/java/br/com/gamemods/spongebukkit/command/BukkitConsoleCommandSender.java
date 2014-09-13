package br.com.gamemods.spongebukkit.command;

import br.com.gamemods.spongebukkit.server.BukkitServer;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;

public class BukkitConsoleCommandSender extends BasicCommandSender implements ConsoleCommandSender
{
    public BukkitConsoleCommandSender(BukkitServer server)
    {
        super(server);
    }

    @Override
    public void sendMessage(String message)
    {
        sendRawMessage(message);
    }

    @Override
    public String getName()
    {
        return "CONSOLE";
    }

    @Override
    public void sendRawMessage(String message)
    {
        System.out.println(ChatColor.stripColor(message));
    }

    @Override
    public boolean isConversing()
    {
        return false;
    }

    @Override
    public void acceptConversationInput(String input)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean beginConversation(Conversation conversation)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void abandonConversation(Conversation conversation)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void abandonConversation(Conversation conversation, ConversationAbandonedEvent details)
    {
        throw new UnsupportedOperationException();
    }
}
