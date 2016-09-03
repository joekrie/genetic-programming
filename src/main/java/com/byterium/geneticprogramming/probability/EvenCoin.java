package com.byterium.geneticprogramming.probability;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class EvenCoin extends WeightedCoin {
    @Autowired
    public EvenCoin(Random randomGenerator) {
        super(randomGenerator, 0.5);
    }
}
