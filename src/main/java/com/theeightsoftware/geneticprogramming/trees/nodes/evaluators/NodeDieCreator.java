package com.theeightsoftware.geneticprogramming.trees.nodes.evaluators;

import com.theeightsoftware.geneticprogramming.trees.nodes.Node;
import com.theeightsoftware.geneticprogramming.utilities.Die;
import com.theeightsoftware.geneticprogramming.utilities.EvenDie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.function.Function;

@Component("nodeDieCreator")
public class NodeDieCreator implements Function<List<Node>, Die<Node>> {
    private final Random randomGenerator;

    @Autowired
    public NodeDieCreator(Random randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    @Override
    public Die<Node> apply(List<Node> nodes) {
        return new EvenDie<>(randomGenerator, nodes);
    }
}
