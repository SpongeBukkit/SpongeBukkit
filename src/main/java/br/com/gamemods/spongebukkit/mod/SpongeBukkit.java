package br.com.gamemods.spongebukkit.mod;

import br.com.gamemods.spongebukkit.BukkitServer;
import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import org.bukkit.Bukkit;

/**
 * Created by José Roberto on 11/09/2014.
 */
@Mod(modid = "SpongeBukkit")
public class SpongeBukkit extends DummyModContainer
{
	@Mod.EventHandler
	public void onPreInit(FMLPreInitializationEvent event)
	{
		Bukkit.setServer(new BukkitServer());
	}
}
