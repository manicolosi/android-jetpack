package com.manico.android_jetpack;

import android.graphics.Rect;
import android.util.Log;

public class Player
{
    private Level level;

    private Rect texture_bounds = new Rect(40, 0, 40 + 32, 48);
    private int x, y;

    private int width  = texture_bounds.width();
    private int height = texture_bounds.height();

    public Player(Level level, int x, int y)
    {
        this.level = level;

        // X,Y are in level coordinates; convert to pixels
        this.x = x * 32;
        this.y = (y+1) * 32 - height;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public Rect getTextureBounds() { return texture_bounds; }


    private int ACCELERATION = 25 * 32;
    private int MAX_VELOCITY = 5  * 32;
    private double velocity_x = 0;

    public void tick(double dtime, boolean left, boolean right)
    {
        if (left) {
            velocity_x -= ACCELERATION * dtime;
        } else if (right) {
            velocity_x += ACCELERATION * dtime;
        } else {
            // FIXME: Highly ugly and inefficient.
            if (velocity_x < 0) {
                velocity_x += ACCELERATION * dtime;
                velocity_x = Math.min(0, velocity_x);
            } else if (velocity_x > 0) {
                velocity_x -= ACCELERATION * dtime;
                velocity_x = Math.max(0, velocity_x);
            }
        }

        Math.min(Math.max(velocity_x, - MAX_VELOCITY), MAX_VELOCITY);

        x += (int)(velocity_x * dtime);

        resolveHorizCollisions();
    }

    private void resolveHorizCollisions() {
        int cx = x + (width / 2);
        int cy = y + (height / 2);

        int left   = (cx - (width / 2) + 5) /  32;
        int right  = (cx + (width / 2) - 5) /  32;
        int top    = (cy - (height / 2)) / 32;
        int bottom = (cy + (height / 2) - 2) / 32;

        if (Tile.isWall(level.getTile(left, top)) || Tile.isWall(level.getTile(left, bottom))) {
            Log.d("AndroidJetpack", "Left Side");
            x = (left+1) * 32 - 5;
        }
        if (Tile.isWall(level.getTile(right, top)) || Tile.isWall(level.getTile(right, bottom))) {
            Log.d("AndroidJetpack", "Right Side");
            x = (right-1) * 32 + 5 - 1;
        }
    }
}
