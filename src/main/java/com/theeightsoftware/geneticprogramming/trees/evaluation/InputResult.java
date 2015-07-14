package com.theeightsoftware.geneticprogramming.trees.evaluation;

public class InputResult {
    private final Number input;
    private final Number expected;
    private final Number actual;

    public InputResult(Number input, Number expected, Number actual) {
        this.input = input;
        this.expected = expected;
        this.actual = actual;
    }

    public Number getInput() {
        return input;
    }

    public Number getExpected() {
        return expected;
    }

    public Number getActual() {
        return actual;
    }
}