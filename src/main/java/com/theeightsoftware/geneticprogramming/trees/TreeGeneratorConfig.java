package com.theeightsoftware.geneticprogramming.trees;

import org.springframework.context.annotation.Configuration;

@Configuration
public class TreeGeneratorConfig {
    private final int maxConstant;
    private final int maxDepthAllowed;
    private final Number chanceOfConstantNode;
    private final Number chanceOfVariableNode;
    private final Number chanceOfOperatorNode;
    private final Number chanceOfMutation;

    public TreeGeneratorConfig(int maxConstant, Number chanceOfConstantNode, Number chanceOfVariableNode,
                               Number chanceOfOperatorNode, int maxDepthAllowed, Number chanceOfMutation) {
        this.maxConstant = maxConstant;
        this.chanceOfConstantNode = chanceOfConstantNode;
        this.chanceOfVariableNode = chanceOfVariableNode;
        this.chanceOfOperatorNode = chanceOfOperatorNode;
        this.maxDepthAllowed = maxDepthAllowed;
        this.chanceOfMutation = chanceOfMutation;
    }

    public int getMaxConstant() {
        return maxConstant;
    }

    public int getMaxDepthAllowed() {
        return maxDepthAllowed;
    }

    public Number getChanceOfConstantNode() {
        return chanceOfConstantNode;
    }

    public Number getChanceOfVariableNode() {
        return chanceOfVariableNode;
    }

    public Number getChanceOfOperatorNode() {
        return chanceOfOperatorNode;
    }

    public Number getChanceOfMutation() {
        return chanceOfMutation;
    }
}

