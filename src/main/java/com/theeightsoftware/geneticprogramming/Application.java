package com.theeightsoftware.geneticprogramming;

import com.theeightsoftware.geneticprogramming.config.AppConfig;
import com.theeightsoftware.geneticprogramming.trees.evaluation.BestTreeFinder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        BestTreeFinder treeFinder = context.getBean(BestTreeFinder.class);
        treeFinder.findBestTree()
    }
}
