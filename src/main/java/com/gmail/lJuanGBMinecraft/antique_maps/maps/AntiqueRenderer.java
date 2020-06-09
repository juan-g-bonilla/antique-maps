package com.gmail.lJuanGBMinecraft.antique_maps.maps;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

import com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTexture;

/**
 * Renders the map. Should be the bottom renderer.
 * 
 * @author lJuanGB
 */
public class AntiqueRenderer extends MapRenderer {

	private AntiqueMap map;
	private boolean hasRendered = false;
	
	public AntiqueRenderer(AntiqueMap map)
	{
		this.map = map;
	}
	
	@Override
	public void render(MapView view, MapCanvas canvas, Player player) 
	{		
		if (hasRendered) return; // Only render Map once
		
		hasRendered = true;
				
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				TileTexture text = map.getTexture(i, j);
				text.paintMap(i, j, canvas);
			}
		}
	}

	public AntiqueMap getMap() {
		return map;
	}

	public void setMap(AntiqueMap map) {
		this.map = map;
	}

	public boolean hasRendered() {
		return hasRendered;
	}

	/**
	 * Sets whether this map has already rendered.
	 * Setting this to false causes the map to render again.
	 * 
	 * @param hasRendered
	 */
	public void setHasRendered(boolean hasRendered) {
		this.hasRendered = hasRendered;
	}

	public AntiqueRenderer clone() 
	{
		AntiqueRenderer result = new AntiqueRenderer(map);
		result.setHasRendered(hasRendered);
		return result;
	}
}
