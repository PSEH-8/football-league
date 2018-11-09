package com.exercise.apis.controller;

import com.exercise.apis.dtos.LeagueSearchResult;
import com.exercise.service.LeagueSearchService;
import com.exercise.service.exceptions.CountryNotFoundException;
import com.exercise.service.exceptions.LeagueNotFoundException;
import com.exercise.service.exceptions.TeamNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import java.net.URL;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LeagueSearchControllerTest {

    @Mock
    private LeagueSearchService leagueSearchService;

    private LeagueSearchController leagueSearchController;


    @Before
    public void setUp(){
        leagueSearchController = new LeagueSearchController(leagueSearchService);

    }

    @Test
    public void testSearchTeamPositionInLeagueByCountryNameLeagueNameAndTeamName(){
        String countName = "England";
        String leagueName = "Championship";
        String teamName = "Leeds United";
        String countryId = "1";
        String leagueId = "1";
        String teamId = "Default";
        String position = "1";
        LeagueSearchResult expectedResult = new LeagueSearchResult(countryId, countName,
                leagueId, leagueName, teamId, teamName, position);
        when(leagueSearchService.search(countName, leagueName, teamName)).thenReturn(expectedResult);
        LeagueSearchResult actualResult = leagueSearchController.search(countName,leagueName, teamName);
        Assert.assertEquals(expectedResult, actualResult);

    }

    @Test(expected = TeamNotFoundException.class)
    public void testSearchTeamPositionWhenTeamNameNotPresent(){
        String countName = "England";
        String leagueName = "Championship";
        String teamName = "ABC";
        leagueSearchController.search(countName,leagueName, teamName);
    }

    @Test(expected = LeagueNotFoundException.class)
    public void testSearchTeamPositionWhenLeagueNameNotPresent(){
        String countName = "England";
        String leagueName = "ABC";
        String teamName = "Leeds United";
        leagueSearchController.search(countName,leagueName, teamName);
    }

    @Test(expected = CountryNotFoundException.class)
    public void testSearchTeamPositionWhenTeamCountryNotPresent(){
        String countName = "England";
        String leagueName = "Championship";
        String teamName = "ABC";
        leagueSearchController.search(countName,leagueName, teamName);
    }

}
