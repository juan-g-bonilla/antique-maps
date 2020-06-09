package com.gmail.lJuanGBMinecraft.antique_maps.tiles;

import com.gmail.lJuanGBMinecraft.antique_maps.util.Direction;

/**
 * Represents one of the 4 corners in a tile
 * 
 * @author lJuanGB
 */
public enum TileCorner
{
	A(0, 0, Direction.WEST, Direction.NORTH),
	B(1, 0, Direction.EAST, Direction.NORTH),
	C(0, 1, Direction.WEST, Direction.SOUTH),
	D(1, 1, Direction.EAST, Direction.SOUTH);
	
	// Represent the position in the tile of the minitile
	// A Tile is composed of 4 minitiles in the shape:
	//         A B
	//         C D
	public final int x;
	public final int y;
	
	// Represent the outer directions of each tile. For example, since
	// A is upper left corner it's facing WEST and NORTH.
	public final Direction horDirection;
	public final Direction verDirection;
	

	private TileCorner(int x, int y, Direction horDirection, Direction verDirection) {
		this.x = x;
		this.y = y;
		this.horDirection = horDirection;
		this.verDirection = verDirection;
	}
}
