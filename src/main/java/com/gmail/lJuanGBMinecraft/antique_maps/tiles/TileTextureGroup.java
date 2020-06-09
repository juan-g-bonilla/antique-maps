package com.gmail.lJuanGBMinecraft.antique_maps.tiles;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.gmail.lJuanGBMinecraft.antique_maps.util.Direction;
import com.google.common.collect.Lists;

/**
 * Represents a group of textures, any of which may be chosen
 * to represent the given TileTextureGroup. 
 * 
 * @author lJuanGB
 */
public enum TileTextureGroup {
		
	TEST(-2, "test"),
	
	UNKNOWN(-1, "unknown"),
	
	ICE(0, "ice_noborder"),
	DESERT(1,
					"sand", "sand",
					"sand2", "sand2",
					"sand3", "sand3",
					"sand_bushes", "sand_bushes",
					"cacti"),
	HILLS(2, "hills"),
	DESERT_HILLS (3,
					"hills", "hills", "hills",
					"hills_bushes",
					"hills_cacti"),
	PLAINS(4, "grass", "grass2", "grass3", "grass4"),
	SUNFLOWERS(5, "sunflowers", "sunflowers2", "grass3", "grass4"),
	
	ICE_SPIKES (6, "ice_spikes", "ice_spikes2"),
	SNOW_PINES (7, "snow_pines", "snow_pines2", "snow_pines3"),
	SNOW_PINES_HILLS (8, "snow_pines_hills", "snow_pines_hills2", "snow_pines_hills3"),
	SNOW_HILLS (9, "snow_hills", "snow_hills2"),
	SNOW (10,
						"snow", "snow", "snow", "snow", "snow",
						"snow1", "snow1", "snow1",
						"snow2", "snow2", "snow2",
						"snow3",
						"snow4",
						"snow5",
						"snow6"),
	
	MOUNTAINS_NAKED (11, "mountains", "mountains2"),
	MOUNTAINS (12,
							"mountains", "mountains",
							"mountains2", "mountains2",
							"mountains3",
							"mountains4"),
	MOUNTAINS_SNOW_CAPS (13, "mountains", "snow_caps"),
	MOUNTAINS_ALL (14,
							"snow_caps", "snow_caps",
							"mountains",
							"mountains2",
							"mountains3",
							"mountains4"),
	
	FOREST(15, "forest", "forest2", "forest3"),
	FOREST_HILLS(16, "forest_hills", "forest_hills2", "forest_hills3"),
	FOREST_FLOWERS(17, "forest_flowers", "forest_flowers2", "forest_flowers3"),
	SPARSE_FOREST (18, "sparse_forest", "sparse_forest2", "sparse_forest3"),
	SPARSE_FOREST_HILLS (19, "sparse_forest_hills", "sparse_forest_hills2", "sparse_forest_hills3"),
	DENSE_FOREST(20, "dense_forest", "dense_forest2"),
	DENSE_FOREST_HILLS(21, "dense_forest_hills", "dense_forest_hills2"),
	
	BIRCH(22, "birch", "birch2"),
	BIRCH_HILLS(23, "birch_hills", "birch_hills2"),
	TALL_BIRCH (24, "tall_birch", "tall_birch2"),
	TALL_BIRCH_HILLS (25, "tall_birch_hills", "tall_birch_hills2"),
	DENSE_BIRCH(26, "dense_birch"),
	
	JUNGLE(27, "jungle", "jungle2"),
	JUNGLE_HILLS(28, "jungle_hills", "jungle_hills2"),
	JUNGLE_CLIFFS (60,
						"jungle_cliffs",	"jungle_cliffs2",
						"bushes_cliffs"),
	JUNGLE_EDGE (29,
						"jungle_edge", "jungle_edge2", "jungle_edge3",
						"grass2", "grass3", "grass4"),
	JUNGLE_EDGE_HILLS (30,
						"jungle_edge_hills", "jungle_edge_hills2", "jungle_edge_hills3",
						"hills_grass", "hills_grass"),
	
	PINES (31, "pines", "pines2", "pines3"),
	PINES_HILLS (32, "pines_hills", "pines_hills2", "pines_hills3"),
	MEGA_SPRUCE (33, "mega_spruce", "mega_spruce2"),
	MEGA_SPRUCE_HILLS (34, "mega_spruce_hills", "mega_spruce_hills2"),
	MEGA_TAIGA(35, "mega_taiga", "mega_taiga2"),
	MEGA_TAIGA_HILLS(36, "mega_taiga_hills", "mega_taiga_hills2"),
	
	SAVANNA(37,
						"savanna", "savanna2", "savanna3",
						"grass",
						"grass2", "grass2",
						"grass3", "grass3",
						"grass4", "grass4"),
	SAVANNA_CLIFFS (38,
						"savanna_cliffs",
						"savanna_cliffs2",
						"savanna_cliffs3",
						"cliffs"),
	PLATEAU_SAVANNA(39,
						"plateau_grass", "plateau_grass",
						"plateau_grass2", "plateau_grass2",
						"plateau_grass3", "plateau_grass3",
						"plateau_savanna",
						"plateau_savanna2",
						"plateau_savanna3"),
	PLATEAU_SAVANNA_M (40,
						"plateau_grass",
						"plateau_grass2",
						"plateau_grass3",
						"plateau_savanna2",
						"plateau_savanna3",
						"cliffs_clouds",
						"savanna_cliffs_clouds",
						"savanna_cliffs_clouds2",
						"savanna_cliffs_clouds3"),
	
	MESA (41,
								"mesa", "mesa2", "mesa3", "mesa4",
								"sand_bushes"),
	BRYCE(42, "bryce", "bryce2", "bryce3", "bryce4"),
	PLATEAU_MESA (43, "plateau_mesa", "plateau_mesa2"),
	PLATEAU_MESA_LOW (44, "plateau_mesa_low", "plateau_mesa_low2"),
	PLATEAU_MESA_TREES (45,
								"plateau_mesa", "plateau_mesa2",
								"plateau_trees"),
	PLATEAU_MESA_TREES_LOW (46,
								"plateau_mesa_low", "plateau_mesa_low2",
								"plateau_trees_low"),
	
	SWAMP (47,
					"swamp", "swamp", "swamp",
					"swamp2",
					"swamp3",
					"swamp4",
					"swamp5",
					"swamp6"),
	SWAMP_HILLS (48,
					"swamp_hills",
					"swamp_hills2",
					"swamp_hills3",
					"swamp_hills4",
					"swamp_hills5"),
	
	WATER (49, "water", "water2"),
	LAVA(50, "lava", "lava2"),
	SHORE (51, "shore", "shore2", "shore3"),
	ROCK_SHORE(52, "rock_shore"),
	LAVA_SHORE(53, "lava_shore", "lava_shore2"),
	
	END_VOID(54, "end_void"),
	END_ISLAND(55, "end_island", "end_island2"),
	END_ISLAND_PLANTS (56, "end_island_plants", "end_island_plants2"),
	
	MUSHROOM (57, "mushroom", "mushroom2"),
	CAVE_WALLS (58, "cave_walls"),
	RAVINE (59, "ravine"),
	
	BUTCHERS_SHOP 	(60, "butchers_shop"),
	CHURCH 			(61, "church"),
	HOUSE_SMALL 	(62, "house_small"),
	L_HOUSE 		(63, "l_house"),
	LIBRARY 		(64, "library"),
	SMITHY 			(65, "smithy"),
	WELL 			(66, "well"),
	
	VILLAGE(67,
			"grass", "grass2", "grass3", "grass4",
			"grass", "grass2", "grass3", "grass4",
			"well", "church", "smithy",
			"l_house", "butchers_shop", "farmland",
			"house_small", "house_small",
			"house_medium", "house_medium"),
	
	HUT(68, "hut"),
	FARMLAND_L(69, "farmland_l"),
	FARMLAND_S(70, "farmland_s"),
	HOUSE(71, "house"),
	
	PATH_X(72, "village_path_x"),
	PATH_Z(73, "village_path_z"),
	
	CASTLE_WALL(74, "castle_wall"),
	CASTLE_TOWER(75, "castle_tower"),
	CASTLE_GATE(76, "castle_bridge_gate"),
	
	FENCE(77, "fence"),
	;
	
	private static final Map<Byte, TileTextureGroup> sets = new HashMap<>();
	static
	{
		for (TileTextureGroup group : TileTextureGroup.values())
		{
			sets.put(group.getSetID(), group);
		}
	}
	
	static
	{
		connectsMutually(PLAINS, SUNFLOWERS);
		WATER.connectsTo(SHORE, ROCK_SHORE, SWAMP);
		LAVA.connectsTo(LAVA_SHORE);
		SWAMP.connectsTo(SWAMP_HILLS);
		SNOW.connectsTo(SNOW_PINES, SNOW_HILLS, ICE_SPIKES, SNOW_PINES_HILLS);
		connectsMutually(MOUNTAINS, MOUNTAINS_NAKED, MOUNTAINS_SNOW_CAPS, MOUNTAINS_ALL);
		DESERT.connectsTo(MESA, BRYCE);
		connectsMutually(PLATEAU_MESA, PLATEAU_MESA_TREES, PLATEAU_SAVANNA, PLATEAU_SAVANNA_M);
		connectsMutually(PLATEAU_MESA_LOW, PLATEAU_MESA_TREES_LOW);
		connectsMutually(END_ISLAND, END_ISLAND_PLANTS);
		SNOW.connectsTo(RAVINE, LAVA);
		connectsMutually(PATH_X, PATH_Z);
		
		connectsMutually(Direction.Type.HORIZONTAL, CASTLE_WALL, CASTLE_TOWER, CASTLE_GATE);
		connectsMutually(Direction.Type.VERTICAL, CASTLE_WALL, CASTLE_TOWER, CASTLE_GATE);
	}
	
	private final byte setID;
	private final List<String> texturesIDs;
	
	private final HashMap<Direction.Type, Set<TileTextureGroup>> connectsTo = new HashMap<>();
	
	private TileTextureGroup(int setID, String... texturesIDs) 
	{
		this.setID = (byte) setID;
		this.texturesIDs = Arrays.asList(texturesIDs);

		for (Direction.Type type : Direction.Type.values())
		{
			connectsTo.put(type, new HashSet<>());
		}
	}
	
	public List<String> getTextures()
	{
		return Lists.newArrayList(texturesIDs);
	}
	
	public String getTexture(int variationIndex)
	{
		return texturesIDs.get(variationIndex);
	}
	
	public String getTexture()
	{
		return getTexture((int) (texturesIDs.size() * Math.random()));
	}
	
	public byte getSetID()
	{
		return setID;
	}
	
	public static TileTextureGroup getByID(byte id)
	{
		return sets.getOrDefault(id, UNKNOWN);
	}
	
	public boolean connects(TileTextureGroup group, Direction.Type typeConnection)
	{
		if (group == null) return false;
		
		if (group.getSetID() == this.getSetID()) return true;
		
		return connectsTo.get(typeConnection).contains(group);
	}
	
	private TileTextureGroup connectsTo(Direction.Type type, TileTextureGroup... groups)
	{
		Collections.addAll(connectsTo.get(type), groups);
		return this;
	}
	
	private TileTextureGroup connectsTo(TileTextureGroup... groups)
	{
		for (Direction.Type type : Direction.Type.values())
		{
			connectsTo(type, groups);
		}
		return this;
	}
	
	private static void connectsMutually(Direction.Type type, TileTextureGroup... groups)
	{
		for (TileTextureGroup group : groups)
		{
			group.connectsTo(type, groups);
		}
	}
	
	private static void connectsMutually(TileTextureGroup... groups)
	{
		for (TileTextureGroup group : groups)
		{
			group.connectsTo(groups);
		}
	}
}
