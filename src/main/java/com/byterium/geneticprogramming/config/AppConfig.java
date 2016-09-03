package com.byterium.geneticprogramming.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Random;

@Configuration
@Import({BestTreeFinderConfig.class, TreeGeneratorConfig.class})
@ComponentScan("com.byterium.geneticprogramming")
public class AppConfig {
    @Bean
    public Random random() {
        return new Random();
    }
}
