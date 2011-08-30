package com.manico.android_jetpack;

import android.graphics.Rect;

public class Player
{
    private Rect texture_bounds = new Rect(40, 0, 40 + 32, 48);
    private int x, y;

    public Player(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public Rect getTextureBounds() { return texture_bounds; }

    public void tick(boolean left, boolean right)
    {
        if (left) {
            this.x--;
        } else if (right) {
            this.x++;
        }
    }
}
