package com.byterium.geneticprogramming.trees.evaluators;

import com.byterium.geneticprogramming.trees.nodes.Node;
import com.byterium.geneticprogramming.trees.nodes.NodeDie;
import com.byterium.geneticprogramming.trees.nodes.NodeDieFactory;
import com.byterium.geneticprogramming.trees.operators.Operator;
import com.byterium.geneticprogramming.probability.Coin;
import com.byterium.geneticprogramming.probability.Die;
import org.springframework.stereotype.Component;

@Component
public class NodeEvaluatorFactory {
    private final int maxConstant;
    private final Die<Integer> intDie;
    private final Coin coin;
    private final Die<Operator> operatorDie;
    private final NodeDieFactory nodeDieFactory;

    public NodeEvaluatorFactory(int maxConstant, Die<Integer> intDie, Coin coin, Die<Operator> operatorDie,
                                NodeDieFactory nodeDieFactory) {
        this.maxConstant = maxConstant;
        this.intDie = intDie;
        this.coin = coin;
        this.operatorDie = operatorDie;
        this.nodeDieFactory = nodeDieFactory;
    }

    public ConstantNodeEvaluator createConstantNodeEvaluator() {
        int initialValue = intDie.roll();
        return new ConstantNodeEvaluator(maxConstant, initialValue, intDie, coin);
    }

    public OperatorNodeEvaluator createOperatorNodeEvaluator(int additionalDepthAllowed) {
        Operator operator = operatorDie.roll();

        NodeDie nodeDie = nodeDieFactory.createNodeDie(additionalDepthAllowed);
        Node leftNode = nodeDie.roll();
        Node rightNode = nodeDie.roll();

        return new OperatorNodeEvaluator(operator, leftNode, rightNode, coin, operatorDie, additionalDepthAllowed);
    }

    public VariableNodeEvaluator createVariableNodeEvaluator() {
        return new VariableNodeEvaluator();
    }
}
