package com.byterium.geneticprogramming.trees;

import com.byterium.geneticprogramming.trees.evaluation.InputResult;
import com.byterium.geneticprogramming.trees.evaluation.TreeEvaluationResult;
import com.byterium.geneticprogramming.trees.nodes.Node;
import com.byterium.geneticprogramming.probability.Coin;
import com.byterium.geneticprogramming.trees.evaluation.Input;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Tree {
    private final Node rootNode;
    private final Coin coin;
    private final Function<Tree, Node> randomNodeRetriever;

    public Tree(Node rootNode, Coin coin, Function<Tree, Node> randomNodeRetriever) {
        this.rootNode = rootNode;
        this.coin = coin;
        this.randomNodeRetriever = randomNodeRetriever;
    }

    public TreeEvaluationResult evaluate(List<Input> inputs) {
        List<InputResult> results = inputs.stream()
                .map(this::evaluateInput)
                .collect(Collectors.toList());
        return new TreeEvaluationResult(this, results);
    }

    private InputResult evaluateInput(Input input) {
        Number actual = rootNode.evaluate(input.getInput());
        return new InputResult(input.getInput(), input.getExpected(), actual);
    }

    public String print() {
        return rootNode.print();
    }

    public Tree mutate() {
        Tree mutatedTree = duplicate();
        mutatedTree.rootNode.signalMutate();
        return mutatedTree;
    }

    public Tree crossover(Tree otherTree) {
        boolean useThisAsBase = coin.flip();
        Tree baseTree = useThisAsBase ? duplicate() : otherTree.duplicate();
        Tree nonBaseTree = useThisAsBase ? otherTree.duplicate() : duplicate();

        Node nodeToReplace = baseTree.getRandomNode();
        Node replacementNode = nonBaseTree.getRandomNode().duplicate();
        nodeToReplace.replace(replacementNode.getNodeEvaluator());

        return baseTree;
    }

    public List<Node> getAllNodes() {
        List<Node> nodes = rootNode.getChildNodes();
        nodes.add(rootNode);
        return nodes;
    }

    public Node getRandomNode() {
        return randomNodeRetriever.apply(this);
    }

    public Tree duplicate() {
        return new Tree(rootNode.duplicate(), coin, randomNodeRetriever);
    }
}

