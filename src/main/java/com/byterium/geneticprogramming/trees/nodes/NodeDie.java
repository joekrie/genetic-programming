package com.byterium.geneticprogramming.trees.nodes;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

import com.byterium.geneticprogramming.trees.evaluators.NodeEvaluatorFactory;
import com.byterium.geneticprogramming.trees.evaluators.OperatorNodeEvaluator;
import com.byterium.geneticprogramming.trees.evaluators.VariableNodeEvaluator;
import com.byterium.geneticprogramming.probability.WeightedDie;
import com.byterium.geneticprogramming.trees.evaluators.ConstantNodeEvaluator;
import com.byterium.geneticprogramming.probability.Die;

public class NodeDie implements Die<Node> {
    private final WeightedDie<Supplier<Node>> nodeDie;
    private final int additionalDepthAllowed;
    private final NodeEvaluatorFactory nodeEvaluatorFactory;

    public NodeDie(int additionalDepthAllowed, Random randomGenerator,
                   Number chanceOfConstantNode, Number chanceOfVariableNode, Number chanceOfOperatorNode,
                   NodeEvaluatorFactory nodeEvaluatorFactory) {
        this.additionalDepthAllowed = additionalDepthAllowed;
        this.nodeEvaluatorFactory = nodeEvaluatorFactory;

        Map<Supplier<Node>, Number> diceSides = new HashMap<>(3);
        diceSides.put(this::generateConstantNode, chanceOfConstantNode);
        diceSides.put(this::generateVariableNode, chanceOfVariableNode);

        if (additionalDepthAllowed > 0) {
            diceSides.put(this::generateOperatorNode, chanceOfOperatorNode);
        }

        nodeDie = new WeightedDie<>(randomGenerator, diceSides);
    }

    @Override
    public Node roll() {
        return nodeDie.roll().get();
    }

    private Node generateConstantNode() {
        ConstantNodeEvaluator evaluator = nodeEvaluatorFactory.createConstantNodeEvaluator();
        return new Node(evaluator);
    }

    private Node generateVariableNode() {
        VariableNodeEvaluator evaluator = nodeEvaluatorFactory.createVariableNodeEvaluator();
        return new Node(evaluator);
    }

    private Node generateOperatorNode() {
        OperatorNodeEvaluator operatorNodeEvaluator = nodeEvaluatorFactory
                .createOperatorNodeEvaluator(additionalDepthAllowed - 1);
        return new Node(operatorNodeEvaluator);
    }
}
