package com.mengstudios.galdulesfate.entity.tile;

import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.screen.PlayScreen;

public class GrassTile extends Tile {

    public GrassTile(PlayScreen playScreen, float x, float y) {
        super(playScreen);
        setRegion(Assets.GRASS_TILE);
        setBounds(x, y, Assets.GRASS_TILE.getWidth(), Assets.GRASS_TILE.getHeight());
    }

}
