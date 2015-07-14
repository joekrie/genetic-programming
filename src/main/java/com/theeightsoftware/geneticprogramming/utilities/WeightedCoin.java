package com.theeightsoftware.geneticprogramming.utilities;

import java.util.Random;

public class WeightedCoin implements Coin {
    private final Random randomGenerator;
    private final double probabilityOfTrue;

    public WeightedCoin(Random randomGenerator, Number probabilityOfTrue) throws IllegalArgumentException {
        if (probabilityOfTrue.doubleValue() < 0 || probabilityOfTrue.doubleValue() > 1) {
            throw new IllegalArgumentException("probabilityOfTrue must be between 0 and 1, inclusive");
        }
        
        this.randomGenerator = randomGenerator;
        this.probabilityOfTrue = probabilityOfTrue.doubleValue();
    }

    public boolean flip(){
        return randomGenerator.nextDouble() <= probabilityOfTrue;
    }
}

