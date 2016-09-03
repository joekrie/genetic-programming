package com.byterium.geneticprogramming.trees;

import com.byterium.geneticprogramming.probability.EvenCoin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.byterium.geneticprogramming.trees.nodes.Node;
import com.byterium.geneticprogramming.trees.nodes.NodeDie;
import com.byterium.geneticprogramming.trees.nodes.NodeDieFactory;
import com.byterium.geneticprogramming.probability.Coin;
import com.byterium.geneticprogramming.probability.Die;

@Component
public class TreeGenerator {
    private final NodeDie nodeDie;
    private final Coin coin;
    private final Function<List<Node>, Die<Node>> nodeDieCreator;

    @Autowired
    public TreeGenerator(NodeDieFactory nodeDieFactory, EvenCoin coin, Function<List<Node>, Die<Node>> nodeDieCreator) {
        this.coin = coin;
        this.nodeDieCreator = nodeDieCreator;

        this.nodeDie = nodeDieFactory.createInitialNodeDie();
    }

    public Tree generateTree() {
        Node rootNode = nodeDie.roll();
        return new Tree(rootNode, coin, this::retrieveRandomNode);
    }

    public List<Tree> generateTrees(int count) {
        List<Tree> trees = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            Tree tree = generateTree();
            trees.add(tree);
        }
        return trees;
    }

    private Node retrieveRandomNode(Tree tree) {
        List<Node> allNodes = tree.getAllNodes();
        Die<Node> dieNode = nodeDieCreator.apply(allNodes);
        return dieNode.roll();
    }
}
