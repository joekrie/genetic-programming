package com.byterium.geneticprogramming.trees.nodes;

import com.byterium.geneticprogramming.probability.WeightedCoin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MutationCoin extends WeightedCoin {
    @Autowired
    public MutationCoin(Random randomGenerator, Number chanceOfMutation) throws IllegalArgumentException {
        super(randomGenerator, chanceOfMutation);
    }
}
