package com.manico.android_jetpack;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import java.util.ArrayList;

public class GameThread extends Thread
{
    private static final String TAG = "GameThread";

    private SurfaceHolder mHolder;
    private Game mGame;

    private boolean mRunning = false;

    private Paint mBgPaint;
    private Paint mTextPaint;
    private Paint mWallPaint;

    private int mFps;

    public GameThread(SurfaceHolder holder, Game game)
    {
        mHolder = holder;
        mGame = game;

        mBgPaint = new Paint();
        mBgPaint.setColor(0xff000000);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(0xff00ff00);
        mTextPaint.setTextSize(15);

        mWallPaint = new Paint();
        mWallPaint.setColor(0xffff0000);
    }

    @Override
    public void run()
    {
        long current = System.currentTimeMillis();
        long old;
        double dtime;

        long last_fps_update = current;
        int frames = 0;

        Canvas canvas;

        while(mRunning) {
            old = current;
            current = System.currentTimeMillis();
            dtime = (current - old) / 1000.0;

            if(current - last_fps_update > 1000) {
                mFps = frames;
                frames = 0;
                last_fps_update = current;
            }
            frames++;

            canvas = null;
            try {
                canvas = mHolder.lockCanvas();
                synchronized (mHolder) {
                    updatePhysics(dtime);
                    render(canvas);
                }
            } finally {
                if (canvas != null) {
                    mHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    public void setRunning(boolean b)
    {
        mRunning = b;
    }

    public void updatePhysics(double dtime)
    {
    }

    public void render(Canvas canvas)
    {
        canvas.drawPaint(mBgPaint);

        renderLevel(canvas);

        canvas.drawText("FPS: " + mFps, 5, 15, mTextPaint);
    }

    private static int TILE_SIZE = 32;

    public void renderLevel(Canvas canvas)
    {
        Level lvl = mGame.getLevel();
        int lvl_w = lvl.getWidth();
        int lvl_h = lvl.getHeight();

        for (int x = 0; x < lvl_w; x++) {
            for (int y = 0; y < lvl_h; y++) {
                if (lvl.isWall(x, y)) {
                    int sx = x * TILE_SIZE;
                    int sy = y * TILE_SIZE;
                    canvas.drawRect(sx, sy, sx + TILE_SIZE, sy + TILE_SIZE, mWallPaint);
                }
            }
        }
    }
}
