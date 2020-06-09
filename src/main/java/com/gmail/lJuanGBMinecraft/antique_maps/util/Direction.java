package com.gmail.lJuanGBMinecraft.antique_maps.util;

/**
 * Represents a cardinal direction with the direction vector
 * (corresponding to minecraft coordinates).
 * 
 * @author lJuanGB
 */
public enum Direction 
{	
	NORTH(0, -1, Type.VERTICAL),
	EAST(1,0, Type.HORIZONTAL),
	SOUTH(0,1, Type.VERTICAL),
	WEST(-1,0, Type.HORIZONTAL),
	
	NW(NORTH, WEST),
	NE(NORTH, EAST),
	SE(SOUTH, EAST),
	SW(SOUTH, WEST),
	;
	
	public final int x;
	public final int y;
	public final Type type;
	
	private Direction(int x, int y, Type type)
	{
		this.x = x;
		this.y = y;
		this.type = type;
	}
	
	private Direction(Direction d1, Direction d2)
	{
		this.x = d1.x + d2.x;
		this.y = d1.y + d2.y;
		this.type = Type.DIAGONAL;
	}
	
	/**
	 * Combines the two directions (NORTH + WEST = NORTH-WEST)
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static Direction combine(Direction d1, Direction d2)
	{
		int x = d1.x + d2.x;
		int y = d1.y + d2.y;
		
		for (Direction dir : Direction.values())
		{
			if (dir.x == x && dir.y == y) return dir;
		}
		
		throw new IllegalArgumentException("One direction must be vertical and the other horizontal");
	}
	
	public enum Type {HORIZONTAL, VERTICAL, DIAGONAL;}
}
