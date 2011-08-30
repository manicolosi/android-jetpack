package com.manico.android_jetpack;

import android.content.Context;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends SurfaceView implements SurfaceHolder.Callback
{
    private static final String TAG = "GameView";

    private GameThread thread;
    private Game game;

    public GameView(Context context, Game game)
    {
        super(context);

        this.game = game;

        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
    }

    public void surfaceCreated(SurfaceHolder holder)
    {
        thread = new GameThread(holder, this, game);
    }

    public void surfaceChanged(SurfaceHolder holder, int format,
            int width, int height)
    {
        thread.setRunning(true);
        thread.start();
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        thread.setRunning(false);

        boolean retry = true;
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }

        Log.d(TAG, "surfaceDestroyed(): " + thread.getState());
    }
}
