package com.gmail.lJuanGBMinecraft.antique_maps.tiles;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import javax.imageio.ImageIO;

import org.apache.commons.lang.Validate;

import com.gmail.lJuanGBMinecraft.antique_maps.tiles.MiniTileTexture.Type;
import com.gmail.lJuanGBMinecraft.antique_maps.util.Direction;

/**
 * Represents the 24 different kinds of MiniTileTexture possible
 * 
 * @author lJuanGB
 */
public class TileNoncontextualTexture {

	private final MiniTileTexture[][] minis = new MiniTileTexture[4][6];

	/**
	 * Creates a TileNoncontextualTexture by using a 32x48 image.
	 * 
	 * @param image the image
	 * @param colorMap the mapping of sRGB to bytes that can be used to paint
	 * a minecraft map
	 * @throws IOException
	 */
	public TileNoncontextualTexture(File image, Function<Color, Byte> colorMap) throws IOException
	{
		BufferedImage bi = ImageIO.read( image );
		
		Validate.isTrue(bi.getHeight() * bi.getWidth() == 32*48,
				"Image ("+image.getPath()+") must be of size 32x48");
		
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 6; j++)
			{
				byte[][] colors = new byte[8][8];
				
				for (int x = 0 ; x < 8; x++)
				{
					for (int y = 0; y < 8; y++)
					{
						Color color = new Color(bi.getRGB(i*8 + x, j*8 + y), true);
						colors[x][y] = colorMap.apply(color);
					}
				}
				
				minis[i][j] = new MiniTileTexture(colors);
			}
		}
	}
	
	/**
	 * Returns the appropriate texture for the given surroundings
	 * 
	 * @param presence a list that contains all Directions in which the same tile is present
	 * @return
	 */
	public TileTexture getTexture(List<Direction> presence)
	{
		Map<TileCorner, MiniTileTexture> corners = new HashMap<>();
		
		Map<TileCorner, MiniTileTexture.Type> test = new HashMap<>();
		
		for (TileCorner corner : TileCorner.values())
		{
			Direction diagonalDirection = Direction.combine(corner.horDirection, corner.verDirection);
			
			boolean horizontal = presence.contains( corner.horDirection );
			boolean vertical = presence.contains( corner.verDirection );
			boolean diagonal = presence.contains( diagonalDirection );
			
			Type type = Type.getType(horizontal, vertical, diagonal);
			corners.put(corner, getMiniTexture(corner, type));
			
			test.put(corner, type);
		}
		
		
		return new TileTexture(corners);
	}
	
	/**
	 * Gets the appropiate MiniTileTexture for the type and corner 
	 * 
	 * @param type
	 * @param index
	 * @return
	 */
	public MiniTileTexture getMiniTexture(TileCorner corner, Type type)
	{
		Point point = type.getCoordinates(corner);

		return minis[point.x][point.y];
	}
}
