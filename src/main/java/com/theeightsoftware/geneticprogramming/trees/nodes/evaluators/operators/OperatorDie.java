package com.theeightsoftware.geneticprogramming.trees.nodes.evaluators.operators;

import com.theeightsoftware.geneticprogramming.utilities.Die;
import com.theeightsoftware.geneticprogramming.utilities.EvenDie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

@Component
public class OperatorDie implements Die<Operator> {
    private Die<Supplier<Operator>> die;

    @Autowired
    public OperatorDie(Random randomGenerator) {
        List<Supplier<Operator>> operatorSuppliers = new ArrayList<>(4);

        operatorSuppliers.add(AdditionOperator::new);
        operatorSuppliers.add(SubtractionOperator::new);
        operatorSuppliers.add(MultiplicationOperator::new);
        operatorSuppliers.add(DivisionOperator::new);

        this.die = new EvenDie<>(randomGenerator, operatorSuppliers);
    }

    @Override
    public Operator roll() {
        return die.roll().get();
    }
}