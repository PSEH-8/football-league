package com.exercise.apis.config.spring;

import com.exercise.service.LeagueSearchService;
import com.exercise.service.client.LeagueDataClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class ServiceConfig {

    @Autowired
    private LeagueDataClient leagueDataClient;

    @Bean
    public LeagueSearchService leagueSearchService(){
        return new LeagueSearchService(leagueDataClient);
    }
}
