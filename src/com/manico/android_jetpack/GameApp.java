package com.manico.android_jetpack;

import android.app.Application;
import android.content.Context;
import android.util.Log;

public class GameApp extends Application
{
    private static Context context;

    @Override
    public void onCreate()
    {
        super.onCreate();

        GameApp.context = getApplicationContext();

        Log.d("AndroidJetpack", "GameApp()");
    }

    public static Context getContext()
    {
        return context;
    }
}
