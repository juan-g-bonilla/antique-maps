package com.gmail.lJuanGBMinecraft.antique_maps.util.data;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import com.gmail.lJuanGBMinecraft.antique_maps.AntiqueMaps;
import com.google.common.collect.Lists;

public class Lang {

	private static String usedLang;
	
	private static Map<String, String> map;
	
	/**
	 * Fetches from the plugin data folder the file that contains the language and
	 * loads it to a map.
	 * 
	 * @param lang Name of the file that contains the language. Normally in the format 'en_US'.
	 * @return true if there was a problem loading the language.
	 */
	public static boolean loadLang(String lang) {
		
		usedLang = lang;
		
		File folder = AntiqueMaps.getInstance().getDataFolder();
		
		File langFile = new File(folder, "lang/" + lang + ".yml");
		
		if(!langFile.exists()) return true;
		
		YamlConfiguration config = YamlConfiguration.loadConfiguration(langFile);
		
		map = new HashMap<>();
		
		for(String s : config.getKeys(true)) 
		{
			if(!config.isString(s)) 
			{ 
				continue;
			}
			
			map.put(s, config.getString(s, ""));	
		}
		
		return false;
	}
	
	/**
	 * @return Currently used language (name of the file)
	 */
	public static String getLanguage() {
		return usedLang;
	}
	
	/**
	 * Translates the text from the identifier.
	 * Strings can be input as the variables array and each element will
	 * replace the corresponding identifier in the text, which follow the format %'i's:
	 * 
	 * If variables = [foo, bar] and text = "The name is %0s and not %1s" the resulting
	 * output will be "The name is foo and not bar"
	 * 
	 * @param path the path in the language file
	 * @param wrap length at which to wrap each line
	 * @param variables variables that should be included in the translation
	 * @return the translation
	 */
	public static List<String> text(String path, int wrap, String... variables) {
		
		if(path == null || path.equals("null")) return null;
		
		if(!map.containsKey(path)) 
		{
			AntiqueMaps.log(Level.WARNING, 
					"Tried to access non-translated path: " + path + " for language " + usedLang);
			return Arrays.asList(path);
		}
		
		String text = map.get(path);
		for (int i = 0 ; i < variables.length; i++) 
		{
			text = text.replace("%" + i + "s", variables[i]);
		}
		text = ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', text);

		if (wrap <= 0)
		{
			return Lists.newArrayList(text);
		}
		
		String[] wrapped = WordUtils.wrap(text, wrap).split(System.lineSeparator());
		
		for (int i = 1; i < wrapped.length; i++)
		{
			wrapped[i] = ChatColor.getLastColors(wrapped[0]) + wrapped[i];
		}
		
		return Arrays.asList(wrapped);
	}
	
	public static String text(String path, String...variables)
	{
		return text(path, -1, variables).get(0);
	}
	
}
