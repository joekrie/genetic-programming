package com.byterium.geneticprogramming.trees.nodes;

import com.byterium.geneticprogramming.trees.evaluators.NodeEvaluator;

import java.util.List;

public class Node {
    private NodeEvaluator evaluator;

    public Node(NodeEvaluator evaluator) {
        this.evaluator = evaluator;
    }

    public void replace(NodeEvaluator newNode) {
        evaluator = newNode;
    }

    public Number evaluate(Number x) {
        return evaluator.evaluate(x);
    }

    public String print() {
        return evaluator.print();
    }

    public void signalMutate() {
        evaluator.mutate();
    }

    public List<Node> getChildNodes() {
        return evaluator.getChildNodes();
    }

    public NodeEvaluator getNodeEvaluator(){
        return evaluator;
    }

    public Node duplicate() {
        return new Node(evaluator.duplicate());
    }
}
