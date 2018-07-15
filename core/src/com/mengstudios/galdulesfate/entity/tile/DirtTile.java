package com.mengstudios.galdulesfate.entity.tile;

import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.screen.PlayScreen;

public class DirtTile extends Tile {

    public DirtTile(PlayScreen playScreen, float x, float y) {
        super(playScreen);
        setRegion(Assets.DIRT_TILE);
        setBounds(x, y, Assets.DIRT_TILE.getWidth(), Assets.DIRT_TILE.getHeight());
    }

}
