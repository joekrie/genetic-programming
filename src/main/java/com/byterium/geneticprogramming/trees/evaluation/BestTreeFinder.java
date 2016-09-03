package com.byterium.geneticprogramming.trees.evaluation;

import com.byterium.geneticprogramming.config.BestTreeFinderConfig;
import com.byterium.geneticprogramming.probability.Coin;
import com.byterium.geneticprogramming.trees.Tree;
import com.byterium.geneticprogramming.trees.TreeGenerator;
import com.byterium.geneticprogramming.probability.Die;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BestTreeFinder {
    private final BestTreeFinderConfig bestTreeFinderConfig;
    private final TreeGenerator treeGenerator;
    private final Coin mutationCoin;
    private final Coin crossoverCoin;
    private final Function<List<Tree>, Die<Tree>> treeDieCreator;

    public BestTreeFinder(BestTreeFinderConfig bestTreeFinderConfig, TreeGenerator treeGenerator, Coin mutationCoin,
                          Coin crossoverCoin, Function<List<Tree>, Die<Tree>> treeDieCreator) {
        this.bestTreeFinderConfig = bestTreeFinderConfig;
        this.treeGenerator = treeGenerator;
        this.mutationCoin = mutationCoin;
        this.crossoverCoin = crossoverCoin;
        this.treeDieCreator = treeDieCreator;
    }

    public Tree findBestTree(List<Input> inputs) {
        List<Tree> initialTrees = treeGenerator.generateTrees(bestTreeFinderConfig.getInitialTreeCount());
        IterationResult iterationResult = iterate(initialTrees, inputs);

        double prevDiff = Double.MAX_VALUE;
        double currentDiff = iterationResult.bestResult.getTotalDifference();

        while (Math.abs(prevDiff - currentDiff) > bestTreeFinderConfig.getDiffThreshold()) {
            iterationResult = iterate(iterationResult.newTrees, inputs);
        }

        return iterationResult.bestResult.getTree();
    }

    private IterationResult iterate(List<Tree> trees, List<Input> inputs) {
        List<Tree> treesToEval = new ArrayList<>(trees);
        treesToEval.addAll(mutate(trees));
        treesToEval.addAll(crossover(trees));
        int numOfTrees = treesToEval.size();

        List<TreeEvaluationResult> results = treesToEval.stream()
                .map(tree -> tree.evaluate(inputs))
                .sorted(Comparator.comparing(TreeEvaluationResult::getTotalDifference))
                .limit((long) (numOfTrees * bestTreeFinderConfig.getPercentToSelectEachGeneration()))
                .collect(Collectors.toList());

        TreeEvaluationResult bestResult = results.stream()
                .max(Comparator.comparing(TreeEvaluationResult::getTotalDifference))
                .get();

        List<Tree> nextTrees = results.stream()
                .map(TreeEvaluationResult::getTree)
                .collect(Collectors.toList());

        return new IterationResult(nextTrees, bestResult);
    }

    private class IterationResult {
        List<Tree> newTrees;
        TreeEvaluationResult bestResult;

        IterationResult(List<Tree> newTrees, TreeEvaluationResult bestResult) {
            this.newTrees = newTrees;
            this.bestResult = bestResult;
        }
    }

    private List<Tree> mutate(List<Tree> trees) {
        return trees.stream()
                .filter(tree -> mutationCoin.flip())
                .map(Tree::mutate)
                .collect(Collectors.toList());
    }

    private List<Tree> crossover(List<Tree> trees) {
        List<Tree> treesToCrossover = trees.stream()
                .filter(tree -> crossoverCoin.flip())
                .collect(Collectors.toList());

        int iterations = treesToCrossover.size() / 2;
        List<Tree> newTrees = new ArrayList<>(iterations);

        for (int i = 0; i < iterations; i++) {
            Die<Tree> treeDie1 = treeDieCreator.apply(treesToCrossover);
            Tree tree1 = treeDie1.roll();
            treesToCrossover.remove(tree1);

            Die<Tree> treeDie2 = treeDieCreator.apply(treesToCrossover);
            Tree tree2 = treeDie2.roll();
            treesToCrossover.remove(tree2);

            Tree newTree = tree1.crossover(tree2);
            newTrees.add(newTree);
        }

        return newTrees;
    }
}
