package com.byterium.geneticprogramming.trees.evaluators;

import com.byterium.geneticprogramming.trees.nodes.Node;
import com.byterium.geneticprogramming.probability.Die;
import com.byterium.geneticprogramming.probability.EvenDie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.function.Function;

@Component
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
