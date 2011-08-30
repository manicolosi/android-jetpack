package com.manico.android_jetpack;

import android.graphics.Rect;

import java.util.HashMap;

public class Tile
{
    private static int TILE_SIZE = 16;
    private static final HashMap<Integer, Rect> tiles = new HashMap<Integer, Rect>();

    static
    {
        tiles.put(0xffff0000, new Rect(0, 0, 16, 16));
    }

    public static Rect getTextureBounds(int tile)
    {
        return tiles.get(tile);
    }

    public static boolean isWall(int tile)
    {
        return tile == 0xffff0000;
    }

}
