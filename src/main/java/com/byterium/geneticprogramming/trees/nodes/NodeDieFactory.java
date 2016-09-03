package com.byterium.geneticprogramming.trees.nodes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;
import com.byterium.geneticprogramming.config.TreeGeneratorConfig;
import com.byterium.geneticprogramming.trees.evaluators.NodeEvaluatorFactory;

@Component
public class NodeDieFactory {
    private final TreeGeneratorConfig treeGeneratorConfig;
    private final Random randomGenerator;
    private final NodeEvaluatorFactory nodeEvaluatorFactory;

    @Autowired
    public NodeDieFactory(TreeGeneratorConfig treeGeneratorConfig, Random randomGenerator,
                          NodeEvaluatorFactory nodeEvaluatorFactory) {
        this.treeGeneratorConfig = treeGeneratorConfig;
        this.randomGenerator = randomGenerator;
        this.nodeEvaluatorFactory = nodeEvaluatorFactory;
    }

    public NodeDie createInitialNodeDie() {
        return createNodeDie(treeGeneratorConfig.getMaxDepthAllowed());
    }

    public NodeDie createNodeDie(int additionalDepthAllowed) {
        return new NodeDie(additionalDepthAllowed, randomGenerator, treeGeneratorConfig.getChanceOfConstantNode(),
                treeGeneratorConfig.getChanceOfVariableNode(), treeGeneratorConfig.getChanceOfOperatorNode(),
                nodeEvaluatorFactory);
    }
}
