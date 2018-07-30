package com.mengstudios.galdulesfate.entity.mob;

import com.mengstudios.galdulesfate.MathExtended;

public class Ai {
    private Mob mob;
    private int id;

    private float timerMax;

    public Ai(Mob mob) {
        this.mob = mob;

        if(mob instanceof Pig || mob instanceof Cow) {
            id = 1;
        }
    }

    public void update(float delta) {
        switch (id) {
            case 1:
                if(mob.stateTimer > timerMax) {
                    if(mob.state == Mob.State.STANDING) {
                        if(Math.random() < 0.5f) {
                            mob.setVelocityX(100);
                        } else {
                            mob.setVelocityX(-100);
                        }
                        timerMax = MathExtended.getFloatBetween(0.1f, 1) * 5;
                    } else {
                        mob.state = Mob.State.STANDING;
                        mob.setVelocityX(0);
                        timerMax = MathExtended.getFloatBetween(0.1f, 1) * 5;
                    }
                }
        }
    }
}
