package com.theeightsoftware.geneticprogramming.trees.nodes.evaluators;

import com.theeightsoftware.geneticprogramming.trees.nodes.Node;

import java.util.ArrayList;
import java.util.List;

public class VariableNodeEvaluator implements NodeEvaluator {
    @Override
    public Number evaluate(Number x) {
        return x;
    }

    @Override
    public String print() {
        return "x";
    }

    @Override
    public void signalMutate() {}

    @Override
    public List<Node> getChildNodes() {
        return new ArrayList<>(0);
    }

    @Override
    public VariableNodeEvaluator duplicate() {
        return new VariableNodeEvaluator();
    }
}
