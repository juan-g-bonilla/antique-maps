package com.gmail.lJuanGBMinecraft.antique_maps.biomes;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChunkSnapshot;
import org.bukkit.Material;
import org.bukkit.block.Biome;

import com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup;

/**
 * Default BiomeDetector for the Overworld
 * 
 * @author lJuanGB
 */
public class OverworldBiomeDetector extends BiomeDetector{

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
				highestY = highestY == 0 ? 0 : highestY - 1;
				Material mat = chunk.getBlockType(i, highestY, j);
				
				// Get the corresping texture for a biome
				TileTextureGroup text = MapBiome.getSetFromBiome(biome);
				
				// Test water
				if (!text.equals(TileTextureGroup.WATER) 
						&& !biome.equals(Biome.SWAMP) 
						&& !biome.equals(Biome.SWAMP_HILLS)
						&& mat.equals(Material.WATER))
				{
					add(ocur, TileTextureGroup.WATER, 4);
				}
				
				// Test Lava
				if (mat.equals(Material.LAVA))
				{
					add(ocur, TileTextureGroup.LAVA, 7);
				}
				
				// Test ravine
				if ((64 - 7) > highestY)
				{
					add(ocur, TileTextureGroup.RAVINE, 12);
				}
	
				// Do not read shores
				if (text.equals(TileTextureGroup.SHORE))
				{
					add(ocur, TileTextureGroup.SHORE, -1);
				}
				if (text.equals(TileTextureGroup.ROCK_SHORE))
				{
					add(ocur, TileTextureGroup.ROCK_SHORE, -1);
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
