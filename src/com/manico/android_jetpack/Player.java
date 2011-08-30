package com.manico.android_jetpack;

import android.graphics.Rect;
import android.util.Log;

public class Player
{
    private Rect texture_bounds = new Rect(40, 0, 40 + 32, 48);
    private double x, y;

    public Player(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public Rect getTextureBounds() { return texture_bounds; }


    private double ACCELERATION = 25;
    private double MAX_VELOCITY = 10;
    private double velocity_x = 0;

    public void tick(double dtime, boolean left, boolean right)
    {
        if (left) {
            velocity_x -= ACCELERATION * dtime;
        } else if (right) {
            velocity_x += ACCELERATION * dtime;
        } else {
            if (velocity_x < 0) {
                velocity_x += ACCELERATION * dtime;
                velocity_x = Math.min(0, velocity_x);
            } else if (velocity_x > 0) {
                velocity_x -= ACCELERATION * dtime;
                velocity_x = Math.max(0, velocity_x);
            }
        }

        // velocity_x = Math.clip(-5, velocity_x, 5);
        if (velocity_x < 0 && velocity_x < - MAX_VELOCITY) velocity_x = - MAX_VELOCITY;
        if (velocity_x > 0 && velocity_x > MAX_VELOCITY)  velocity_x =   MAX_VELOCITY;

        x += velocity_x * dtime;
    }
}
