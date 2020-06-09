package com.gmail.lJuanGBMinecraft.antique_maps.biomes;

import org.bukkit.ChunkSnapshot;

import com.gmail.lJuanGBMinecraft.antique_maps.maps.AntiqueMap;
import com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup;

/**
 * Class that generates an AntiqueMap from ChunkSnapshots
 * 
 * @author lJuanGB
 */
public abstract class BiomeDetector {

	/**
	 * Returns an appropiate TileTextureGroup given a ChunkSnapshot
	 * 
	 * @param chunk
	 * @return
	 */
	public abstract TileTextureGroup getChunkTexture(ChunkSnapshot chunk);
	
	/**
	 * Generates AntiqueMap from a matrix of ChunkSnapshots
	 * 
	 * @param chunks A 10x10 ChunkSnapshots matrix
	 * @return
	 */
	public AntiqueMap generateMap(ChunkSnapshot[][] chunks)
	{
		TileTextureGroup[][] tiles = new TileTextureGroup[10][10];
						
		for( int i = 0; i < 10; i += 1)
		{
			for( int j = 0; j < 10; j += 1)
			{
				tiles[i][j] = this.getChunkTexture(chunks[i][j]);
			}
		}
		
		return new AntiqueMap(tiles);
	}
	
}
