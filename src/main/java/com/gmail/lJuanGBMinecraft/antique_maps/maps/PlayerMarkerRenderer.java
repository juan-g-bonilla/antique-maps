package com.gmail.lJuanGBMinecraft.antique_maps.maps;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapCursor;
import org.bukkit.map.MapCursorCollection;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

/**
 * Renders the player marker on top of the map
 * 
 * @author lJuanGB
 */
public class PlayerMarkerRenderer extends MapRenderer {

	private boolean tracking;
	private final String world;
	
	public PlayerMarkerRenderer(boolean tracking, String world)
	{
		super(true);

		this.tracking = tracking;
		this.world = world;
	}
	
	@Override
	public void render(MapView map, MapCanvas canvas, Player player) 
	{
		// Cursors
		MapCursorCollection cursors = canvas.getCursors();
		while (cursors.size() > 0) 
		{
		    cursors.removeCursor(cursors.getCursor(0));
		}
		
		if (!tracking) return;
		
		Location loc = player.getLocation();

		if (!loc.getWorld().getName().equals(world)) return;
		
		int centerX = map.getCenterX();
		int centerZ = map.getCenterZ();
		
		int x = 2 * (loc.getBlockX() - centerX);
		int z = 2 * (loc.getBlockZ() - centerZ);

		x = x < -128 ? -128 : x > 127 ? 127 : x;
		z = z < -128 ? -128 : z > 127 ? 127 : z;

		byte direction = (byte) ( Math.round( (loc.getYaw() + 360) % 360 / 360.0 * 16.0 ) % 16 );
				
		MapCursor.Type icon = MapCursor.Type.WHITE_POINTER;
		MapCursor cursor = new MapCursor((byte) x, (byte) z, direction, icon, true);
		cursors.addCursor(cursor);
	}

	public boolean isTracking() 
	{
		return tracking;
	}

	public void setTracking(boolean tracking) 
	{
		this.tracking = tracking;
	}

	public String getWorld() 
	{
		return world;
	}
}
