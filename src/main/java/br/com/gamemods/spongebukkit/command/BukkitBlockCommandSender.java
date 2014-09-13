package br.com.gamemods.spongebukkit.command;

import br.com.gamemods.spongebukkit.server.BukkitServer;
import com.google.common.base.Preconditions;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import org.bukkit.block.Block;
import org.bukkit.command.BlockCommandSender;

public class BukkitBlockCommandSender extends BasicCommandSender implements BlockCommandSender
{
    private final ICommandSender sender;
    private final Block block;

    public BukkitBlockCommandSender(BukkitServer server, Block block, ICommandSender sender)
    {
        super(server);
        this.block = Preconditions.checkNotNull(block);
        this.sender = Preconditions.checkNotNull(sender);
    }

    @Override
    public Block getBlock()
    {
        return block;
    }

    @Override
    public void sendMessage(String message)
    {
        // Sending a message to a block?
        sender.addChatMessage(new ChatComponentText(message));
    }

    @Override
    public String getName()
    {
        return block.getType().name();
    }
}
