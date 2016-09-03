package com.byterium.geneticprogramming.trees.operators;

public class AdditionOperator implements Operator {
    @Override
    public Number evaluate(Number left, Number right) {
        return left.doubleValue() + right.doubleValue();
    }

    @Override
    public String getSymbol() {
        return "+";
    }
}
