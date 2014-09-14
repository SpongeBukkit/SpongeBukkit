package br.com.gamemods.spongebukkit.server;

import br.com.gamemods.spongebukkit.command.BukkitCommandMap;
import br.com.gamemods.spongebukkit.command.BukkitConsoleCommandSender;
import br.com.gamemods.spongebukkit.entity.EntityMap;
import br.com.gamemods.spongebukkit.mod.SpongeBukkitMod;
import br.com.gamemods.spongebukkit.scheduler.SpongeBukkitScheduler;
import br.com.gamemods.spongebukkit.world.WorldMap;
import com.avaje.ebean.config.ServerConfig;
import com.google.common.base.Preconditions;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.help.HelpMap;
import org.bukkit.inventory.*;
import org.bukkit.map.MapView;
import org.bukkit.plugin.*;
import org.bukkit.plugin.java.JavaPluginLoader;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.plugin.messaging.StandardMessenger;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.util.CachedServerIcon;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

public class BukkitServer implements Server
{
    private final SpongeBukkitMod mod;
    private final BukkitCommandMap commandMap;
    private final SimplePluginManager pluginManager;
    private final SimpleServicesManager servicesManager = new SimpleServicesManager();
    private final BukkitHelpMap helpMap = new BukkitHelpMap(this);
    private final MinecraftServer server;
    private final StandardMessenger messenger = new StandardMessenger();
    private SpongeBukkitScheduler scheduler = new SpongeBukkitScheduler();
    private long started = 0;
    private File pluginDir; // This will be configurable later, so do not convert into a local variable
    private BukkitConsoleCommandSender consoleCommandSender;

    public BukkitServer(SpongeBukkitMod mod, MinecraftServer server)
    {
        this.mod = Preconditions.checkNotNull(mod);
        this.server = Preconditions.checkNotNull(server);
        this.commandMap = new BukkitCommandMap(this);
        this.pluginManager = new SimplePluginManager(this,this.commandMap);
        this.pluginManager.registerInterface(JavaPluginLoader.class);
    }

    public synchronized void start()
    {
        if(started != 0)
            throw new IllegalStateException("Already started");

        EntityMap.clear();
        WorldMap.clear();

        consoleCommandSender = new BukkitConsoleCommandSender(this);
        started = System.currentTimeMillis();

        loadPlugins();
    }

    private void loadPlugins()
    {
        pluginDir = server.getFile("plugins");
        if(!pluginDir.isDirectory())
            if(!pluginDir.mkdirs() || !pluginDir.isDirectory())
                throw new RuntimeException(new IOException("Failed to create the directory "+pluginDir.getAbsolutePath()));

        getLogger().info("Loading plugins at "+pluginDir.getAbsolutePath());
        Plugin[] plugins = pluginManager.loadPlugins(pluginDir);
        getLogger().info("Loaded "+plugins.length+" plugins");
        for(Plugin plugin: plugins)
        {
            getLogger().info("Enabling plugin "+plugin.getName()+"-"+plugin.getDescription().getVersion());
            pluginManager.enablePlugin(plugin);
        }
    }

    @Override
    public String getName()
    {
        return "SpongeBukkit";
    }

    @Override
    public String getVersion()
    {
        return "1.7.10-R1-SNAPSHOT";
    }

    @Override
    public String getBukkitVersion()
    {
        return "1.7.10-R0.1-SNAPSHOT";
    }

    @Override
    public Player[] _INVALID_getOnlinePlayers()
    {
        List<EntityPlayerMP> list = server.getConfigurationManager().playerEntityList;
        Player[] players = new Player[list.size()];
        int i = 0;
        for(EntityPlayerMP playerMP: list)
            players[i++] = (Player) EntityMap.getBukkitEntity(playerMP.worldObj.provider.dimensionId, playerMP.getUniqueID());

        return players;
    }

    @Override
    public Collection<? extends Player> getOnlinePlayers()
    {
        return Arrays.asList(_INVALID_getOnlinePlayers());
    }

    @Override
    public int getMaxPlayers()
    {
        return server.getMaxPlayers();
    }

    @Override
    public int getPort()
    {
        return server.getPort();
    }

    @Override
    public int getViewDistance()
    {
        return server.getConfigurationManager().getViewDistance();
    }

    @Override
    public String getIp()
    {
        return server.getServerHostname();
    }

    @Override
    public String getServerName()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getServerId()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getWorldType()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean getGenerateStructures()
    {
        return server.canStructuresSpawn();
    }

    @Override
    public boolean getAllowEnd()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean getAllowNether()
    {
        return server.getAllowNether();
    }

    @Override
    public boolean hasWhitelist()
    {
        return server.getConfigurationManager().isWhiteListEnabled();
    }

    @Override
    public void setWhitelist(boolean b)
    {
        server.getConfigurationManager().setWhiteListEnabled(b);
    }

    @Override
    public Set<OfflinePlayer> getWhitelistedPlayers()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void reloadWhitelist()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int broadcastMessage(String s)
    {
        List<EntityPlayerMP> players = server.getConfigurationManager().playerEntityList;
        ChatComponentText text = new ChatComponentText(s);
        int count = 0;
        for(EntityPlayerMP playerMP: players)
        {
            playerMP.addChatMessage(text);
            count++;
        }
        return count;
    }

    @Override
    public String getUpdateFolder()
    {
        return "update";
    }

    @Override
    public File getUpdateFolderFile()
    {
        return new File("update");
    }

    @Override
    public long getConnectionThrottle()
    {
        return 0;
    }

    @Override
    public int getTicksPerAnimalSpawns()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getTicksPerMonsterSpawns()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Player getPlayer(String s)
    {
        EntityPlayerMP playerMP = server.getConfigurationManager().func_152612_a(s);
        if(playerMP == null) return null;
        return (Player)EntityMap.getBukkitEntity(playerMP.dimension, playerMP.getUniqueID());
    }

    @Override
    public Player getPlayerExact(String s)
    {
        List<EntityPlayerMP> players = server.getConfigurationManager().playerEntityList;
        for(EntityPlayerMP playerMP: players)
            if(s.equals(playerMP.getCommandSenderName()))
                return (Player)EntityMap.getBukkitEntity(playerMP.dimension, playerMP.getUniqueID());

        return null;
    }

    @Override
    public List<Player> matchPlayer(String s)
    {
        s = s.toLowerCase();
        List<EntityPlayerMP> players = server.getConfigurationManager().playerEntityList;
        List<Player> matchedPlayers = new ArrayList<>(5);
        for(EntityPlayerMP playerMP: players)
        {
            String loweredName = playerMP.getCommandSenderName().toLowerCase();
            if(loweredName.startsWith(s) || loweredName.endsWith(s))
                matchedPlayers.add((Player)EntityMap.getBukkitEntity(playerMP.dimension, playerMP.getUniqueID()));
        }

        return matchedPlayers;
    }

    @Override
    public Player getPlayer(UUID uuid)
    {
        List<EntityPlayerMP> players = server.getConfigurationManager().playerEntityList;
        for(EntityPlayerMP playerMP: players)
            if (playerMP.getUniqueID().equals(uuid))
                return (Player) EntityMap.getBukkitEntity(playerMP.dimension, uuid);
        return null;
    }

    @Override
    public PluginManager getPluginManager()
    {
        return pluginManager;
    }

    @Override
    public BukkitScheduler getScheduler()
    {
        return scheduler;
    }

    @Override
    public ServicesManager getServicesManager()
    {
        return servicesManager;
    }

    @Override
    public List<World> getWorlds()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public World createWorld(WorldCreator worldCreator)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean unloadWorld(String s, boolean b)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean unloadWorld(World world, boolean b)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public World getWorld(String s)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public World getWorld(UUID uuid)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public MapView getMap(short i)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public MapView createMap(World world)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void reload()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Logger getLogger()
    {
        return Logger.getLogger("Minecraft");
    }

    @Override
    public PluginCommand getPluginCommand(String command)
    {
        Command cmd = commandMap.getCommand(command);
        if(!(cmd instanceof PluginCommand))
            return null;
        return (PluginCommand) cmd;
    }

    @Override
    public void savePlayers()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean dispatchCommand(CommandSender sender, String s) throws CommandException
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void configureDbConfig(ServerConfig serverConfig)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addRecipe(Recipe recipe)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Recipe> getRecipesFor(ItemStack stack)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<Recipe> recipeIterator()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clearRecipes()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void resetRecipes()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<String, String[]> getCommandAliases()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getSpawnRadius()
    {
        return server.getSpawnProtectionSize();
    }

    @Override
    public void setSpawnRadius(int i)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean getOnlineMode()
    {
        return server.isServerInOnlineMode();
    }

    @Override
    public boolean getAllowFlight()
    {
        return server.isFlightAllowed();
    }

    @Override
    public boolean isHardcore()
    {
        return server.isHardcore();
    }

    @Override
    public boolean useExactLoginLocation()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void shutdown()
    {
        getLogger().info("Bukkit implementation is shutting down...");

        getLogger().info("Clearing plugins...");
        pluginManager.clearPlugins();
        getLogger().info("Removing commands...");
        commandMap.clearCommands();

        getLogger().info("Stopping the server...");
        server.initiateShutdown();
    }

    public BukkitCommandMap getCommandMap()
    {
        return commandMap;
    }

    @Override
    public int broadcast(String s, String s2)
    {
        List<EntityPlayerMP> players = server.getConfigurationManager().playerEntityList;
        ChatComponentText text = new ChatComponentText(s);
        int count = 0;
        for(EntityPlayerMP playerMP: players)
        {
            Player player = (Player)EntityMap.getBukkitEntity(playerMP.dimension, playerMP.getUniqueID());
            if(!player.hasPermission(s2)) continue;
            playerMP.addChatMessage(text);
            count++;
        }
        return count;
    }

    @Override
    public OfflinePlayer getOfflinePlayer(String s)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public OfflinePlayer getOfflinePlayer(UUID uuid)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<String> getIPBans()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void banIP(String s)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void unbanIP(String s)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<OfflinePlayer> getBannedPlayers()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public BanList getBanList(BanList.Type type)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<OfflinePlayer> getOperators()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public GameMode getDefaultGameMode()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setDefaultGameMode(GameMode gameMode)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public ConsoleCommandSender getConsoleSender()
    {
        return consoleCommandSender;
    }

    @Override
    public File getWorldContainer()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public OfflinePlayer[] getOfflinePlayers()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Messenger getMessenger()
    {
        return messenger;
    }

    @Override
    public HelpMap getHelpMap()
    {
        return helpMap;
    }

    @Override
    public Inventory createInventory(InventoryHolder inventoryHolder, InventoryType inventoryType)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Inventory createInventory(InventoryHolder inventoryHolder, InventoryType inventoryType, String s)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Inventory createInventory(InventoryHolder inventoryHolder, int i) throws IllegalArgumentException
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Inventory createInventory(InventoryHolder inventoryHolder, int i, String s) throws IllegalArgumentException
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getMonsterSpawnLimit()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getAnimalSpawnLimit()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getWaterAnimalSpawnLimit()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getAmbientSpawnLimit()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isPrimaryThread()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getMotd()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getShutdownMessage()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Warning.WarningState getWarningState()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public ItemFactory getItemFactory()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public ScoreboardManager getScoreboardManager()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public CachedServerIcon getServerIcon()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public CachedServerIcon loadServerIcon(File file) throws IllegalArgumentException, Exception
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public CachedServerIcon loadServerIcon(BufferedImage bufferedImage) throws IllegalArgumentException, Exception
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setIdleTimeout(int i)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getIdleTimeout()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public UnsafeValues getUnsafe()
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

    public MinecraftServer getVanillaServer()
    {
        return server;
    }
}
