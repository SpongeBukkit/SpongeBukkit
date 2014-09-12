package br.com.gamemods.spongebukkit.server;

import com.avaje.ebean.config.ServerConfig;
import org.bukkit.*;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.help.HelpMap;
import org.bukkit.inventory.*;
import org.bukkit.map.MapView;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.util.CachedServerIcon;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import java.util.logging.Logger;

public class VolatileServer implements Server
{
    private BukkitServer currentServer;

    public BukkitServer getCurrentServer()
    {
        return currentServer;
    }

    public void setCurrentServer(BukkitServer server)
    {
        this.currentServer = server;
        server.getLogger().info("This server is running " + getName() + " version " + getVersion() + " (Implementing API version " + getBukkitVersion() + ")");
    }

    @Override
    public String getName()
    {
        return currentServer == null? "SpongeBukkit-Volatile" : currentServer.getName();
    }

    @Override
    public String getVersion()
    {
        return currentServer == null? VolatileServer.class.getPackage().getImplementationVersion() : currentServer.getVersion();
    }

    @Override
    public String getBukkitVersion()
    {
        return currentServer == null? VolatileServer.class.getPackage().getSpecificationVersion() : currentServer.getBukkitVersion();
    }

    @Override
    public Player[] _INVALID_getOnlinePlayers()
    {
        return currentServer._INVALID_getOnlinePlayers();
    }

    @Override
    public Collection<? extends Player> getOnlinePlayers()
    {
        return currentServer.getOnlinePlayers();
    }

    @Override
    public int getMaxPlayers()
    {
        return currentServer.getMaxPlayers();
    }

    @Override
    public int getPort()
    {
        return currentServer.getPort();
    }

    @Override
    public int getViewDistance()
    {
        return currentServer.getViewDistance();
    }

    @Override
    public String getIp()
    {
        return currentServer.getIp();
    }

    @Override
    public String getServerName()
    {
        return currentServer.getServerName();
    }

    @Override
    public String getServerId()
    {
        return currentServer.getServerId();
    }

    @Override
    public String getWorldType()
    {
        return currentServer.getWorldType();
    }

    @Override
    public boolean getGenerateStructures()
    {
        return currentServer.getGenerateStructures();
    }

    @Override
    public boolean getAllowEnd()
    {
        return currentServer.getAllowEnd();
    }

    @Override
    public boolean getAllowNether()
    {
        return currentServer.getAllowNether();
    }

    @Override
    public boolean hasWhitelist()
    {
        return currentServer.hasWhitelist();
    }

    @Override
    public void setWhitelist(boolean b)
    {
        currentServer.setWhitelist(b);
    }

    @Override
    public Set<OfflinePlayer> getWhitelistedPlayers()
    {
        return currentServer.getWhitelistedPlayers();
    }

    @Override
    public void reloadWhitelist()
    {
        currentServer.reloadWhitelist();
    }

    @Override
    public int broadcastMessage(String s)
    {
        return currentServer.broadcastMessage(s);
    }

    @Override
    public String getUpdateFolder()
    {
        return currentServer.getUpdateFolder();
    }

    @Override
    public File getUpdateFolderFile()
    {
        return currentServer.getUpdateFolderFile();
    }

    @Override
    public long getConnectionThrottle()
    {
        return currentServer.getConnectionThrottle();
    }

    @Override
    public int getTicksPerAnimalSpawns()
    {
        return currentServer.getTicksPerAnimalSpawns();
    }

    @Override
    public int getTicksPerMonsterSpawns()
    {
        return currentServer.getTicksPerMonsterSpawns();
    }

    @Override
    public Player getPlayer(String s)
    {
        return currentServer.getPlayer(s);
    }

    @Override
    public Player getPlayerExact(String s)
    {
        return currentServer.getPlayerExact(s);
    }

    @Override
    public List<Player> matchPlayer(String s)
    {
        return currentServer.matchPlayer(s);
    }

    @Override
    public Player getPlayer(UUID uuid)
    {
        return currentServer.getPlayer(uuid);
    }

    @Override
    public PluginManager getPluginManager()
    {
        return currentServer.getPluginManager();
    }

    @Override
    public BukkitScheduler getScheduler()
    {
        return currentServer.getScheduler();
    }

    @Override
    public ServicesManager getServicesManager()
    {
        return currentServer.getServicesManager();
    }

    @Override
    public List<World> getWorlds()
    {
        return currentServer.getWorlds();
    }

    @Override
    public World createWorld(WorldCreator worldCreator)
    {
        return currentServer.createWorld(worldCreator);
    }

    @Override
    public boolean unloadWorld(String s, boolean b)
    {
        return currentServer.unloadWorld(s, b);
    }

    @Override
    public boolean unloadWorld(World world, boolean b)
    {
        return currentServer.unloadWorld(world, b);
    }

    @Override
    public World getWorld(String s)
    {
        return currentServer.getWorld(s);
    }

    @Override
    public World getWorld(UUID uuid)
    {
        return currentServer.getWorld(uuid);
    }

    @Override
    public MapView getMap(short i)
    {
        return currentServer.getMap(i);
    }

    @Override
    public MapView createMap(World world)
    {
        return currentServer.createMap(world);
    }

    @Override
    public void reload()
    {
        currentServer.reload();
    }

    @Override
    public Logger getLogger()
    {
        return currentServer == null? Logger.getLogger("Minecraft") : currentServer.getLogger();
    }

    @Override
    public PluginCommand getPluginCommand(String command)
    {
        return currentServer.getPluginCommand(command);
    }

    @Override
    public void savePlayers()
    {
        currentServer.savePlayers();
    }

    @Override
    public boolean dispatchCommand(CommandSender sender, String s) throws CommandException
    {
        return currentServer.dispatchCommand(sender, s);
    }

    @Override
    public void configureDbConfig(ServerConfig serverConfig)
    {
        currentServer.configureDbConfig(serverConfig);
    }

    @Override
    public boolean addRecipe(Recipe recipe)
    {
        return currentServer.addRecipe(recipe);
    }

    @Override
    public List<Recipe> getRecipesFor(ItemStack stack)
    {
        return currentServer.getRecipesFor(stack);
    }

    @Override
    public Iterator<Recipe> recipeIterator()
    {
        return currentServer.recipeIterator();
    }

    @Override
    public void clearRecipes()
    {
        currentServer.clearRecipes();
    }

    @Override
    public void resetRecipes()
    {
        currentServer.resetRecipes();
    }

    @Override
    public Map<String, String[]> getCommandAliases()
    {
        return currentServer.getCommandAliases();
    }

    @Override
    public int getSpawnRadius()
    {
        return currentServer.getSpawnRadius();
    }

    @Override
    public void setSpawnRadius(int i)
    {
        currentServer.setSpawnRadius(i);
    }

    @Override
    public boolean getOnlineMode()
    {
        return currentServer.getOnlineMode();
    }

    @Override
    public boolean getAllowFlight()
    {
        return currentServer.getAllowFlight();
    }

    @Override
    public boolean isHardcore()
    {
        return currentServer.isHardcore();
    }

    @Override
    public boolean useExactLoginLocation()
    {
        return currentServer.useExactLoginLocation();
    }

    @Override
    public void shutdown()
    {
        currentServer.shutdown();
    }

    @Override
    public int broadcast(String s, String s2)
    {
        return currentServer.broadcast(s, s2);
    }

    @Override
    public OfflinePlayer getOfflinePlayer(String s)
    {
        return currentServer.getOfflinePlayer(s);
    }

    @Override
    public OfflinePlayer getOfflinePlayer(UUID uuid)
    {
        return currentServer.getOfflinePlayer(uuid);
    }

    @Override
    public Set<String> getIPBans()
    {
        return currentServer.getIPBans();
    }

    @Override
    public void banIP(String s)
    {
        currentServer.banIP(s);
    }

    @Override
    public void unbanIP(String s)
    {
        currentServer.unbanIP(s);
    }

    @Override
    public Set<OfflinePlayer> getBannedPlayers()
    {
        return currentServer.getBannedPlayers();
    }

    @Override
    public BanList getBanList(BanList.Type type)
    {
        return currentServer.getBanList(type);
    }

    @Override
    public Set<OfflinePlayer> getOperators()
    {
        return currentServer.getOperators();
    }

    @Override
    public GameMode getDefaultGameMode()
    {
        return currentServer.getDefaultGameMode();
    }

    @Override
    public void setDefaultGameMode(GameMode gameMode)
    {
        currentServer.setDefaultGameMode(gameMode);
    }

    @Override
    public ConsoleCommandSender getConsoleSender()
    {
        return currentServer.getConsoleSender();
    }

    @Override
    public File getWorldContainer()
    {
        return currentServer.getWorldContainer();
    }

    @Override
    public OfflinePlayer[] getOfflinePlayers()
    {
        return currentServer.getOfflinePlayers();
    }

    @Override
    public Messenger getMessenger()
    {
        return currentServer.getMessenger();
    }

    @Override
    public HelpMap getHelpMap()
    {
        return currentServer.getHelpMap();
    }

    @Override
    public Inventory createInventory(InventoryHolder inventoryHolder, InventoryType inventoryType)
    {
        return currentServer.createInventory(inventoryHolder, inventoryType);
    }

    @Override
    public Inventory createInventory(InventoryHolder inventoryHolder, InventoryType inventoryType, String s)
    {
        return currentServer.createInventory(inventoryHolder, inventoryType, s);
    }

    @Override
    public Inventory createInventory(InventoryHolder inventoryHolder, int i) throws IllegalArgumentException
    {
        return currentServer.createInventory(inventoryHolder, i);
    }

    @Override
    public Inventory createInventory(InventoryHolder inventoryHolder, int i, String s) throws IllegalArgumentException
    {
        return currentServer.createInventory(inventoryHolder, i, s);
    }

    @Override
    public int getMonsterSpawnLimit()
    {
        return currentServer.getMonsterSpawnLimit();
    }

    @Override
    public int getAnimalSpawnLimit()
    {
        return currentServer.getAnimalSpawnLimit();
    }

    @Override
    public int getWaterAnimalSpawnLimit()
    {
        return currentServer.getWaterAnimalSpawnLimit();
    }

    @Override
    public int getAmbientSpawnLimit()
    {
        return currentServer.getAmbientSpawnLimit();
    }

    @Override
    public boolean isPrimaryThread()
    {
        return currentServer.isPrimaryThread();
    }

    @Override
    public String getMotd()
    {
        return currentServer.getMotd();
    }

    @Override
    public String getShutdownMessage()
    {
        return currentServer.getShutdownMessage();
    }

    @Override
    public Warning.WarningState getWarningState()
    {
        return currentServer.getWarningState();
    }

    @Override
    public ItemFactory getItemFactory()
    {
        return currentServer.getItemFactory();
    }

    @Override
    public ScoreboardManager getScoreboardManager()
    {
        return currentServer.getScoreboardManager();
    }

    @Override
    public CachedServerIcon getServerIcon()
    {
        return currentServer.getServerIcon();
    }

    @Override
    public CachedServerIcon loadServerIcon(File file) throws IllegalArgumentException, Exception
    {
        return currentServer.loadServerIcon(file);
    }

    @Override
    public CachedServerIcon loadServerIcon(BufferedImage bufferedImage) throws IllegalArgumentException, Exception
    {
        return currentServer.loadServerIcon(bufferedImage);
    }

    @Override
    public void setIdleTimeout(int i)
    {
        currentServer.setIdleTimeout(i);
    }

    @Override
    public int getIdleTimeout()
    {
        return currentServer.getIdleTimeout();
    }

    @Override
    public UnsafeValues getUnsafe()
    {
        return currentServer.getUnsafe();
    }

    @Override
    public void sendPluginMessage(Plugin plugin, String s, byte[] bytes)
    {
        currentServer.sendPluginMessage(plugin, s, bytes);
    }

    @Override
    public Set<String> getListeningPluginChannels()
    {
        return currentServer.getListeningPluginChannels();
    }
}
