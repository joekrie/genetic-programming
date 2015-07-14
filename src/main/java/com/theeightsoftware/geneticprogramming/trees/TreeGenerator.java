package com.theeightsoftware.geneticprogramming.trees;

import com.theeightsoftware.geneticprogramming.trees.nodes.Node;
import com.theeightsoftware.geneticprogramming.utilities.Coin;
import com.theeightsoftware.geneticprogramming.utilities.Die;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Component
public class TreeGenerator {
    private final Die<Node> nodeDie;
    private final Coin coin;
    private final Function<List<Node>, Die<Node>> nodeDieCreator;

    @Autowired
    public TreeGenerator(@Qualifier("nodeDie") Die<Node> nodeDie, @Qualifier("evenCoin") Coin coin,
                         Function<List<Node>, Die<Node>> nodeDieCreator) {
        this.nodeDie = nodeDie;
        this.coin = coin;
        this.nodeDieCreator = nodeDieCreator;
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

