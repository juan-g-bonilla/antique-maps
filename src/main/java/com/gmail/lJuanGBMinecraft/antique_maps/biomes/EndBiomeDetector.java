package com.gmail.lJuanGBMinecraft.antique_maps.biomes;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChunkSnapshot;
import org.bukkit.block.Biome;

import com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup;

/**
 * Default BiomeDetector for the End
 * 
 * @author lJuanGB
 */
public class EndBiomeDetector extends BiomeDetector{

	@Override
	public TileTextureGroup getChunkTexture(ChunkSnapshot chunk) {
		
		Map<TileTextureGroup, Integer> ocur = new HashMap<>();
		
		for (int i = 0; i < 16; i++)
		{
			for (int j = 0; j < 16; j++)
			{
				// Information about each block in chunk
				Biome biome = chunk.getBiome(i, j);
				int highestY = chunk.getHighestBlockYAt(i, j);
				
				// Get the corresping texture for a biome
				TileTextureGroup text = MapBiome.getSetFromBiome(biome);
				
				// Test void
				if (highestY == 0)
				{
					add(ocur, TileTextureGroup.END_VOID, 2);
				}
				
				add(ocur, text, 1);
			}
		}
		
		// Get the TileTextureGroup with highest appearances
		TileTextureGroup result = Collections.max(ocur.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
		
		return result;
	}

	private void add(Map<TileTextureGroup, Integer> ocur, TileTextureGroup key, Integer inte)
	{
		ocur.put(key, ocur.getOrDefault(key, 0) + inte );
	}

}
