package com.gmail.lJuanGBMinecraft.antique_maps.util.data;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Level;

import org.apache.commons.io.FileUtils;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.map.MapPalette;

import com.gmail.lJuanGBMinecraft.antique_maps.AntiqueMaps;
import com.gmail.lJuanGBMinecraft.antique_maps.maps.AntiqueMap;
import com.gmail.lJuanGBMinecraft.antique_maps.maps.MapsManager;
import com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileNoncontextualTexture;
import com.google.common.io.Files;

public class DataLoader {
	
	private final List<String> tileList;
	private final List<String> langList;
	
	public DataLoader()	
	{
		InputStream stream = AntiqueMaps.getInstance().getResource("dataList.yml");
		InputStreamReader reader = new InputStreamReader(stream);
		YamlConfiguration conf = YamlConfiguration.loadConfiguration(reader);
		
		langList = conf.getStringList("lang");
		tileList = conf.getStringList("tiles");
	}
	
	public boolean loadData() {
		
		File dataFolder = AntiqueMaps.getInstance().getDataFolder();
		
		for (String lang : langList)
		{
			String langFName = "lang/" + lang + ".yml";
			File langF = new File(dataFolder, langFName);
			if (!langF.exists())
			{
				InputStream langStream = AntiqueMaps.getInstance().getResource(langFName);
				
				try 
				{
					FileUtils.copyInputStreamToFile(langStream, langF);
				} 
				catch (IOException e) 
				{
					AntiqueMaps.log(Level.SEVERE, "Could not copy language " + lang);
				}
			}
		}
		
		File configF = new File(dataFolder, "config.yml");
		if (!configF.exists())
		{
			InputStream confStream = AntiqueMaps.getInstance().getResource("config.yml");
			
			try 
			{
				FileUtils.copyInputStreamToFile(confStream, configF);
			} 
			catch (IOException e) 
			{
				AntiqueMaps.log(Level.SEVERE, "Could not copy config");
				return true;
			}
		}
		Config.reload();
		
		for (String tile : tileList)
		{
			String tileFName = "tiles/" + tile + ".png";
			File tileF = new File(dataFolder, tileFName);
			if (!tileF.exists())
			{
				InputStream langStream = AntiqueMaps.getInstance().getResource(tileFName);
				
				try 
				{
					FileUtils.copyInputStreamToFile(langStream, tileF);
				} 
				catch (Exception e) 
				{
					AntiqueMaps.log(Level.SEVERE, "Could not copy tile texture " + tile);
				}
			}
		}		
		reloadTileTextures();
		
		File mapDir = new File(dataFolder, "maps");
		if (mapDir.exists() && mapDir.isDirectory())
		{
			for (String world : mapDir.list())
			{
				File worldDir = new File(mapDir, world);
				
				if (!worldDir.exists() || !worldDir.isDirectory()) continue;
				
				for (String fileMapS : worldDir.list() )
				{
					File fileMap = new File(worldDir, fileMapS);
					
					if (!fileMapS.matches("map_\\d*\\.dat"))
					{
						continue;
					}
					
					try
					{
						int id = Integer.parseInt( fileMapS.replaceAll("\\D+","") );
						byte[] data = Files.toByteArray(fileMap);
						
						AntiqueMap map = new AntiqueMap(data);
						boolean track = data[10*10] != 0;
						MapsManager.render(id, map, track, world);
					}
					catch(Exception ex)
					{
						AntiqueMaps.log(Level.WARNING, 
								"Map " + fileMapS + " could not be loaded: " + ex.getMessage());
						continue;
					}
					
				}
			}
		}
		
		/*
		File mapDir = new File(dataFolder, "maps");
		if (mapDir.exists() && mapDir.isDirectory())
		{
			for (String fileMapS : mapDir.list() )
			{
				File fileMap = new File(mapDir, fileMapS);
				
				if (!fileMapS.matches("map_\\d*\\.dat"))
				{
					continue;
				}
				
				try
				{
					int id = Integer.parseInt( fileMapS.replaceAll("\\D+","") );
					byte[] data = Files.toByteArray(fileMap);
					
					AntiqueMap map = new AntiqueMap(data);
					boolean track = data[10*10] != 0;
					MapsManager.render(id, map, track);
				}
				catch(Exception ex)
				{
					AntiqueMaps.log(Level.WARNING, 
							"Map " + fileMapS + " could not be loaded: " + ex.getMessage());
					continue;
				}
				
			}
		}
		*/
		
		return false;
	}
	
	@SuppressWarnings("deprecation")
	public static void reloadTileTextures()
	{
		File tileFolder = new File(AntiqueMaps.getInstance().getDataFolder(), "tiles");
		
		Function<Color, Byte> palette = (c) -> 
		{
			c = alphaBlend(c, new Color(214, 190, 140));
			return MapPalette.matchColor(c);
		};		
		
		if (tileFolder.exists() && tileFolder.isDirectory())
		{
			for (String fileN : tileFolder.list() )
			{
				File file = new File(tileFolder, fileN);
				
				if( !fileN.endsWith(".png") )
				{
					continue;
				}
				
				String key = fileN.replaceAll(".png", "");
				
				TileNoncontextualTexture set;
				try 
				{
					set = new TileNoncontextualTexture(file, palette);
				} 
				catch (Exception e) 
				{
					AntiqueMaps.log(Level.WARNING, "Could not load texture " + fileN + ": ", e);
					continue;
				}

				TexturesMap.putNoncontextualTexture(key, set);
			}
		}
		else
		{
			AntiqueMaps.log(Level.SEVERE, "tiles folder doesn't exists, couldn't load tiles textures");
		}
	}
	
	/**
	 * Overlays a color (top) on top of a background color (back),
	 * making the assumption that the latter is fully opaque (back.alpha = 255)
	 * 
	 * @param top Color to overlay on top of background
	 * @param back Background color
	 * @return the resulting color
	 */
	private static Color alphaBlend(Color top, Color back)
	{
		double a = top.getAlpha() / 255.0;
		
		int outR = (int) (top.getRed()*a + back.getRed()*(1-a));
		int outG = (int) (top.getGreen()*a + back.getGreen()*(1-a));
		int outB = (int) (top.getBlue()*a + back.getBlue()*(1-a));
		
		return new Color(outR, outG, outB);
	}
}
