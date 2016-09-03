package com.byterium.geneticprogramming.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TreeGeneratorConfig {
    @Bean
    public int getMaxConstant() {
        return 10;
    }

    @Bean
    public int getMaxDepthAllowed() {
        return 10;
    }

    @Bean
    public Number getChanceOfConstantNode() {
        return 0.33d;
    }

    @Bean
    public Number getChanceOfVariableNode() {
        return 0.33d;
    }

    @Bean
    public Number getChanceOfOperatorNode() {
        return 0.33d;
    }

    @Bean
    public Number getChanceOfMutation() {
        return 0.33d;
    }
}
