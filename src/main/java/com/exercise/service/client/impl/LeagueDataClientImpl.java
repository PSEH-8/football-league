package com.exercise.service.client.impl;

import com.exercise.service.client.LeagueDataClient;
import com.exercise.service.dtos.CountryDetail;
import com.exercise.service.dtos.LeagueDetail;
import com.exercise.service.dtos.StandingDetail;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
public class LeagueDataClientImpl implements LeagueDataClient {

    private final RestTemplate restTemplate;
    private final String baseURI ="https://apifootball.com/api/";
    private final String apiKey="9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978";

    public LeagueDataClientImpl(){
        restTemplate = new RestTemplateBuilder().build();
    }

    @Override
    public CountryDetail[] getCountries() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("action","get_countries");
        return getResult(CountryDetail[].class,params);
    }

    @Override
    public LeagueDetail[] getLeagues(String countryId) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("action","get_leagues");
        params.put("country_id",countryId);
        return getResult(LeagueDetail[].class,params);
    }

    @Override
    public StandingDetail[] getStandings(String leagueId) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("action","get_standings");
        params.put("league_id",leagueId);
        return getResult(StandingDetail[].class,params);
    }

    private <T> T getResult(Class<T> responseType, Map<String,String> queryParams){
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseURI)
                .queryParam("APIkey", apiKey);
        queryParams.entrySet().forEach(e-> builder.queryParam(e.getKey(),e.getValue()));
        String uriBuilder = builder.build().encode().toUriString();
        return restTemplate.getForObject(uriBuilder, responseType);
    }
}
