package com.collection.words.wordscollectionswebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;

@EnableJpaAuditing
@SpringBootApplication
public class WordsCollectionApplication {

    public static void main(String[] args) {
        SpringApplication.run(WordsCollectionApplication.class, args);
    }
    @Bean
    public PageableHandlerMethodArgumentResolverCustomizer customizer(){
        return p -> {
            p.setOneIndexedParameters(true);
        };
    }
}
