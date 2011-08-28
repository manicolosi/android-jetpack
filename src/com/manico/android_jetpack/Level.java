package com.manico.android_jetpack;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.InputStream;
import java.io.IOException;

public class Level
{
    private boolean map[][];
    private int width;
    private int height;

    public Level(String name)
    {
        try {
            AssetManager assets = GameApp.getContext().getAssets();
            InputStream stream = assets.open(name + ".png");
            Bitmap bitmap = BitmapFactory.decodeStream(stream);
            stream.close();

            width = bitmap.getWidth();
            height = bitmap.getHeight();

            map = new boolean[width][height];

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    map[x][y] = bitmap.getPixel(x, y) == 0xffff0000;
                }
            }

        } catch (IOException e) {
            Log.d("AndroidJetpack", "IOException occurred while load level: " + name);
        }
    }

    public boolean isWall(int x, int y)
    {
        return map[x][y];
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
