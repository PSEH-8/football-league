package com.exercise.apis.config.spring;

import com.exercise.apis.controller.LeagueSearchController;
import com.exercise.service.LeagueSearchService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.inject.Inject;

@Configuration
@ComponentScan({ "com.exercise.apis", "com.exercise.service"})
@EnableConfigurationProperties
public class ApplicationConfig {
    @Inject
    private LeagueSearchService leagueSearchService;

    @Bean
    public LeagueSearchController leagueSearchController(){
        return new LeagueSearchController(leagueSearchService);
    }
}
