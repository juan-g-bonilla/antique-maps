package com.gmail.lJuanGBMinecraft.antique_maps.util.data;

import java.util.HashMap;
import java.util.Map;

import com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileNoncontextualTexture;

/**
 * Stores the TileNoncontextualTexture used by the plugin with their name
 * 
 * @author lJuanGB
 */
public class TexturesMap {

	private static Map<String, TileNoncontextualTexture> tilesMap = new HashMap<>();
	
	public static TileNoncontextualTexture getNoncontextualTexture(String string)
	{
		return tilesMap.getOrDefault(string, tilesMap.get("unknown") );
	}

	public static TileNoncontextualTexture putNoncontextualTexture(String key, TileNoncontextualTexture set) 
	{
		return tilesMap.put(key, set);
	}

	public static void nullify() {
		tilesMap = null;
	}
}
