package com.theeightsoftware.geneticprogramming.config;

import com.theeightsoftware.geneticprogramming.trees.TreeGeneratorConfig;
import com.theeightsoftware.geneticprogramming.trees.evaluation.EvaluationConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Random;

@Configuration
@Import({EvaluationConfig.class, TreeGeneratorConfig.class})
@ComponentScan("com.theeightsoftware.geneticprogramming")
public class AppConfig {
    @Bean
    public Random random() {
        return new Random();
    }
}