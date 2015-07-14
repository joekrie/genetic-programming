package com.theeightsoftware.geneticprogramming.trees.nodes.evaluators;

import com.theeightsoftware.geneticprogramming.trees.nodes.Node;
import com.theeightsoftware.geneticprogramming.utilities.Coin;
import com.theeightsoftware.geneticprogramming.utilities.Die;

import java.util.ArrayList;
import java.util.List;

public class ConstantNodeEvaluator implements NodeEvaluator {
    private final int maxConstant;
    private final Die<Integer> intDie;
    private final Coin coin;

    private int currentConstant;

    public ConstantNodeEvaluator(int maxConstant, int startingConstant, Die<Integer> intDie, Coin coin) {
        this.maxConstant = maxConstant;
        this.currentConstant = startingConstant;
        this.intDie = intDie;
        this.coin = coin;
    }

    @Override
    public Number evaluate(Number x) {
        return currentConstant;
    }

    @Override
    public String print() {
        return Integer.toString(currentConstant);
    }

    @Override
    public void signalMutate() {
        if (coin.flip()) {
            currentConstant = intDie.roll();
        }
    }

    @Override
    public List<Node> getChildNodes() {
        return new ArrayList<>(0);
    }

    @Override
    public NodeEvaluator duplicate() {
        return new ConstantNodeEvaluator(maxConstant, currentConstant, intDie, coin);
    }
}

