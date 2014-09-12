package br.com.gamemods.spongebukkit.mod;

import br.com.gamemods.spongebukkit.BukkitServer;
import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import org.bukkit.Bukkit;

/**
 * The forge mod class, it will setup the bukkit environment and load bukkit plugins.
 */
@Mod(modid = "SpongeBukkit")
public class SpongeBukkitMod extends DummyModContainer
{
	@Mod.EventHandler
	public void onPreInit(FMLPreInitializationEvent event)
	{
		Bukkit.setServer(new BukkitServer());
	}
}
