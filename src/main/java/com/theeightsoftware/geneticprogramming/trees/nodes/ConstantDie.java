package com.theeightsoftware.geneticprogramming.trees.nodes;

import com.theeightsoftware.geneticprogramming.utilities.IntegerDie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ConstantDie extends IntegerDie {
    @Autowired
    public ConstantDie(int maxConstant, Random randomGenerator) {
        super(maxConstant, randomGenerator);
    }
}
