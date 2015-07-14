package com.theeightsoftware.geneticprogramming.trees.nodes.evaluators;

import com.theeightsoftware.geneticprogramming.trees.nodes.Node;

import java.util.List;

public interface NodeEvaluator {
    Number evaluate(Number x);
    String print();
    void signalMutate();
    List<Node> getChildNodes();
    NodeEvaluator duplicate();
}

