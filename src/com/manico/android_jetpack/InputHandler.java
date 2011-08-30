package com.manico.android_jetpack;

import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;

import java.util.HashMap;

public class InputHandler implements OnKeyListener, OnTouchListener
{
    boolean isLeftPressed  = false;
    boolean isRightPressed = false;

    public InputHandler(View view)
    {
        view.setOnKeyListener(this);
        view.setOnTouchListener(this);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
    }

    private HashMap<Integer, Boolean> keyboard_state = new HashMap<Integer, Boolean>();

    // FIXME: Return whether key was handled
    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            event.setAction(MotionEvent.ACTION_DOWN);
            keyboard_state.put(KeyEvent.KEYCODE_DPAD_LEFT, false);
            keyboard_state.put(KeyEvent.KEYCODE_DPAD_RIGHT, false);
        }

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int keycode = (int)event.getX() < (v.getWidth() / 2) ?
                        KeyEvent.KEYCODE_DPAD_LEFT : KeyEvent.KEYCODE_DPAD_RIGHT;
            keyboard_state.put(keycode, true);
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            keyboard_state.put(KeyEvent.KEYCODE_DPAD_LEFT, false);
            keyboard_state.put(KeyEvent.KEYCODE_DPAD_RIGHT, false);
        }

        return true;
    }

    // FIXME: Return whether key was handled
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
