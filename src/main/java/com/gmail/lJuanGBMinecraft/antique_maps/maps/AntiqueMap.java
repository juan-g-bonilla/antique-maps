package com.gmail.lJuanGBMinecraft.antique_maps.maps;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;

import com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileNoncontextualTexture;
import com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTexture;
import com.gmail.lJuanGBMinecraft.antique_maps.tiles.TileTextureGroup;
import com.gmail.lJuanGBMinecraft.antique_maps.util.Direction;
import com.gmail.lJuanGBMinecraft.antique_maps.util.data.TexturesMap;

/**
 * Stores a 10x10 TileTextureGroup that can be used to generate a full map.
 * The inner 8x8 is used in the map, and the border for context.
 * 
 * @author lJuanGB
 */
public class AntiqueMap {
	
	private TileTextureGroup[][] chunks = new TileTextureGroup[10][10];
	
	public AntiqueMap()
	{
		fillEmpty();
	}

	public AntiqueMap(TileTextureGroup[][] chunks)
	{
		Validate.isTrue(chunks.length == 10 && chunks[0].length == 10);
		
		for (int i = 0; i < 10; i++)
		{
			for (int j = 0; j < 10; j++)
			{
				this.chunks[i][j] = chunks[i][j] == null ? TileTextureGroup.UNKNOWN : chunks[i][j];
			}
		}
	}
	
	public AntiqueMap(byte[] data)
	{
		Validate.isTrue(data.length >= 10*10);
		
		for (int i = 0; i < 10; i++)
		{
			for (int j = 0; j < 10; j++)
			{
				this.chunks[i][j] = TileTextureGroup.getByID( data[i*10 + j] );
			}
		}
	}
	
	public TileTextureGroup getTextureGroup(int i, int j)
	{
		return chunks[i][j];
	}
	
	public void setTextureGroup(int i, int j, TileTextureGroup group)
	{
		chunks[i][j] = group;
	}
	
	public TileTexture getTexture(int i, int j)
	{
		// The actual map is in the central 8x8 grid, rest is for context
		i++;
		j++;
		
		List<Direction> presence = new ArrayList<>();
		
		for (Direction dir : Direction.values())
		{
			int objX = i + dir.x;
			int objY = j + dir.y;
			
			if (chunks[i][j].connects(chunks[objX][objY], dir.type))
			{
				presence.add(dir);
			}
		}
				
		TileNoncontextualTexture nonCont = TexturesMap.getNoncontextualTexture( chunks[i][j].getTexture() );
		
		return nonCont.getTexture(presence);
	}
	
	public byte[] getData() {
		
		byte[] data = new byte[10*10];
		
		for (int i = 0; i < 10; i++)
		{
			for (int j = 0; j < 10; j++)
			{
				data[i*10 + j] = this.chunks[i][j].getSetID();
			}
		}
		
		return data;
	}
	
	private void fillEmpty() {
		for (int i = 0; i < 10; i++)
		{
			for (int j = 0; j < 10; j++)
			{
				this.chunks[i][j] = TileTextureGroup.UNKNOWN;
			}
		}
	}

}
