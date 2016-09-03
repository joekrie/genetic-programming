package com.byterium.geneticprogramming.trees.operators;

public interface Operator {
    Number evaluate(Number left, Number right);
    String getSymbol();
}

