package com.gmail.lJuanGBMinecraft.antique_maps.biomes;

import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.BIRCH;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.BIRCH_HILLS;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.BRYCE;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.CAVE_WALLS;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.DENSE_FOREST;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.DENSE_FOREST_HILLS;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.DESERT;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.DESERT_HILLS;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.END_ISLAND;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.END_ISLAND_PLANTS;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.END_VOID;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.FOREST;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.FOREST_FLOWERS;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.FOREST_HILLS;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.ICE;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.ICE_SPIKES;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.JUNGLE;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.JUNGLE_CLIFFS;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.JUNGLE_EDGE;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.JUNGLE_EDGE_HILLS;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.JUNGLE_HILLS;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.MEGA_SPRUCE;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.MEGA_SPRUCE_HILLS;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.MEGA_TAIGA;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.MEGA_TAIGA_HILLS;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.MESA;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.MOUNTAINS;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.MOUNTAINS_ALL;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.MOUNTAINS_SNOW_CAPS;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.MUSHROOM;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.PINES;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.PINES_HILLS;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.PLAINS;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.PLATEAU_MESA;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.PLATEAU_MESA_LOW;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.PLATEAU_MESA_TREES;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.PLATEAU_MESA_TREES_LOW;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.PLATEAU_SAVANNA;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.PLATEAU_SAVANNA_M;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.ROCK_SHORE;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.SAVANNA;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.SAVANNA_CLIFFS;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.SHORE;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.SNOW;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.SNOW_HILLS;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.SNOW_PINES;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.SNOW_PINES_HILLS;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.SUNFLOWERS;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.SWAMP;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.SWAMP_HILLS;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.TALL_BIRCH;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.TALL_BIRCH_HILLS;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.TEST;
import static com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup.WATER;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.block.Biome;

import com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup;

/**
 * Class for storing the corresponding TileTextureSet for each Biome.
 * 
 * @author lJuanGB
 */
public class MapBiome {

	private static Map<Biome, TileTextureGroup> biomesMap = new HashMap<>();
	
	static
	{
		biomesMap.put(Biome.OCEAN, WATER);
		biomesMap.put(Biome.DEEP_OCEAN, WATER);
		biomesMap.put(Biome.RIVER, WATER);
		biomesMap.put(Biome.FROZEN_OCEAN, ICE);
		biomesMap.put(Biome.FROZEN_RIVER, ICE);
		biomesMap.put(Biome.BEACH, SHORE);
		biomesMap.put(Biome.SNOWY_BEACH, SHORE);
		biomesMap.put(Biome.STONE_SHORE, ROCK_SHORE);
		biomesMap.put(Biome.DESERT, DESERT);
		biomesMap.put(Biome.DESERT_LAKES, DESERT);
		biomesMap.put(Biome.DESERT_HILLS, DESERT_HILLS);
		biomesMap.put(Biome.PLAINS, PLAINS);
		biomesMap.put(Biome.SUNFLOWER_PLAINS, SUNFLOWERS);
		biomesMap.put(Biome.SNOWY_TUNDRA, SNOW);
		biomesMap.put(Biome.ICE_SPIKES, ICE_SPIKES);
		biomesMap.put(Biome.MOUNTAINS, MOUNTAINS);
		biomesMap.put(Biome.MOUNTAIN_EDGE, MOUNTAINS);
		biomesMap.put(Biome.GRAVELLY_MOUNTAINS, MOUNTAINS_SNOW_CAPS);
		biomesMap.put(Biome.WOODED_MOUNTAINS, MOUNTAINS_ALL);
		biomesMap.put(Biome.MODIFIED_GRAVELLY_MOUNTAINS, MOUNTAINS_SNOW_CAPS);
		biomesMap.put(Biome.FOREST, FOREST);
		biomesMap.put(Biome.FLOWER_FOREST, FOREST_FLOWERS);
		biomesMap.put(Biome.WOODED_HILLS, FOREST_HILLS);
		biomesMap.put(Biome.DARK_FOREST, DENSE_FOREST);
		biomesMap.put(Biome.DARK_FOREST_HILLS, DENSE_FOREST_HILLS);
		biomesMap.put(Biome.BIRCH_FOREST, BIRCH);
		biomesMap.put(Biome.TALL_BIRCH_FOREST, TALL_BIRCH);
		biomesMap.put(Biome.BIRCH_FOREST_HILLS, BIRCH_HILLS);
		biomesMap.put(Biome.TALL_BIRCH_HILLS, TALL_BIRCH_HILLS);
		biomesMap.put(Biome.JUNGLE, JUNGLE);
		biomesMap.put(Biome.MODIFIED_JUNGLE, JUNGLE_CLIFFS);
		biomesMap.put(Biome.JUNGLE_HILLS, JUNGLE_HILLS);
		biomesMap.put(Biome.JUNGLE_EDGE, JUNGLE_EDGE);
		biomesMap.put(Biome.MODIFIED_JUNGLE_EDGE, JUNGLE_EDGE_HILLS);
		biomesMap.put(Biome.TAIGA, PINES);
		biomesMap.put(Biome.TAIGA_MOUNTAINS, PINES_HILLS);
		biomesMap.put(Biome.TAIGA_HILLS, PINES_HILLS);
		biomesMap.put(Biome.SNOWY_TAIGA, SNOW_PINES);
		biomesMap.put(Biome.SNOWY_TAIGA_HILLS, SNOW_PINES_HILLS);
		biomesMap.put(Biome.SNOWY_TAIGA_MOUNTAINS, SNOW_PINES_HILLS);
		biomesMap.put(Biome.GIANT_TREE_TAIGA, MEGA_TAIGA);
		biomesMap.put(Biome.GIANT_SPRUCE_TAIGA, MEGA_SPRUCE);
		biomesMap.put(Biome.GIANT_TREE_TAIGA_HILLS, MEGA_TAIGA_HILLS);
		biomesMap.put(Biome.GIANT_SPRUCE_TAIGA_HILLS, MEGA_SPRUCE_HILLS);
		biomesMap.put(Biome.SWAMP, SWAMP);
		biomesMap.put(Biome.SWAMP_HILLS, SWAMP_HILLS);
		biomesMap.put(Biome.NETHER, CAVE_WALLS);
		biomesMap.put(Biome.THE_VOID, END_VOID);
		biomesMap.put(Biome.MUSHROOM_FIELDS, MUSHROOM);
		biomesMap.put(Biome.MUSHROOM_FIELD_SHORE, SHORE);
		biomesMap.put(Biome.SAVANNA, SAVANNA);
		biomesMap.put(Biome.SHATTERED_SAVANNA, SAVANNA_CLIFFS);
		biomesMap.put(Biome.BADLANDS, MESA);
		biomesMap.put(Biome.ERODED_BADLANDS, BRYCE);
		biomesMap.put(Biome.BADLANDS_PLATEAU, PLATEAU_MESA);
		biomesMap.put(Biome.WOODED_BADLANDS_PLATEAU, PLATEAU_MESA_TREES);
		biomesMap.put(Biome.MODIFIED_BADLANDS_PLATEAU, PLATEAU_MESA_LOW);
		biomesMap.put(Biome.MODIFIED_WOODED_BADLANDS_PLATEAU, PLATEAU_MESA_TREES_LOW);
		biomesMap.put(Biome.SAVANNA_PLATEAU, PLATEAU_SAVANNA);
		biomesMap.put(Biome.SHATTERED_SAVANNA_PLATEAU, PLATEAU_SAVANNA_M);
		
		biomesMap.put(Biome.SNOWY_MOUNTAINS, SNOW_HILLS);
		biomesMap.put(Biome.WARM_OCEAN, WATER);
		biomesMap.put(Biome.LUKEWARM_OCEAN, WATER);
		biomesMap.put(Biome.COLD_OCEAN, WATER);
		biomesMap.put(Biome.DEEP_LUKEWARM_OCEAN, WATER);
		biomesMap.put(Biome.DEEP_COLD_OCEAN, WATER);
		biomesMap.put(Biome.DEEP_FROZEN_OCEAN, WATER);
		biomesMap.put(Biome.BAMBOO_JUNGLE, JUNGLE);
		biomesMap.put(Biome.BAMBOO_JUNGLE_HILLS, JUNGLE_HILLS);
		
		biomesMap.put(Biome.END_BARRENS, END_ISLAND);
		biomesMap.put(Biome.END_HIGHLANDS, END_ISLAND_PLANTS);
		biomesMap.put(Biome.END_MIDLANDS, END_ISLAND);
		biomesMap.put(Biome.SMALL_END_ISLANDS, END_ISLAND);
		biomesMap.put(Biome.THE_END, END_ISLAND);

	}

	/**
	 * @param b
	 * @return the corresponding TileTextureSet to the Biome
	 */
	public static TileTextureGroup getSetFromBiome(Biome b)
	{
		return biomesMap.getOrDefault(b, TEST);
	}

}
