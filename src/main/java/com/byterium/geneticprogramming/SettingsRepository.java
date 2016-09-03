package com.byterium.geneticprogramming;

import java.util.HashMap;
import java.util.Map;

public class SettingsRepository {
    public static Map<Number, Number> getTrainingData() {
        HashMap<Number, Number> data = new HashMap<>();
        data.put(-5, -1.5f);
        data.put(-4, -1.33333333333333f);
        data.put(-3, -1.125f);
        data.put(-2, -0.857142857142857f);
        data.put(-1, -0.5f);
        data.put(0, 0f);
        data.put(1, 0.75f);
        data.put(2, 2f);
        data.put(3, 4.5f);
        data.put(4, 12f);
        data.put(6, -18f);
        data.put(7, -10.5f);
        data.put(8, -8f);
        data.put(9, -6.75f);
        data.put(10, -6f);
        data.put(11, -5.5f);
        data.put(12, -5.14285714285714f);
        data.put(13, -4.875f);
        data.put(14, -4.66666666666667f);
        data.put(15, -4.5f);
        data.put(16, -4.36363636363636f);
        data.put(17, -4.25f);
        data.put(18, -4.15384615384615f);
        data.put(19, -4.07142857142857f);
        data.put(20, -4f);
        return data;
    }
}
