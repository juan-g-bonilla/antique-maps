package com.gmail.lJuanGBMinecraft.antique_maps.tiles;

import java.awt.Color;

public final class BrownMapPalette {

    private static Color c(int r, int g, int b) {
        return new Color(r, g, b);
    }

    private static double getDistance( Color c1, Color c2) {
        double rmean = (c1.getRed() + c2.getRed()) / 2.0;
        double r = c1.getRed() - c2.getRed();
        double g = c1.getGreen() - c2.getGreen();
        int b = c1.getBlue() - c2.getBlue();
        double weightR = 2 + rmean / 256.0;
        double weightG = 4.0;
        double weightB = 2 + (255 - rmean) / 256.0;
        return weightR * r * r + weightG * g * g + weightB * b * b;
    }

    static final Color[] colors = {
    	null, null, null, null,
        //c(0, 0, 0), c(0, 0, 0), c(0, 0, 0), c(0, 0, 0),
        null, null, null, null,
        null, null, null, null,
        null, null, null, null,
        null, null, null, null,
        null, null, null, null,
        null, null, null, null,
        null, null, null, null,
        null, null, null, null,
        null, null, null, null,
        c(106, 76, 54), c(130, 94, 66), c(151, 109, 77), c(79, 57, 40),
        null, null, null, null,
        null, null, null, null,
        null, c(123, 102, 62), c(143, 119, 72), c(75, 63, 38),
        null, null, null, null,
        null, null, null, null,
        null, null, null, null,
        null, null, null, null,
        null, null, null, null,
        null, null, null, null,
        null, null, null, null,
        null, null, null, null,
        null, null, null, null,
        null, null, null, null,
        null, null, null, null,
        null, null, null, null,
        c(72, 53, 36), c(88, 65, 44), c(102, 76, 51), c(54, 40, 27),
        null, null, null, null,
        null, null, null, null,
        null, null, null, null,
        null, null, null, null,
        null, null, null, null,
        null, null, null, null,
        null, null, null, null,
        c(91, 60, 34), c(111, 74, 42), c(129, 86, 49), c(68, 45, 25),
        null, null, null, null,
        null, null, null, null,
        null, null, null, null,
        null, null, null, null,
        null, null, null, null,
        null, null, null, null,
        null, null, null, null,
        null, null, null, null,
        c(40, 28, 24), c(49, 35, 30), c(57, 41, 35), c(30, 21, 18),
        c(95, 75, 69), c(116, 92, 84), c(135, 107, 98), c(71, 56, 51),
        null, null, null, null,
        null, null, null, null,
        null, null, null, null,
        c(53, 35, 24), c(65, 43, 30), c(76, 50, 35), c(40, 26, 18),
        null, null, null, null,
        null, null, null, c(75, 31, 24),
        c(26, 15, 11), c(31, 18, 13), c(37, 22, 16), c(19, 11, 8)
    };

    /**
     * Get the index of the closest matching color in the palette to the given
     * color. Copied from Spigot, with very small variation.
     *
     * @param color The Color to match.
     * @return The index in the palette.
     */
    public static byte matchColor(Color color) {
        if (color.getAlpha() < 128) return 0;

        int index = 0;
        double best = -1;

        for (int i = 4; i < colors.length; i++) {
        	if (colors[i] == null) continue;
            double distance = getDistance(color, colors[i]);
            if (distance < best || best == -1) {
                best = distance;
                index = i;
            }
        }

        // Minecraft has 143 colors, some of which have negative byte representations
        return (byte) (index < 128 ? index : -129 + (index - 127));
    }
}