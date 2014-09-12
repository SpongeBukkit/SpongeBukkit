package br.com.gamemods.spongebukkit.entity;

import org.bukkit.*;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.map.MapView;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Scoreboard;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.Set;

public class BukkitPlayer extends BukkitHumanEntity implements Player
{
	@Override
	public String getDisplayName()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void setDisplayName(String s)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public String getPlayerListName()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void setPlayerListName(String s)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void setCompassTarget(Location location)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public Location getCompassTarget()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public InetSocketAddress getAddress()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isConversing()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void acceptConversationInput(String s)
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
	public void abandonConversation(Conversation conversation, ConversationAbandonedEvent conversationAbandonedEvent)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void sendRawMessage(String s)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void kickPlayer(String s)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void chat(String s)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean performCommand(String s)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isSneaking()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void setSneaking(boolean b)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isSprinting()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void setSprinting(boolean b)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void saveData()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void loadData()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void setSleepingIgnored(boolean b)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isSleepingIgnored()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void playNote(Location location, byte b, byte b2)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void playNote(Location location, Instrument instrument, Note note)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void playSound(Location location, Sound sound, float v, float v2)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void playSound(Location location, String s, float v, float v2)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void playEffect(Location location, Effect effect, int i)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> void playEffect(Location location, Effect effect, T t)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void sendBlockChange(Location location, Material material, byte b)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean sendChunkChange(Location location, int i, int i2, int i3, byte[] bytes)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void sendBlockChange(Location location, int i, byte b)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void sendSignChange(Location location, String[] strings) throws IllegalArgumentException
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void sendMap(MapView mapView)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateInventory()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void awardAchievement(Achievement achievement)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void removeAchievement(Achievement achievement)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean hasAchievement(Achievement achievement)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void incrementStatistic(Statistic statistic) throws IllegalArgumentException
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void decrementStatistic(Statistic statistic) throws IllegalArgumentException
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void incrementStatistic(Statistic statistic, int i) throws IllegalArgumentException
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void decrementStatistic(Statistic statistic, int i) throws IllegalArgumentException
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void setStatistic(Statistic statistic, int i) throws IllegalArgumentException
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public int getStatistic(Statistic statistic) throws IllegalArgumentException
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void incrementStatistic(Statistic statistic, Material material) throws IllegalArgumentException
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void decrementStatistic(Statistic statistic, Material material) throws IllegalArgumentException
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public int getStatistic(Statistic statistic, Material material) throws IllegalArgumentException
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void incrementStatistic(Statistic statistic, Material material, int i) throws IllegalArgumentException
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void decrementStatistic(Statistic statistic, Material material, int i) throws IllegalArgumentException
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void setStatistic(Statistic statistic, Material material, int i) throws IllegalArgumentException
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void incrementStatistic(Statistic statistic, EntityType entityType) throws IllegalArgumentException
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void decrementStatistic(Statistic statistic, EntityType entityType) throws IllegalArgumentException
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public int getStatistic(Statistic statistic, EntityType entityType) throws IllegalArgumentException
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void incrementStatistic(Statistic statistic, EntityType entityType, int i) throws IllegalArgumentException
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void decrementStatistic(Statistic statistic, EntityType entityType, int i)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void setStatistic(Statistic statistic, EntityType entityType, int i)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void setPlayerTime(long l, boolean b)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public long getPlayerTime()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public long getPlayerTimeOffset()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isPlayerTimeRelative()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void resetPlayerTime()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void setPlayerWeather(WeatherType weatherType)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public WeatherType getPlayerWeather()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void resetPlayerWeather()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void giveExp(int i)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void giveExpLevels(int i)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public float getExp()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void setExp(float v)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public int getLevel()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void setLevel(int i)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public int getTotalExperience()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void setTotalExperience(int i)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public float getExhaustion()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void setExhaustion(float v)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public float getSaturation()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void setSaturation(float v)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public int getFoodLevel()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void setFoodLevel(int i)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isOnline()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isBanned()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void setBanned(boolean b)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isWhitelisted()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void setWhitelisted(boolean b)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public Player getPlayer()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public long getFirstPlayed()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public long getLastPlayed()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean hasPlayedBefore()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public Location getBedSpawnLocation()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void setBedSpawnLocation(Location location)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void setBedSpawnLocation(Location location, boolean b)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean getAllowFlight()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void setAllowFlight(boolean b)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void hidePlayer(Player player)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void showPlayer(Player player)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean canSee(Player player)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isFlying()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void setFlying(boolean b)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void setFlySpeed(float v) throws IllegalArgumentException
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void setWalkSpeed(float v) throws IllegalArgumentException
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public float getFlySpeed()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public float getWalkSpeed()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void setTexturePack(String s)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void setResourcePack(String s)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public Scoreboard getScoreboard()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void setScoreboard(Scoreboard scoreboard) throws IllegalArgumentException, IllegalStateException
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isHealthScaled()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void setHealthScaled(boolean b)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void setHealthScale(double v) throws IllegalArgumentException
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public double getHealthScale()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void sendMessage(String s)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void sendMessage(String[] strings)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public Map<String, Object> serialize()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void sendPluginMessage(Plugin plugin, String s, byte[] bytes)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<String> getListeningPluginChannels()
	{
		throw new UnsupportedOperationException();
	}
}
