package com.exercise.service;

import com.exercise.service.client.LeagueDataClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LeagueSearchServiceTest {

    @Mock
    private LeagueDataClient leagueDataClient;

    private final LeagueSearchService leagueSearchService;


    public LeagueSearchServiceTest() {
        leagueSearchService = new LeagueSearchService(leagueDataClient);
    }

    @Test
    public void testLeagueSearchResult(){

    }

    @Test
    public void testGetCountryId(){

    }

    @Test
    public void testGetLeagueId(){

    }

    @Test
    public void testGetStandingDetails(){

    }

}
