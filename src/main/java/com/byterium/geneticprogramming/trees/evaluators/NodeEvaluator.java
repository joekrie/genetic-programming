package com.byterium.geneticprogramming.trees.evaluators;

import com.byterium.geneticprogramming.trees.nodes.Node;

import java.util.List;

public interface NodeEvaluator {
    Number evaluate(Number x);
    String print();
    void mutate();
    List<Node> getChildNodes();
    NodeEvaluator duplicate();
}

