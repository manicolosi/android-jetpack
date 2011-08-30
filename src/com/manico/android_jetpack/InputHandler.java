package com.manico.android_jetpack;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;

import java.util.HashMap;

public class InputHandler implements OnKeyListener
{
    boolean isLeftPressed  = false;
    boolean isRightPressed = false;

    public InputHandler(View view)
    {
        Log.d("AndroidJetpack", "InputHandler()");

        view.setOnKeyListener(this);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
    }

    private HashMap<Integer, Boolean> keyboard_state = new HashMap<Integer, Boolean>();

    @Override
    public boolean onKey(View v, int keycode, KeyEvent event)
    {
        keyboard_state.put(keycode, event.getAction() == KeyEvent.ACTION_DOWN);

        return false;
    }

    public boolean isKeyDown(int keycode)
    {
        return keyboard_state.containsKey(keycode) && keyboard_state.get(keycode);
    }
}
