package com.byterium.geneticprogramming.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BestTreeFinderConfig {
    @Bean
    public double getDiffThreshold() {
        return 0.5d;
    }

    @Bean
    public double getPercentToSelectEachGeneration() {
        return 0.5d;
    }

    @Bean
    public int getInitialTreeCount() {
        return 100;
    }
}
