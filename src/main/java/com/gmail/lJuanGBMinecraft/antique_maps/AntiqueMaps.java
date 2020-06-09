package com.gmail.lJuanGBMinecraft.antique_maps;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.lJuanGBMinecraft.antique_maps.listeners.MapInteractListener;
import com.gmail.lJuanGBMinecraft.antique_maps.listeners.PlayerInteractItemFrame;
import com.gmail.lJuanGBMinecraft.antique_maps.util.AntiqueItems;
import com.gmail.lJuanGBMinecraft.antique_maps.util.AntiqueRecipes;
import com.gmail.lJuanGBMinecraft.antique_maps.util.data.Config;
import com.gmail.lJuanGBMinecraft.antique_maps.util.data.DataLoader;
import com.gmail.lJuanGBMinecraft.antique_maps.util.data.DataSaver;
import com.gmail.lJuanGBMinecraft.antique_maps.util.data.Lang;
import com.gmail.lJuanGBMinecraft.antique_maps.util.data.TexturesMap;

public class AntiqueMaps extends JavaPlugin {

	private static AntiqueMaps instance;
	
	private DataSaver saver;
	
	@Override
	public void onEnable()
	{
		instance = this;

		if (new DataLoader().loadData())
		{
			log(Level.SEVERE, "Plugin disabled due to catastrophic errors");
			this.getPluginLoader().disablePlugin(this);
			return;
		}
		
		Lang.loadLang( Config.lang.get() );
		
		AntiqueItems.initialize();
		AntiqueRecipes.initialize();
		
		registerRecipes();
		registerListeners();
		
		saver = new DataSaver();
		
	}


	@Override
	public void onDisable()
	{
		saver.saveMaps();
		
		TexturesMap.nullify();
		instance = null;
	}
	
	public static AntiqueMaps getInstance()
	{
		return instance;
	}

	public static void log(Level level, String message)
	{
		instance.getLogger().log(level, message);
	}
	
	public static void log(Level level, String message, Exception ex)
	{
		instance.getLogger().log(level, message, ex);
	}
	
	public DataSaver getSaver()
	{
		return saver;
	}
	
	private void registerListeners() 
	{
		Bukkit.getPluginManager().registerEvents(new MapInteractListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerInteractItemFrame(), this);
	}
	
	private void registerRecipes()
	{
		Bukkit.addRecipe( AntiqueRecipes.ANTIQUE_MAP );
	}
}
