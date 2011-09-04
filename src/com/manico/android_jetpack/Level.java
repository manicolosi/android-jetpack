package com.manico.android_jetpack;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.InputStream;
import java.io.IOException;

public class Level
{
    private int map[][];
    private int width;
    private int height;
    private Player player;

    public Level(String name)
    {
        try {
            AssetManager assets = GameApp.getContext().getAssets();
            InputStream stream = assets.open(name + ".png");
            Bitmap bitmap = BitmapFactory.decodeStream(stream);
            stream.close();

            width = bitmap.getWidth();
            height = bitmap.getHeight();

            map = new int[width][height];

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int tile = bitmap.getPixel(x, y);
                    map[x][y] = tile;

                    if (tile == 0xff00ff00) {
                        player = new Player(this, x, y);
                    }
                }
            }

        } catch (IOException e) {
            Log.d("AndroidJetpack", "IOException occurred while loading level: " + name);
        }
    }

    public int getTile(int x, int y)
    {
        return map[x][y];
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public Player getPlayer() { return player; }
}
