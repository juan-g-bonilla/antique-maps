package com.gmail.lJuanGBMinecraft.antique_maps.tiles;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.Validate;

/**
 * Represent the 8x8 texture of a quarter of a tile.
 * 
 * @author lJuanGB
 */
public class MiniTileTexture {
	
	public static enum Type
	{
		ANGLE(true, true, false,     2, 0,   3, 0,   2, 1,   3, 1),
		NONE(false, false, null,     0, 2,   3, 2,   0, 5,   3, 5),
		ALL(true, true, true,         2, 4,   1, 4,   2, 3,   1, 3),
		HORIZONTAL(true, false, null, 2, 2,   1, 2,   2, 5,   1, 5),
		VERTICAL(false, true, null,   0, 4,   3, 4,   0, 3,   3, 3),
		;

		// Represent the requirements for this Type to be chosen
		private final boolean hor; // True if horizontally adjacent tile must be same
		private final boolean ver; // True if vertically adjacent tile must be same
		private final Boolean diag; // True if diagonal adjacent tile must be same, 
									// null if we should only care about hor y ver
		
		private Map<TileCorner, Point> coords = new HashMap<>();
		
		private Type(boolean hor, boolean ver, Boolean diag, int... coords ) 
		{

			this.hor = hor;
			this.ver = ver;
			this.diag = diag;
			
			for (TileCorner corner : TileCorner.values())
			{
				int i = corner.ordinal()*2;
				this.coords.put(corner, new Point(coords[i], coords[i+1]));
			}
		}
		
		/**
		 * Get coordinates on the 4x6 grid of this Type and Corner
		 * See https://blog.rpgmakerweb.com/tutorials/anatomy-of-an-autotile/
		 * 
		 * @param corner
		 * @return
		 */
		public Point getCoordinates(TileCorner corner)
		{
			return coords.get(corner);
		}
		
		/**
		 * @param horizontal If tile is horizontally adjacent
		 * @param vertical If tile is vertically adjacent
		 * @param diagonal If tile is diagonally adjacent
		 * @return appropiate type for the given case
		 */
		public static Type getType(boolean horizontal, boolean vertical, boolean diagonal)
		{
			for (Type type : Type.values())
			{
				if ((type.hor == horizontal) && (type.ver == vertical))
				{
					if (type.diag != null && type.diag != diagonal)
					{
						continue;
					}
					
					return type;
				}
			}
			
			throw new IllegalArgumentException("Impossible configuration: " 
					+ horizontal + " - " + vertical + " - " + diagonal);
		}
		
	}
	
	private final byte[][] colors;
	
	public MiniTileTexture(byte[][] colors)
	{
		Validate.isTrue(colors.length == 8);
		Validate.isTrue(colors[0].length == 8);
		
		this.colors = colors;
	}

	public byte[][] getColors() 
	{
		return colors;
	}

	public byte getColor(int i, int j) 
	{
		return colors[i][j];
	}
}
