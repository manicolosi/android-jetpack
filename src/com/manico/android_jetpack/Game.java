package com.manico.android_jetpack;

public class Game
{
    private Level level;

    public Game()
    {
        level = new Level("level1");
    }

    public Level getLevel() { return level; }
    public Player getPlayer() { return level.getPlayer(); }
}
