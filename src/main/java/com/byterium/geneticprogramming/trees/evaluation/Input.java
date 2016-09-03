package com.byterium.geneticprogramming.trees.evaluation;

public class Input {
    private final Number input;
    private final Number expected;

    public Input(Number input, Number expected) {
        this.input = input;
        this.expected = expected;
    }

    public Number getInput() {
        return input;
    }

    public Number getExpected() {
        return expected;
    }
}