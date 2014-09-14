package br.com.gamemods.spongebukkit.mod;

import br.com.gamemods.spongebukkit.server.BukkitServer;
import com.google.common.base.Preconditions;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.command.ICommandManager;
import net.minecraftforge.event.CommandEvent;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles forge events
 */
public class SpongeForgeListener
{
    private final SpongeBukkitMod mod;

    public SpongeForgeListener(SpongeBukkitMod mod)
    {
        this.mod = Preconditions.checkNotNull(mod);
    }

    @SubscribeEvent(receiveCanceled = true, priority = EventPriority.LOWEST)
    public void on(CommandEvent event)
    {
        BukkitServer server = mod.getServer();
        if(server == null) return;

        CommandSender sender = server.getCommandMap().getCommandSender(event.sender);


        StringBuilder sb = new StringBuilder();
        sb.append(event.command.getCommandName());
        sb.append(" ");
        for(String arg: event.parameters)
        {
            sb.append(arg);
            sb.append(" ");
        }
        String msg = sb.substring(0,sb.length()-1);
        String returnedMsg;

        mod.getLogger().info("###### CommandEvent "+sender+" "+msg+" ######");

        if(sender instanceof Player)
        {
            PlayerCommandPreprocessEvent bukkitEvent = new PlayerCommandPreprocessEvent((Player) sender, msg);
            bukkitEvent.setCancelled(event.isCanceled());
            server.getPluginManager().callEvent(bukkitEvent);
            returnedMsg = bukkitEvent.getMessage();
        }
        else
        {
            ServerCommandEvent bukkitEvent = new ServerCommandEvent(sender, msg);
            server.getPluginManager().callEvent(bukkitEvent);
            returnedMsg = msg;
        }

        if(!msg.equals(returnedMsg))
        {
            event.setCanceled(true);
            if(returnedMsg == null || (returnedMsg = returnedMsg.trim()).isEmpty())
                return;

            String[] parts = msg.split(" ");
            List<String> list = new ArrayList<>(parts.length);
            for(String part: parts)
            {
                part = part.trim();
                if(part.isEmpty()) continue;
                list.add(part);
            }

            mod.getLogger().info("###### CommandEvent "+sender+" @MODIFIED@ "+list+" ######");

            if(list.isEmpty())
                return;

            String cmdName = list.get(0);
            ICommandManager commandManager = server.getVanillaServer().commandManager;
            List<String> commands = commandManager.getPossibleCommands(event.sender, cmdName);
            String matched = null;
            for(String cmd: commands)
            {
                if(cmd.equalsIgnoreCase(cmdName))
                {
                    matched = cmd;
                    break;
                }
            }

            if(matched == null)
            {
                server.getLogger().severe("A plugin modified " + sender.getName() + "'s command to a command that isn't registered!");
            }
            else
            {
                commandManager.executeCommand(event.sender, returnedMsg);
            }
        }
    }

}
