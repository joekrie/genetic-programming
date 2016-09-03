package com.byterium.geneticprogramming.trees.operators;

public class MultiplicationOperator implements Operator {
    @Override
    public Number evaluate(Number left, Number right) {
        return left.doubleValue() * right.doubleValue();
    }

    @Override
    public String getSymbol() {
        return "*";
    }
}
