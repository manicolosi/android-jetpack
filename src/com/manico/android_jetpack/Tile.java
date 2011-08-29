package com.manico.android_jetpack;

import android.graphics.Point;
import android.graphics.Rect;

import java.util.HashMap;

public class Tile
{
    private static int TILE_SIZE = 16;
    private static final HashMap<Integer, Point> tiles = new HashMap<Integer, Point>();

    static {
        tiles.put(0xffff0000, new Point(0, 0));
    }

    public static Point getTopLeft(int tile)
    {
        return tiles.get(tile);
    }

    public static boolean isWall(int tile)
    {
        return tile == 0xffff0000;
    }

}
