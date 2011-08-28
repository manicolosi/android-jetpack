package com.manico.android_jetpack;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class AndroidJetpackActivity extends Activity
{
    private static final String TAG = "AndroidJetpackActivity";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Game game = new Game ();
        GameView gameView = new GameView(this, game);

        setContentView(gameView);
    }
}
