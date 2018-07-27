package com.mengstudios.galdulesfate;

public class MathExtended {
    public static float getFloatBetween(float min, float max) {
        return (float)(min + Math.random() * (max - min));
    }
}
