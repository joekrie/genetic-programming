package com.theeightsoftware.geneticprogramming.trees.nodes;

import com.theeightsoftware.geneticprogramming.trees.TreeGeneratorConfig;
import com.theeightsoftware.geneticprogramming.trees.nodes.evaluators.ConstantNodeEvaluator;
import com.theeightsoftware.geneticprogramming.trees.nodes.evaluators.OperatorNodeEvaluator;
import com.theeightsoftware.geneticprogramming.trees.nodes.evaluators.VariableNodeEvaluator;
import com.theeightsoftware.geneticprogramming.trees.nodes.evaluators.operators.Operator;
import com.theeightsoftware.geneticprogramming.utilities.Coin;
import com.theeightsoftware.geneticprogramming.utilities.Die;
import com.theeightsoftware.geneticprogramming.utilities.WeightedDie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

@Component
public class NodeDie implements Die<Node> {
    private final WeightedDie<Supplier<Node>> nodeDie;
    private final Die<Operator> operatorDie;
    private final Die<Integer> intDie;
    private final Coin coin;
    private final int additionalDepthAllowed;
    private final TreeGeneratorConfig treeGeneratorConfig;
    private final Random randomGenerator;

    @Autowired
    public NodeDie(TreeGeneratorConfig treeGeneratorConfig, @Qualifier("maxDepthAllowed") int additionalDepthAllowed,
                   Random randomGenerator, Die<Operator> operatorDie, @Qualifier("constantDie") Die<Integer> intDie,
                   @Qualifier("mutationCoin") Coin coin) {
        this.operatorDie = operatorDie;
        this.intDie = intDie;
        this.coin = coin;
        this.additionalDepthAllowed = additionalDepthAllowed;
        this.treeGeneratorConfig = treeGeneratorConfig;
        this.randomGenerator = randomGenerator;

        Map<Supplier<Node>, Number> diceSides = new HashMap<>(3);
        diceSides.put(() -> generateConstantNode(treeGeneratorConfig.getMaxConstant()),
                treeGeneratorConfig.getChanceOfConstantNode());
        diceSides.put(this::generateVariableNode, treeGeneratorConfig.getChanceOfVariableNode());

        if (additionalDepthAllowed > 0) {
            diceSides.put(this::generateOperatorNode, treeGeneratorConfig.getChanceOfOperatorNode());
        }

        nodeDie = new WeightedDie<>(randomGenerator, diceSides);
    }

    @Override
    public Node roll() {
        return nodeDie.roll().get();
    }

    private Die<Node> createDieForChildNode() {
        int newDepthAllowed = additionalDepthAllowed - 1;
        return new NodeDie(treeGeneratorConfig, newDepthAllowed, randomGenerator, operatorDie, intDie, coin);
    }

    private Node generateConstantNode(int maxConstant) {
        int initial = intDie.roll();
        ConstantNodeEvaluator evaluator = new ConstantNodeEvaluator(maxConstant, initial, intDie, coin);
        return new Node(evaluator);
    }

    private Node generateVariableNode() {
        VariableNodeEvaluator evaluator = new VariableNodeEvaluator();
        return new Node(evaluator);
    }

    private Node generateOperatorNode() {
        Die<Node> newNodeDie = createDieForChildNode();
        Node left = newNodeDie.roll();
        Node right = newNodeDie.roll();
        Operator operator = operatorDie.roll();
        OperatorNodeEvaluator operatorNodeEvaluator = new OperatorNodeEvaluator(operator, left, right, coin,
                operatorDie);
        return new Node(operatorNodeEvaluator);
    }
}

