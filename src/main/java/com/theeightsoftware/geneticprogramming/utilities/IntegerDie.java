package com.theeightsoftware.geneticprogramming.utilities;

import java.util.Random;

public class IntegerDie implements Die<Integer> {
    private final int max;
    private final Random randomGenerator;

    public IntegerDie(int max, Random randomGenerator) {
        this.max = max;
        this.randomGenerator = randomGenerator;
    }

    @Override
    public Integer roll() {
        return randomGenerator.nextInt(max) + 1;
    }
}
