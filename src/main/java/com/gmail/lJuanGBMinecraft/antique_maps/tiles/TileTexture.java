package com.gmail.lJuanGBMinecraft.antique_maps.tiles;

import java.util.Map;

import org.apache.commons.lang.Validate;
import org.bukkit.map.MapCanvas;

/**
 * Represents a set of 4 MiniTileTextures to form a complete Tile
 * 
 * @author lJuanGB
 */
public class TileTexture {

	private MiniTileTexture[] minis = new MiniTileTexture[4];
	
	public TileTexture(Map<TileCorner, MiniTileTexture> minis)
	{
		Validate.isTrue(minis.keySet().size() == 4, "All 4 corners must be specified");
		
		minis.entrySet().forEach((e) -> setCorner(e.getKey(), e.getValue()));
	}
	
	public void setCorner(TileCorner corner, MiniTileTexture mini)
	{
		this.minis[corner.ordinal()] = mini;
	}
	
	public MiniTileTexture getCorner(TileCorner corner)
	{
		return minis[corner.ordinal()];
	}
	
	
	/**
	 * Draws the texture on the canvas at the grid position specified by x and y.
	 * The grid is 8x8.
	 * 
	 * @param x Number between 0 and 7 (inclusive)
	 * @param y Number between 0 and 7 (inclusive)
	 * @param canvas
	 */
	public void paintMap(int x, int y, MapCanvas canvas)
	{
		Validate.isTrue(x >= 0 && x < 8, "x must be between 0 and 7");
		Validate.isTrue(y >= 0 && y < 8, "y must be between 0 and 7");
		Validate.notNull(canvas);

		for (TileCorner cor : TileCorner.values())
		{
			for (int i = 0; i < 8; i ++)
			{
				for (int j = 0; j < 8; j ++)
				{
					byte color = getCorner(cor).getColor(i, j);
					canvas.setPixel(x*16 + cor.x*8 + i, y*16 + cor.y*8 + j, color);
				}
			}
		}
		
	}
}
