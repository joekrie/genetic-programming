package com.theeightsoftware.geneticprogramming.trees.nodes.evaluators;

import com.theeightsoftware.geneticprogramming.trees.nodes.Node;
import com.theeightsoftware.geneticprogramming.trees.nodes.evaluators.operators.Operator;
import com.theeightsoftware.geneticprogramming.utilities.Coin;
import com.theeightsoftware.geneticprogramming.utilities.Die;

import java.util.ArrayList;
import java.util.List;

public class OperatorNodeEvaluator implements NodeEvaluator {
    private Operator operator;
    private Node leftNode;
    private Node rightNode;
    private Coin coin;
    private Die<Operator> operatorDie;

    public OperatorNodeEvaluator(Operator operator, Node leftNode, Node rightNode,
                                  Coin coin, Die<Operator> operatorDie) {
        this.operator = operator;
        this.coin = coin;
        this.operatorDie = operatorDie;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public OperatorNodeEvaluator(OperatorNodeEvaluator nodeEvaluator) {
        this.operator = nodeEvaluator.operator;
        this.coin = nodeEvaluator.coin;
        this.operatorDie = nodeEvaluator.operatorDie;
        this.leftNode = nodeEvaluator.leftNode.duplicate();
        this.rightNode = nodeEvaluator.rightNode.duplicate();
    }

    @Override
    public Number evaluate(Number x) {
        Number leftResult = leftNode.evaluate(x);
        Number rightResult = rightNode.evaluate(x);
        return operator.evaluate(leftResult, rightResult);
    }

    @Override
    public String print() {
        String leftPrinted = leftNode.print();
        String rightPrinted = rightNode.print();
        return "(" + leftPrinted + " " + operator.getSymbol() + " " + rightPrinted + ")";
    }

    @Override
    public void signalMutate() {
        if (coin.flip()) {
            operator = operatorDie.roll();
        }

        leftNode.signalMutate();
        rightNode.signalMutate();
    }

    @Override
    public List<Node> getChildNodes() {
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.addAll(leftNode.getChildNodes());
        nodes.addAll(rightNode.getChildNodes());
        return nodes;
    }

    @Override
    public OperatorNodeEvaluator duplicate() {
        return new OperatorNodeEvaluator(this);
    }
}

