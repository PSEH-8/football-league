package com.exercise.service.client;

import com.exercise.service.dtos.CountryDetail;
import com.exercise.service.dtos.LeagueDetail;
import com.exercise.service.dtos.StandingDetail;

public interface LeagueDataClient {

    CountryDetail[] getCountries() ;

    LeagueDetail[] getLeagues(String countryId);

    StandingDetail[] getStandings(String leagueId);
}
