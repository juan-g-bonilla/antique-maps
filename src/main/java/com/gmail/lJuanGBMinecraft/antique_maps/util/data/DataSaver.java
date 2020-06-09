package com.gmail.lJuanGBMinecraft.antique_maps.util.data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

import com.gmail.lJuanGBMinecraft.antique_maps.AntiqueMaps;
import com.gmail.lJuanGBMinecraft.antique_maps.maps.AntiqueRenderer;
import com.gmail.lJuanGBMinecraft.antique_maps.maps.PlayerMarkerRenderer;

public class DataSaver {
	
	private List<Integer> toSave = new ArrayList<>();
	
	public void unMarkForSave(int id)
	{
		toSave.remove(id);
	}
	
	public void markForSave(int id)
	{
		toSave.add(id);
	}
	
	public boolean willBeSaved(int id)
	{
		return toSave.contains(id);
	}
	
	public void saveMaps()
	{
		File folder = new File(AntiqueMaps.getInstance().getDataFolder(), "maps");
		
		for (int id : toSave)
		{
			try 
			{
				@SuppressWarnings("deprecation")
				MapView view = Bukkit.getMap(id);				
				
				AntiqueRenderer renderer = null;
				byte track = 0;
				String world = "unknown";
				for (MapRenderer rend : view.getRenderers())
				{
					if (rend instanceof AntiqueRenderer) 
					{
						renderer = (AntiqueRenderer) rend;
					}
					else if (rend instanceof PlayerMarkerRenderer)
					{
						PlayerMarkerRenderer markerRend = (PlayerMarkerRenderer) rend;
						track = (byte) (markerRend.isTracking() ? 1 : 0);
						world = markerRend.getWorld();
					}
				}
				
				if (renderer == null)
				{
					throw new Exception("Map " + id + " is not defined by AntiqueMaps");
				}
				
				byte[] data = new byte[101];
				byte[] mapData = renderer.getMap().getData();
				
				for (int i = 0; i < 100; i++)
				{
					data[i] = mapData[i];
				}

				data[100] = track;
				
				File mapF = new File(folder, world + File.separator + "map_" + id + ".dat");
				if (mapF.exists() && mapF.isFile())
				{
					mapF.delete();
				}
				
				FileUtils.writeByteArrayToFile(mapF, data);
			}
			catch (Exception ex)
			{
				AntiqueMaps.log(Level.WARNING, "Could not save map: " + id);
			}
		}
	}
}
