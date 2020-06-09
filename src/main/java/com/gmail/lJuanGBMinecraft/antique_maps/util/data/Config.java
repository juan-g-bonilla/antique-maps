package com.gmail.lJuanGBMinecraft.antique_maps.util.data;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.logging.Level;

import org.apache.commons.lang.Validate;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import com.gmail.lJuanGBMinecraft.antique_maps.AntiqueMaps;
import com.gmail.lJuanGBMinecraft.antique_maps.util.data.Config.Entry.Type;
import com.google.common.collect.Lists;
import com.google.common.io.Files;

/**
 * Handles config file reading and storing of variables
 * 
 * @author lJuanGB
 */
public class Config {

	// Do not remove from here will cause null pointer
	private static List<Entry<?>> entries = new ArrayList<>();

	//public static Entry<Integer> map_palette = new Entry<>("map_palette", Type.INTEGER, 0);
	public static Entry<String> lang = new Entry<>("lang", Type.STRING, "en_US");
	public static Entry<Boolean> async = new Entry<>("async", Type.BOOLEAN, true);
	
	public static Entry<Boolean> tracking_default = new Entry<>("tracking.default", Type.BOOLEAN, true);
	public static Entry<Boolean> tracking_allowChange = new Entry<>("tracking.allowChange", Type.BOOLEAN, true);

	public static Entry<List<String>> map_recipe = new Entry<>("map_recipe", Type.STRINGLIST, 
			Lists.newArrayList("paper","paper","paper","black_dye"));

	
	public static void load()
	{
		File configF = new File(AntiqueMaps.getInstance().getDataFolder(), "config.yml");
		
		if (!configF.exists()) // If config is not present, place it from resources
		{
			AntiqueMaps.getInstance().getDataFolder().mkdirs();
			InputStream confStream = AntiqueMaps.getInstance().getResource("config.yml");
			
			try 
			{
				byte[] buffer = new byte[confStream.available()];
				confStream.read(buffer);
				Files.write(buffer, configF);
			} 
			catch (IOException e) 
			{
				AntiqueMaps.log(Level.SEVERE, "Could not copy config file", e);
			}
		}
		
		reload();
	}
	
	public static void reload()
	{
		AntiqueMaps.getInstance().reloadConfig();
		FileConfiguration file = AntiqueMaps.getInstance().getConfig();
		
		for (Entry<?> entry : entries)
		{
			entry.set(file);
		}
	}
	
	public static class Entry<T> {
		
		protected static class Type<T> {
			
			public static Type<String> STRING = new Type<>((c,s) -> c.getString(s));
			public static Type<Double> DOUBLE = new Type<>((c,s) -> c.getDouble(s));
			public static Type<Integer> INTEGER = new Type<>((c,s) -> c.getInt(s));
			public static Type<Boolean> BOOLEAN = new Type<>((c,s) -> c.getBoolean(s));
			public static Type<List<String>> STRINGLIST = new Type<>((c,s) -> c.getStringList(s));
			
			private final BiFunction<ConfigurationSection,String, T> func;
			
			private Type(BiFunction<ConfigurationSection,String, T> func)
			{
				this.func = func;
			}
			
			public T get(ConfigurationSection sec, String path)
			{
				return func.apply(sec, path);
			}
		}
		
		protected T value;
		protected final T defaultValue;
		
		protected final Type<T> type;
		
		protected final String path;
				
		private Entry(String path, Type<T> type, T defaultValue)
		{
			Validate.notNull(path);
			Validate.notNull(type);
			Validate.notNull(defaultValue);
			
			this.path = path;
			this.defaultValue = defaultValue;
			this.type = type;
			
			entries.add(this);
		}

		public T get() 
		{
			return value;
		}
		
		public T getDefault()
		{
			return defaultValue;
		}
		
		public void set(ConfigurationSection conf)
		{
			T obj = type.get(conf, path);
			
			if (obj == null)
			{
				value = defaultValue;
				AntiqueMaps.log(Level.WARNING, 
						"Config value " + path + " could not be read");
			}
			else
			{
				value = obj;
			}
		}
	}
	
}
