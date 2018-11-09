package com.exercise.apis.controller;

import com.exercise.apis.dtos.LeagueSearchResult;
import com.exercise.service.LeagueSearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.ThreadSafe;
import javax.ws.rs.core.MediaType;

@Api(produces = "http")
@ThreadSafe
@RequestMapping("/v1/")
@RestController
@ParametersAreNonnullByDefault
public class LeagueSearchController {

    protected static final int HTTP_OK = 200;
    protected static final int HTTP_CREATED = 201;
    protected static final int HTTP_INTERNAL_SERVER_ERROR = 500;
    protected static final int HTTP_BAD_REQUEST = 400;
    protected static final int HTTP_CONFLICT = 409;

    private final LeagueSearchService footballSearchService;

    public LeagueSearchController(LeagueSearchService footballSearchService) {
        this.footballSearchService = footballSearchService;
    }


    @ApiOperation(nickname = "search league controller", value = "search league controller")
    @ApiResponses({
            @ApiResponse(code = HTTP_OK, message = "Result retrieved successfully"),
            @ApiResponse(code = HTTP_INTERNAL_SERVER_ERROR, message = "Internal server error"),
            @ApiResponse(code = HTTP_BAD_REQUEST, message = "Bad Request")})
    @RequestMapping(path = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    public LeagueSearchResult search(@RequestParam("countryName") String countryName,
                                     @RequestParam("leagueName") String leagueName,
                                     @RequestParam("teamName") String teamName) {
        return footballSearchService.search(countryName,leagueName,teamName);
    }

}
