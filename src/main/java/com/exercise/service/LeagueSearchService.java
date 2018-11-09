package com.exercise.service;

import com.exercise.apis.dtos.LeagueSearchResult;
import com.exercise.service.client.LeagueDataClient;
import com.exercise.service.dtos.CountryDetail;
import com.exercise.service.dtos.LeagueDetail;
import com.exercise.service.dtos.StandingDetail;
import com.exercise.service.exceptions.CountryNotFoundException;
import com.exercise.service.exceptions.LeagueNotFoundException;
import com.exercise.service.exceptions.TeamNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LeagueSearchService {

    private final LeagueDataClient leagueDataClient;

    public LeagueSearchService(LeagueDataClient leagueDataClient) {
        this.leagueDataClient = leagueDataClient;
    }


    public LeagueSearchResult search(String countryName, String leagueName, String teamName) {
        String countryId = getCountryId(countryName);
        String leagueId = getLeagueId(countryId, leagueName);
        StandingDetail standingDetail = getStandingDetail(leagueId, teamName);
        return new LeagueSearchResult(countryId, countryName, leagueId, leagueName, "Unknown", teamName, standingDetail.getOverallLeaguePosition());
    }

    private String getCountryId(String countryName) {
        CountryDetail[] countryDetails = leagueDataClient.getCountries();
        if (countryDetails != null)
            for (CountryDetail countryDetail : countryDetails)
                if (countryDetail.getCountryName().equals(countryName))
                    return countryDetail.getCountryId();
        throw new CountryNotFoundException("Country:" + countryName + " not found.");
    }

    private String getLeagueId(String countryId, String leagueName) {
        LeagueDetail[] leagueDetails = leagueDataClient.getLeagues(countryId);
        if (leagueDetails != null)
            for (LeagueDetail leagueDetail : leagueDetails)
                if (leagueDetail.getLeagueName().equals(leagueName))
                    return leagueDetail.getLeagueId();
        throw new LeagueNotFoundException("League:" + leagueName + " not found.");
    }

    private StandingDetail getStandingDetail(String leagueId, String teamName) {
        StandingDetail[] standingDetails = leagueDataClient.getStandings(leagueId);
        if (standingDetails != null)
            for (StandingDetail standingDetail : standingDetails)
                if (standingDetail.getTeamName().equals(teamName))
                    return standingDetail;
        throw new TeamNotFoundException("TeamName:" + teamName + " not found.");
    }

}
