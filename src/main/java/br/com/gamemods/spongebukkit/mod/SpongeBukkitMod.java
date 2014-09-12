package br.com.gamemods.spongebukkit.mod;

import br.com.gamemods.spongebukkit.server.BukkitServer;
import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.Logger;
import org.bukkit.Bukkit;

/**
 * The forge mod class, it will setup the bukkit environment and load bukkit plugins.
 */
@Mod(modid = "SpongeBukkit")
public class SpongeBukkitMod extends DummyModContainer
{
	private Logger logger;

	@Mod.EventHandler
	void onPreInit(FMLPreInitializationEvent event)
	{
		logger = event.getModLog();
		Bukkit.setServer(new BukkitServer(this));
		MinecraftForge.EVENT_BUS.register(new SpongeForgeListener(this));
	}

	public Logger getLogger()
	{
		return logger;
	}
}
