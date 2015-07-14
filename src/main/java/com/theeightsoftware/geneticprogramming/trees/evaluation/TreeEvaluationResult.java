package com.theeightsoftware.geneticprogramming.trees.evaluation;

import com.theeightsoftware.geneticprogramming.trees.Tree;

import java.util.List;
import java.util.stream.DoubleStream;

public class TreeEvaluationResult {
    private final List<InputResult> results;
    private final Tree tree;

    public TreeEvaluationResult(Tree tree, List<InputResult> results) {
        this.tree = tree;
        this.results = results;
    }

    public Tree getTree() {
        return tree;
    }

    public List<InputResult> getResults() {
        return results;
    }

    private DoubleStream getFiniteActualsStream() {
        return results.stream()
                .mapToDouble(r -> r.getActual().doubleValue())
                .filter(Double::isFinite);
    }

    public double getVariance() {
        double mean = getFiniteActualsStream().average().getAsDouble();
        long count = getFiniteActualsStream().count();
        DoubleStream squaredDiffs = getFiniteActualsStream()
                .map(r -> Math.pow(r - mean, 2));
        return squaredDiffs.sum() / count;
    }

    public double getTotalDifference() {
        return results.stream()
                .filter(r -> Double.isFinite(r.getActual().doubleValue()))
                .mapToDouble(r -> Math.abs(r.getActual().doubleValue() - r.getExpected().doubleValue()))
                .sum();
    }
}

