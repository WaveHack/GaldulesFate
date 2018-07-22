package com.mengstudios.galdulesfate.hud;

public class AnvilUi extends Ui {
    private boolean justShown;

    public AnvilUi(Hud hud) {
        super(hud);
    }

    @Override
    public void update(float delta) {
        justShown = false;
    }

    @Override
    public void touchDown(int screenX, int screenY, int pointer, int button) {
        justShown = true;
    }

    @Override
    public void show() {
        super.show();
        justShown = true;
    }
}
