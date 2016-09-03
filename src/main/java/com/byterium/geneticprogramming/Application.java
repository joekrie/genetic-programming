package com.byterium.geneticprogramming;

import com.byterium.geneticprogramming.config.AppConfig;
import com.byterium.geneticprogramming.trees.evaluation.BestTreeFinder;
import com.byterium.geneticprogramming.trees.evaluation.Input;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        BestTreeFinder treeFinder = context.getBean(BestTreeFinder.class);

        List<Input> inputs = SettingsRepository
                .getTrainingData()
                .entrySet()
                .stream()
                .map(entry -> new Input(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        treeFinder.findBestTree(inputs);
    }
}
