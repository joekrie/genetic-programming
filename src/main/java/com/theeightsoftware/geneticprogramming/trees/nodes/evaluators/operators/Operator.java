package com.theeightsoftware.geneticprogramming.trees.nodes.evaluators.operators;

public interface Operator {
    Number evaluate(Number left, Number right);
    String getSymbol();
}

