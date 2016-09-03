package com.byterium.geneticprogramming.probability;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class WeightedDie<T> implements Die<T> {
    private final Random randomGenerator;
    private final List<WeightedDiceSide> sides;
    private final double weightSum;

    public WeightedDie(Random randomGenerator, Map<T, Number> sidesWithWeights)
            throws IllegalArgumentException {
        if (sidesWithWeights.size() == 0) {
            throw new IllegalArgumentException("sidesWithWeights cannot be empty");
        }

        if (sidesWithWeights.values().stream().mapToDouble(Number::doubleValue).min().getAsDouble() < 0) {
            throw new IllegalArgumentException("sidesWithWeights may not contain negative values");
        }

        this.weightSum = sidesWithWeights.values().stream().mapToDouble(Number::doubleValue).sum();
        this.sides = new ArrayList<>();

        double min = 0;

        for(Map.Entry<T, Number> entry : sidesWithWeights.entrySet()) {
            WeightedDiceSide side = new WeightedDiceSide();
            side.value = entry.getKey();
            side.lowerIncl = min;
            side.upperExcl = min + entry.getValue().doubleValue();
            min += entry.getValue().doubleValue();
            sides.add(side);
        }

        this.randomGenerator = randomGenerator;
    }

    @Override
    public T roll() {
        double roll = randomGenerator.nextDouble() * weightSum;

        WeightedDiceSide selectedSide = sides.stream()
                .filter(side -> side.lowerIncl <= roll && side.upperExcl > roll).findFirst().get();

        return selectedSide.value;
    }

    private class WeightedDiceSide {
        T value;
        double lowerIncl;
        double upperExcl;
    }
}

