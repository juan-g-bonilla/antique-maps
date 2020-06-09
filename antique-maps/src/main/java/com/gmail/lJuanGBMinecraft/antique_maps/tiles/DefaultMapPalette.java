package com.gmail.lJuanGBMinecraft.antique_maps.tiles;

import java.awt.Color;

/**
 * Implementation of MapPalette that is fast and returns best color fit
 * for the default textures in the Antique Atlas mod (default textues).
 * Does not provide faithful results for any color that is not the color
 * 0x330000 with varying alpha.
 * 
 * @author lJuanGB
 */
public class DefaultMapPalette{

	public static byte matchColor(Color color) {

		double a = color.getAlpha() / 255.0;
		
		int i = 0;
		
		if (a < 0.27)
		{
			i = 0;
		}
		else if (a < 0.45)
		{
			i = 42;
		}
		else if (a < 0.57)
		{
			i = 41;
		}
		else if (a < 0.69)
		{
			i = 40;
		}
		else if (a < 0.75)
		{
			i = 136;
		}
		else if (a < 0.82)
		{
			i = 171;
		}
		else if (a < 0.92)
		{
			i = 203;
		}
		else
		{
			i = 143;
		}
		
		return (byte) (i < 128 ? i : -129 + (i - 127));
	}
}
