package com.gmail.lJuanGBMinecraft.antique_maps.maps;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.ChunkSnapshot;
import org.bukkit.World;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

import com.gmail.lJuanGBMinecraft.antique_maps.AntiqueMaps;
import com.gmail.lJuanGBMinecraft.antique_maps.biomes.BiomeDetector;
import com.gmail.lJuanGBMinecraft.antique_maps.util.data.Config;

public class MapsManager {
	
	/**
	 * Updates the map with given id with a new AntiqueMap 
	 * and / or tracking or not
	 * 
	 * @param id
	 * @param map
	 * @param track
	 */
	public static void update(int id, AntiqueMap map, Boolean track)
	{
		@SuppressWarnings("deprecation")
		MapView view = Bukkit.getMap(id);
		
		AntiqueMaps.getInstance().getSaver().markForSave(id);
		
		for (MapRenderer rend : view.getRenderers())
		{
			if (map != null && rend instanceof AntiqueRenderer)
			{
				AntiqueRenderer aRend = (AntiqueRenderer) rend;
				aRend.setMap(map);
				aRend.setHasRendered(false);
			}
			
			if (track != null && rend instanceof PlayerMarkerRenderer)
			{
				((PlayerMarkerRenderer) rend).setTracking(track);
			}
		}
	}
	
	/**
	 * Renders the antique map on all maps with given ID
	 * 
	 * @param id The id of the map to render. This map MUST already exist.
	 * @param map The antique map to render
	 * @param track  Whether it should show player marker
	 * @param world The world this map is associated with
	 */
	public static void render(int id, AntiqueMap map, boolean track, String world)
	{
		@SuppressWarnings("deprecation")
		MapView view = Bukkit.getMap(id);
		
		while (view.getRenderers().size() > 0)
		{
			view.removeRenderer( view.getRenderers().get(0) );
		}
				
		AntiqueRenderer renderer = new AntiqueRenderer(map);
		view.addRenderer(renderer);
		PlayerMarkerRenderer pMarker = new PlayerMarkerRenderer(track, world);
		view.addRenderer(pMarker);
	}
	
	/**
	 * Generates a new map and renders it on the given id.
	 * Also marks the id to ensure that the data is stored
	 * as a file on plugin disable.
	 * 
	 * @param id of the map that will show this antique map
	 * @param centerX center of the map to be rendered
	 * @param centerZ center of the map to be rendered
	 * @param world center of the map to be rendered
	 */
	public static void render(int id, int centerX, int centerZ, World world, BiomeDetector det)
	{
		ChunkSnapshot[][] chunks = getChunks(centerX, centerZ, world);
		AntiqueMap map = det.generateMap(chunks);
		
		render(id, map, Config.tracking_default.get(), world.getName());
		AntiqueMaps.getInstance().getSaver().markForSave(id);
	}
	
	/**
	 * Generates a new map and renders it on the given id.
	 * Performs the operation the most resource intensive
	 * operation asynchronously. Not Thread Safe.
	 * Also marks the id to ensure that the data is stored
	 * as a file on plugin disable.
	 * 
	 * @param id of the map that will show this antique map
	 * @param centerX center of the map to be rendered
	 * @param centerZ center of the map to be rendered
	 * @param world center of the map to be rendered
	 */
	public static void asyncRender(int id, int centerX, int centerZ, World world, BiomeDetector det)
	{
		ChunkSnapshot[][] chunks = getChunks(centerX, centerZ, world);
		
		Bukkit.getScheduler().runTaskAsynchronously(AntiqueMaps.getInstance(), (bt)->
		{
			AntiqueMap map = det.generateMap(chunks);
			
			Bukkit.getScheduler().runTask(AntiqueMaps.getInstance(), (bt2)->
			{
				render(id, map, Config.tracking_default.get(), world.getName());
				AntiqueMaps.getInstance().getSaver().markForSave(id);
			});
		});
	}
	
	private static ChunkSnapshot[][] getChunks(int centerX, int centerZ, World world)
	{
		ChunkSnapshot[][] result = new ChunkSnapshot[10][10];
		
		Chunk center = world.getBlockAt(centerX, 64, centerZ).getChunk();
		
		for( int i = 0; i < 10; i += 1)
		{
			for( int j = 0; j < 10; j += 1)
			{
				int x = center.getX() - 5 + i;
				int y = center.getZ() - 5 + j;

				result[i][j] = world.getChunkAt(x, y).getChunkSnapshot(true, true, false);				
			}
		}
		
		return result;
	}
}
