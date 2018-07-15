package com.mengstudios.galdulesfate.entity.tile;

import com.mengstudios.galdulesfate.entity.Entity;
import com.mengstudios.galdulesfate.screen.PlayScreen;

public class Tile extends Entity {

    public Tile(PlayScreen playScreen) {
        super(playScreen);
    }

    public Tile(PlayScreen playScreen, float x, float y) {
        super(playScreen, x, y);
    }
}
