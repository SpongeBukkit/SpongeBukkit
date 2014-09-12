package br.com.gamemods.spongebukkit.server;

import com.google.common.base.Preconditions;
import org.bukkit.help.HelpMap;
import org.bukkit.help.HelpTopic;
import org.bukkit.help.HelpTopicFactory;

import java.util.Collection;
import java.util.List;

public class BukkitHelpMap implements HelpMap
{
    private final BukkitServer server;

    public BukkitHelpMap(BukkitServer server)
    {
        this.server = Preconditions.checkNotNull(server);
    }

    @Override
    public HelpTopic getHelpTopic(String topicName)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<HelpTopic> getHelpTopics()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addTopic(HelpTopic topic)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void registerHelpTopicFactory(Class<?> commandClass, HelpTopicFactory<?> factory)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> getIgnoredPlugins()
    {
        throw new UnsupportedOperationException();
    }
}
