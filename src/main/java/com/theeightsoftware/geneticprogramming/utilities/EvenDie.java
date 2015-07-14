package com.theeightsoftware.geneticprogramming.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EvenDie<T> implements Die<T> {
    private final Random randomGenerator;
    private final List<T> sides;

    public EvenDie(Random randomGenerator, List<T> sides) throws IllegalArgumentException {
        if (sides.size() == 0) {
            throw new IllegalArgumentException("sides cannot be empty");
        }

        this.randomGenerator = randomGenerator;
        this.sides = new ArrayList<>(sides);
    }

    @Override
    public T roll() {
        int roll = randomGenerator.nextInt(sides.size());
        return sides.get(roll);
    }
}
